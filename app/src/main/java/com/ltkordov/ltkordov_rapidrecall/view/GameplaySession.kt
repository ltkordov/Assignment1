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
    var gamePhase: String = ""
        private set
    var currentChar: Char? = null
        private set
    var isCorrect: Boolean? = null
        private set

    override fun update(model: ActiveGameplaySessionModel) {
        gamePhase = model.gamePhase
        currentChar = model.currentChar
        isCorrect = model.isCorrect
    }


    @Composable
    fun render(onGoBack: () -> Unit, onSubmitGuess: () -> Unit, modifier: Modifier = Modifier) {
        Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            if (gamePhase == "Running") {
                if (currentChar != null) {
                    Text(text=currentChar.toString(), fontSize=48.sp)
                }
            }
            if (gamePhase == "Guess") {
                Text(text="Guess", fontSize=48.sp)
                OutlinedTextField(value=guess, onValueChange = { guess = it }, maxLines = 1)
                Button(onClick={ onSubmitGuess() }) {
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
}