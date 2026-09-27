package com.ltkordov.ltkordov_rapidrecall.controllers

import com.ltkordov.ltkordov_rapidrecall.models.ActiveGameplaySessionModel
import com.ltkordov.ltkordov_rapidrecall.models.GameParametersModel
import com.ltkordov.ltkordov_rapidrecall.models.HistoryEntry
import com.ltkordov.ltkordov_rapidrecall.models.HistoryModel
import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TCommand
import kotlinx.coroutines.delay
import java.util.Date
import kotlin.time.Duration.Companion.milliseconds

// Called just GameplayController because it coordinates the active gameplay and the gameplay history
class GameplayController(
    private val model: ActiveGameplaySessionModel,
    private val historyModel: HistoryModel,
    private val setupModel: GameParametersModel
) {
    suspend fun startSequence() { // I think suspend is like async in TS, android studio intellisense told me to do this
        model.setupRound(setupModel.length)
        for (i in 0..<model.length) {
            model.showChar(i)
            delay(500.milliseconds)
            model.hideChar()
            delay(500.milliseconds)
        }
        model.switchToGuessingPhase()
    }
    fun recordGuess(guess: String) {
        model.recordGuess(guess.trim());
        historyModel.addEntry(HistoryEntry(
            model.length,
            guess.trim(),
            model.sequence,
            model.isCorrect!!,
            Date(),
        ))
    }

    val increaseLength = TCommand {
        setupModel.setLength(setupModel.length + 1)
    }
    val decreaseLength = TCommand {
        setupModel.setLength(setupModel.length - 1)
    }

}