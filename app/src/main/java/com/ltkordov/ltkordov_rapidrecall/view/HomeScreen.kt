package com.ltkordov.ltkordov_rapidrecall.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltkordov.ltkordov_rapidrecall.controllers.GameSetupController
import com.ltkordov.ltkordov_rapidrecall.models.GameParametersModel

@Composable
fun HomeScreen(gameParamsModel: GameParametersModel, gameSetupController: GameSetupController, onStart: () -> Unit, modifier: Modifier = Modifier) {
    val gameSetupView = remember { GameSetup() }
    LaunchedEffect(Unit) {
        gameParamsModel.addView(gameSetupView)
        gameSetupView.update(gameParamsModel) // Manually update with the initial values
    }


    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Rapid Recall: Memory Game", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Created By:")
        Text("Logan Kordov")
        Text("CCID: LTKORDOV")
        Text("Student ID: 1844555")
        Spacer(modifier = Modifier.height(32.dp))
        Text("Sequence Length:")

        gameSetupView.Render(
            onIncrement = {
                gameSetupController.increaseLength.execute()
            },
            onDecrement = {
                gameSetupController.decreaseLength.execute()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onStart() }) {
            Text("Start!")
        }
    }
}