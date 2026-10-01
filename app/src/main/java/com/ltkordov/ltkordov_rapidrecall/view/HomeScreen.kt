package com.ltkordov.ltkordov_rapidrecall.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltkordov.ltkordov_rapidrecall.controllers.GameplayController

// This is the only composable UI function in the app that ISN'T in a view
// But I did that because this is all just either static information, styling, or calling other views that are passed in
// Nothing in this function itself rerenders on state changes, and so I just made it a function.
@Composable
fun HomeScreen(gameSetupView: GameSetup, gameplayController: GameplayController, onStart: () -> Unit, onGoHistory: () -> Unit, onGoSummary: () -> Unit, modifier: Modifier = Modifier) {
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
                gameplayController.increaseLength.execute()
            },
            onDecrement = {
                gameplayController.decreaseLength.execute()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onStart() }) {
            Text("Start!")
        }
        Button(onClick = { onGoHistory() }) {
            Text("View History")
        }
        Button(onClick = { onGoSummary() }) {
            Text("View Summary")
        }
    }
}