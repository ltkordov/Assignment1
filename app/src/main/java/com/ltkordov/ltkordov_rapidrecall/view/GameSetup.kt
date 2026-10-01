package com.ltkordov.ltkordov_rapidrecall.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltkordov.ltkordov_rapidrecall.models.GameParametersModel
import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TView

class GameSetup: TView<GameParametersModel> {
    private var displayedCurrentLength by mutableIntStateOf(1) // `remember` does basically "don't change this on rerender" but since this class only ever gets invoked once, it doens't need remember
    private var canIncrement: Boolean by mutableStateOf(false)
    private var canDecrement: Boolean by mutableStateOf(false)

    override fun update(model: GameParametersModel) {
        displayedCurrentLength = model.length
        canIncrement = model.canIncreaseLength
        canDecrement = model.canDecreaseLength
    }

    @Composable
    fun Render(onIncrement: () -> Unit, onDecrement: () -> Unit, modifier: Modifier = Modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                // For this, I wasn't sure if enabled=false actually stopeped onClick events from being fired, so I add an if statement check inside the handler just to be safe
                enabled = canDecrement,
                onClick = {
                    if (canDecrement) {
                        onDecrement()
                    }
                }
            ) {
                Text("Down")
            }
            Text(text = displayedCurrentLength.toString(), fontSize = 36.sp)
            Button(
                enabled = canIncrement,
                onClick = {
                    if (canIncrement) {
                        onIncrement()
                    }
                }
            ) {
                Text("Up")
            }
        }
    }
}