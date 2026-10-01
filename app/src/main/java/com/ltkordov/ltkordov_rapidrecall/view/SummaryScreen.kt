package com.ltkordov.ltkordov_rapidrecall.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltkordov.ltkordov_rapidrecall.models.HistoryEntry
import com.ltkordov.ltkordov_rapidrecall.models.HistoryModel
import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TView
import kotlin.math.roundToInt

// This could arguably be a footer inside of HistoryScreen, but I just chose to make it separate
// That being said, it still depends on HistoryModel, so it's a good example of two views on one model
class SummaryScreen: TView<HistoryModel> {
    private var numAttempts by mutableIntStateOf(0)
    private var numCorrect by mutableIntStateOf(0)
    private var formattedCorrectPercentage by mutableStateOf("")

    override fun update(model: HistoryModel) {
        numAttempts = model.numTries
        numCorrect = model.numCorrect
        formattedCorrectPercentage = "%.2f".format(model.correctPercentage * 100)
    }


    @Composable
    fun Render(onGoBack: () -> Unit, modifier: Modifier = Modifier) {
        Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically)) {
            Text("Number of Attempts: $numAttempts", fontSize = 24.sp)
            Text("Number of Correct Answers: $numCorrect", fontSize = 24.sp)
            Text("Win Percentage: $formattedCorrectPercentage", fontSize = 24.sp)
            Button(onClick = { onGoBack() }) {
                Text("Go Back")
            }
        }
    }
}