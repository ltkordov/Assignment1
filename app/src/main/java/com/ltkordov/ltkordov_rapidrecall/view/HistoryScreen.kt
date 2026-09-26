package com.ltkordov.ltkordov_rapidrecall.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltkordov.ltkordov_rapidrecall.controllers.GameSetupController
import com.ltkordov.ltkordov_rapidrecall.models.ActiveGameplaySessionModel
import com.ltkordov.ltkordov_rapidrecall.models.GameParametersModel
import com.ltkordov.ltkordov_rapidrecall.models.HistoryEntry
import com.ltkordov.ltkordov_rapidrecall.models.HistoryModel
import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TView

class HistoryScreen: TView<HistoryModel> {
    var entries by mutableStateOf<List<HistoryEntry>>(emptyList())

    override fun update(model: HistoryModel) {
        entries = model.entries
    }


    @Composable
    fun Render(onGoBack: () -> Unit, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier.fillMaxSize().padding(16.dp)) {
            items(entries) { entry ->
                val formattedDate = entry.timestamp.toString()

                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp).border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(16.dp)
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(formattedDate)
                    Text("Sequence: " + entry.sequence, fontSize = 24.sp)
                    Text("Guess: " + entry.input, fontSize = 24.sp)
                    Text(
                        if (entry.correct) "Correct" else "Incorrect",
                        fontSize = 32.sp,
                        color = if (entry.correct) Color.Green else Color.Red
                    )
                }
            }
        }
        Button(onClick = { onGoBack() }) {
            Text("Go Back")
        }
    }
}