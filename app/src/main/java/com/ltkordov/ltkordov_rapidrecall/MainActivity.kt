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

class MainActivity : ComponentActivity() {
    val routerModel = RouterModel()
    val historyModel = HistoryModel()
    val gameParametersModel = GameParametersModel()
    val activeGameplaySessionModel = ActiveGameplaySessionModel()

    val routerController = RouterController(routerModel)
    val gameplayController = GameplayController(activeGameplaySessionModel, historyModel, gameParametersModel)

    val gameSetupView = GameSetup()
    val gameplaySessionView = GameplaySession()
    val historyScreenView = HistoryScreen()
    val summaryScreenView = SummaryScreen()

    val routerView = RootRouter(gameSetupView, gameplaySessionView, historyScreenView, summaryScreenView, routerController, gameplayController)

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
