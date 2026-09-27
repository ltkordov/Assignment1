package com.ltkordov.ltkordov_rapidrecall.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ltkordov.ltkordov_rapidrecall.controllers.GameplayController
import com.ltkordov.ltkordov_rapidrecall.controllers.RouterController
import com.ltkordov.ltkordov_rapidrecall.models.RouterModel
import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TView
import kotlinx.coroutines.launch

class RootRouter(
    private val gameSetupView: GameSetup,
    private val gameplayScreen: GameplaySession,
    private val historyView: HistoryScreen,
    private val summaryView: SummaryScreen,
    private val routerController: RouterController,
    private val gameplayController: GameplayController,
): TView<RouterModel> {
    var currentScreen by mutableStateOf("Home")
        private set

    override fun update(model: RouterModel) {
        currentScreen = model.screen
    }

    @Composable
    fun Render(modifier: Modifier = Modifier) {
        // my gameplayController.startSequence function is "suspend" (async), and so I was getting error messages that I need to put it in a coroutine scope
        // This is the solution that works
        val coroutineScope = rememberCoroutineScope()

        if (currentScreen == "Home") {
            HomeScreen(
                gameSetupView,
                gameplayController,
                { routerController.navigateToGameplay.execute(); coroutineScope.launch{ gameplayController.startSequence() }},
                { routerController.navigateToHistory.execute() },
                { routerController.navigateToSummary.execute() },
                modifier
            )
        }
        if (currentScreen == "Gameplay") {
            gameplayScreen.Render(onGoBack = {routerController.navigateHome.execute()}, onSubmitGuess = {gameplayController.recordGuess(it)}, modifier)
        }
        if (currentScreen == "History") {
            historyView.Render(onGoBack = {routerController.navigateHome.execute()}, modifier)
        }
        if (currentScreen == "Summary") {
            summaryView.Render(onGoBack = {routerController.navigateHome.execute()}, modifier)
        }
    }
}