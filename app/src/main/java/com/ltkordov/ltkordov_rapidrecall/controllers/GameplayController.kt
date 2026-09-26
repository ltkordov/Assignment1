package com.ltkordov.ltkordov_rapidrecall.controllers

import com.ltkordov.ltkordov_rapidrecall.models.ActiveGameplaySessionModel
import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TCommand
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

// Called just GameplayController because it coordinates the active gameplay and the gameplay history
class GameplayController(
    private val model: ActiveGameplaySessionModel
) {
    suspend fun startSequence() { // I think suspend is like async in TS, android studio intellisense told me to do this
        model.generateString()
        for (i in 0..<model.length) {
            model.showChar(i)
            delay(500.milliseconds)
            model.hideChar()
            delay(500.milliseconds)
        }
        model.switchToGuessingPhase()
    }
    fun recordGuess(guess: String) {
        model.recordGuess(guess);
        // TODO: History.addEntry()
    }

}