package com.ltkordov.ltkordov_rapidrecall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ltkordov.ltkordov_rapidrecall.controllers.GameplayController
import com.ltkordov.ltkordov_rapidrecall.controllers.RouterController
import com.ltkordov.ltkordov_rapidrecall.models.ActiveGameplaySessionModel
import com.ltkordov.ltkordov_rapidrecall.models.GameParametersModel
import com.ltkordov.ltkordov_rapidrecall.models.HistoryModel
import com.ltkordov.ltkordov_rapidrecall.models.RouterModel
import com.ltkordov.ltkordov_rapidrecall.ui.theme.LtkordovRapidRecallTheme
import com.ltkordov.ltkordov_rapidrecall.view.GameSetup
import com.ltkordov.ltkordov_rapidrecall.view.GameplaySession
import com.ltkordov.ltkordov_rapidrecall.view.HistoryScreen
import com.ltkordov.ltkordov_rapidrecall.view.RootRouter
import com.ltkordov.ltkordov_rapidrecall.view.SummaryScreen

// I asked in class about the best way to handle UI that is 'scoped', as in it technically describes something that comes and goes
// What Prof. Campbell told me was that I should declare just one copy of each of my models so that I'm not creating and destroying things in the lifecycle of the app
// In order to do that, what I did was I initialize all of my models, controllers, etc right here in the root of the project
// And then I just pass things down as needed.
// This functions as the 'entrypoint' of the app, it doesn't rerender on any state changes itself, it just orchestrates all of the models, controllers, and views
class MainActivity : ComponentActivity() {
    private val routerModel = RouterModel()
    private val historyModel = HistoryModel()
    private val gameParametersModel = GameParametersModel()
    private val activeGameplaySessionModel = ActiveGameplaySessionModel()

    private val routerController = RouterController(routerModel)
    private val gameplayController = GameplayController(activeGameplaySessionModel, historyModel, gameParametersModel)

    private val gameSetupView = GameSetup()
    private val gameplaySessionView = GameplaySession()
    private val historyScreenView = HistoryScreen()
    private val summaryScreenView = SummaryScreen()

    private val routerView = RootRouter(gameSetupView, gameplaySessionView, historyScreenView, summaryScreenView, routerController, gameplayController)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        routerModel.addView(routerView)
        routerView.update(routerModel)
        gameParametersModel.addView(gameSetupView)
        gameSetupView.update(gameParametersModel)
        activeGameplaySessionModel.addView(gameplaySessionView)
        gameplaySessionView.update(activeGameplaySessionModel)
        historyModel.addView(historyScreenView)
        historyScreenView.update(historyModel)
        historyModel.addView(summaryScreenView)
        summaryScreenView.update(historyModel)


        enableEdgeToEdge()
        setContent {
            LtkordovRapidRecallTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    routerView.Render(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
