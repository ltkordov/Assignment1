package com.ltkordov.ltkordov_rapidrecall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun GameplayScreen(modifier: Modifier = Modifier, sequenceLength: Int, onReport: (result: HistoryEntry) -> Unit, onBack: () -> Unit) {
    val gameInstance = remember { GameInstance(sequenceLength, onReport)}
    var currentDigit by remember { mutableStateOf("") }
    var gamePhase by remember { mutableStateOf("Running") }
    var guess by remember { mutableStateOf("") }
    var result by remember {mutableStateOf<GameResult?>(null)}
    var blinkNumber by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        gameInstance.setup()
        var nextDigit = gameInstance.getNextDigit()
        while (nextDigit != "END") {
            currentDigit = nextDigit
            blinkNumber = false
            delay(500.milliseconds)
            blinkNumber = true
            delay(500.milliseconds)
            nextDigit = gameInstance.getNextDigit()
        }
        gamePhase = "Guess"
    }

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        if (gamePhase == "Running") {
            if (blinkNumber == false) {
                Text(text=currentDigit, fontSize=48.sp)
            }
        }
        if (gamePhase == "Guess") {
            Text(text="Guess", fontSize=48.sp)
            OutlinedTextField(value=guess, onValueChange = { guess = it }, maxLines = 1)
            Button(onClick={
                result = gameInstance.recordGuess(guess.trim())
                gamePhase = "Result"
            }) {
                Text("Submit Guess")
            }
        }
        if (gamePhase == "Result") {
            Text(text=(if (result!!.correct) "Correct!" else "Incorrect!"))
            Text("Your Guess:")
            Text(guess)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Correct Sequence:")
            Text(result!!.sequence)
            Button(onClick={onBack()}) {
                Text(("Go Back"))
            }
        }
    }

}