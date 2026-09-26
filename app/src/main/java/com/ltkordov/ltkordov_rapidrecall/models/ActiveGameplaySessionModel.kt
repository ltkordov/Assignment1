package com.ltkordov.ltkordov_rapidrecall.models

import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TModel
import kotlin.random.Random

fun randDigit(): String {
    return Random.nextInt(0, 9).toString()
}

class ActiveGameplaySessionModel(private val sequenceLength: Int): TModel<ActiveGameplaySessionModel>() {
    val length: Int = sequenceLength

    var gamePhase: String = "Showing" // Starts as "Showing", then "Guessing", then "Result"
        private set
    var sequence = ""
        private set
    var currentChar: Char? = null
        private set
    var isCorrect: Boolean? = null
        private set
    var userGuess: String? = null
        private set

    fun hideChar() {
        currentChar = null;
        notifyViews(this)
    }

    fun showChar(charIndex: Int) {
        if (charIndex in 0..sequenceLength) {
            currentChar = sequence[charIndex]
            notifyViews(this)
        }
    }
    fun generateString() {
        sequence = ""
        for (i in 1..sequenceLength) {
            sequence += randDigit()
        }
        notifyViews(this)
    }
    fun switchToGuessingPhase() {
        this.gamePhase = "Guessing"
        notifyViews(this)
    }
    // I toyed with putting this logic in the controller, because you could argue that "does guess == correct" is logic that the model doesn't necessarily care about
    // But then I decided that that logic is CORE to the active gameplay session, the session model would be useless if we didn't know what was correct and what wasn't, the same way any other piece of hardcoded info is given to any other model
    fun recordGuess(guess: String) {
        this.isCorrect = guess == sequence;
        this.gamePhase = "Result"
        this.userGuess = guess
        notifyViews(this)
    }

}