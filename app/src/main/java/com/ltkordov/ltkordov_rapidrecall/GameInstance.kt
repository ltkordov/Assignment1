package com.ltkordov.ltkordov_rapidrecall

import java.util.Date
import kotlin.random.Random

data class GameResult(
    val correct: Boolean,
    val sequence: String,
)

fun randDigit(): String {
    return Random.nextInt(0, 9).toString()
}

class GameInstance(private val sequenceLength: Int, private val onReport: (data: HistoryEntry) -> Unit) {
    private var sequence = ""
    private var currentDigit = 0;

    fun setup() {
        for (i in 1..sequenceLength) {
            sequence += randDigit()
        }
    }

    fun getNextDigit(): String {
        if (currentDigit >= sequenceLength) {
            return "END"
        }
        return sequence[currentDigit++].toString()
    }


    fun recordGuess(guess: String): GameResult {
        val wasCorrect = guess == sequence;
        onReport(HistoryEntry(wasCorrect, sequence, guess, sequenceLength, Date()))
        return GameResult(wasCorrect, sequence)
    }
}