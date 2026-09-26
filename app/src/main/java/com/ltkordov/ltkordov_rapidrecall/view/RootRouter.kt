package com.ltkordov.ltkordov_rapidrecall.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltkordov.ltkordov_rapidrecall.controllers.GameSetupController
import com.ltkordov.ltkordov_rapidrecall.controllers.GameplayController
import com.ltkordov.ltkordov_rapidrecall.models.ActiveGameplaySessionModel
import com.ltkordov.ltkordov_rapidrecall.models.GameParametersModel
import com.ltkordov.ltkordov_rapidrecall.models.HistoryModel
import com.ltkordov.ltkordov_rapidrecall.models.RouterModel
import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TView

class RootRouter: TView<RouterModel> {

    // This being the root router of the app means (in my opinion) that it is where the setup for the entire rest of the app should live
    private val gameParams = GameParametersModel()
    private val gameSetupController = GameSetupController(gameParams)
    private val historyModel = HistoryModel()


    var currentScreen by mutableStateOf("Home")
        private set

    override fun update(model: RouterModel) {
        currentScreen = model.screen
    }

    @Composable
    fun Render(onGoHome: () -> Unit, onGoGameplay: () -> Unit, onGoHistory: () -> Unit, modifier: Modifier = Modifier) {
        if (currentScreen == "Home") {
            HomeScreen(
                gameParams, gameSetupController, { onGoGameplay() }, { onGoHistory() }, modifier
            )
        }
        if (currentScreen == "Gameplay") {
            // This creates a new ActiveGameplaySessionModel everytime we go out and back
            // Which is exactly what we want, because that way we can make all the logic equal to just one session, with no reset logic needed
            // When you go back out and back in, it destructs and creates new versions of everything
            val activeGameplaySessionModel = remember { ActiveGameplaySessionModel(gameParams.length) }
            val gameplayController = remember { GameplayController(activeGameplaySessionModel, historyModel) }
            val gameplayScreen = remember { GameplaySession() }

            LaunchedEffect(Unit) {
                activeGameplaySessionModel.addView(gameplayScreen)
                gameplayScreen.update(activeGameplaySessionModel)
                gameplayController.startSequence()
            }

            gameplayScreen.Render(onGoBack = {onGoHome()}, onSubmitGuess = {gameplayController.recordGuess(it)}, modifier)
        }
        if (currentScreen == "History") {
            val historyView = remember { HistoryScreen() };
            DisposableEffect(Unit) {
                historyModel.addView(historyView)
                historyView.update(historyModel)
                onDispose { historyModel.deleteView(historyView) }
            }

            historyView.Render(onGoBack = {onGoHome()}, modifier)
        }
    }
}