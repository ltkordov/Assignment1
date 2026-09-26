package com.ltkordov.ltkordov_rapidrecall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ltkordov.ltkordov_rapidrecall.controllers.RouterController
import com.ltkordov.ltkordov_rapidrecall.models.RouterModel
import com.ltkordov.ltkordov_rapidrecall.ui.theme.LtkordovRapidRecallTheme
import com.ltkordov.ltkordov_rapidrecall.view.RootRouter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LtkordovRapidRecallTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppSetup(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppSetup(modifier: Modifier = Modifier) {
    val routerModel = remember { RouterModel() }
    val routerController = remember { RouterController(routerModel) }
    val routerView = remember { RootRouter() }

    LaunchedEffect(Unit) {
        routerModel.addView(routerView)
        routerView.update(routerModel)
    }

    routerView.render(
        onGoHome = {
            routerController.navigateHome.execute()
        },
        onGoGameplay = {
            routerController.navigateToGameplay.execute()
        },
        modifier
    )
}