package com.ltkordov.ltkordov_rapidrecall.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.ltkordov.ltkordov_rapidrecall.models.ActiveGameplaySessionModel
import com.ltkordov.ltkordov_rapidrecall.models.GameParametersModel
import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TView

class GameplaySession: TView<ActiveGameplaySessionModel> {
    var gamePhase by mutableStateOf("")
        private set
    var currentChar by mutableStateOf<Char?>(null)
        private set
    var isCorrect by mutableStateOf<Boolean?>(null)
        private set
    var guess by mutableStateOf<String?>(null)
        private set
    var sequence by mutableStateOf<String?>(null)
        private set

    override fun update(model: ActiveGameplaySessionModel) {
        gamePhase = model.gamePhase
        currentChar = model.currentChar
        isCorrect = model.isCorrect
        guess = model.userGuess
        sequence = model.sequence
    }



    @Composable
    fun Render(onGoBack: () -> Unit, onSubmitGuess: (guess: String) -> Unit, modifier: Modifier = Modifier) {
        // How the user inputs their guess is NOT the concern of the model or controller
        // That is very view-specific behaviour.
        // That's why we have this text input value as a piece of state in the View instead of in something else
        var textInputGuessValue by remember { mutableStateOf("") }

        Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            if (gamePhase == "Showing") {
                if (currentChar != null) {
                    Text(text=currentChar.toString(), fontSize=48.sp)
                }
            }
            if (gamePhase == "Guessing") {
                Text(text="Guess", fontSize=48.sp)
                OutlinedTextField(value=textInputGuessValue, onValueChange = { textInputGuessValue = it }, maxLines = 1)
                Button(onClick={ onSubmitGuess(textInputGuessValue) }) {
                    Text("Submit Guess")
                }
            }
            if (gamePhase == "Result") {
                Text(text=(if (isCorrect == true) "Correct!" else "Incorrect!"))
                Text("Your Guess:")
                Text(guess!!)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Correct Sequence:")
                Text(sequence!!)
                Button(onClick={onGoBack()}) {
                    Text(("Go Back"))
                }
            }
        }
    }
}