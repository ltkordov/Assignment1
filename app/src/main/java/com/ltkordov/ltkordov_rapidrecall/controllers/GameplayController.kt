package com.ltkordov.ltkordov_rapidrecall.controllers

import com.ltkordov.ltkordov_rapidrecall.models.ActiveGameplaySessionModel
import com.ltkordov.ltkordov_rapidrecall.models.GameParametersModel
import com.ltkordov.ltkordov_rapidrecall.models.HistoryEntry
import com.ltkordov.ltkordov_rapidrecall.models.HistoryModel
import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TCommand
import kotlinx.coroutines.delay
import java.util.Date
import kotlin.time.Duration.Companion.milliseconds

// This is called just GameplayController because it coordinates the driving for active gameplay and gameplay history
// It's a really good example of how MVC allows us to have one controller that can orchestrate across multiple models to expose an easy set of command functions
// Originally, I had one controller for each model, but in the process of refactoring I settled on this
class GameplayController(
    private val model: ActiveGameplaySessionModel,
    private val historyModel: HistoryModel,
    private val setupModel: GameParametersModel
) {
    // I stumbled on the `delay` function by just typing out synonyms for 'wait' until the Android Studio suggestions showed me this
    // It then also hinted that I'd need to change this to a `suspend fun`, what that means is that this runs in a coroutine (and thus does not block the main thread)
    // Otherwise, it works like an async function in any other programming language for my purposes
    suspend fun startSequence() {
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