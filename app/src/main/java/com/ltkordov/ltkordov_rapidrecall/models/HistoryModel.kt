package com.ltkordov.ltkordov_rapidrecall.models

import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TModel
import java.util.Date

// This could be thought of as its own class, but I chose to inline it here
// I truly feel that HistoryEntry does not have state of its own, but is instead just an Object designed to be created and managed exclusively by other models and controllers
// In a language like Typescript this would've just been `type HistoryEntry = { ... }`
data class HistoryEntry (
    val sequenceLength: Int,
    val input: String,
    val sequence: String,
    val correct: Boolean,
    val timestamp: Date
)

class HistoryModel: TModel<HistoryModel>() {
    // Declaring a list as `val` only makes the POINTER to it unchangable
    // Anything could modify the list
    // As such, we can't expose a public mutable list of our entries
    // So we use a getter
    private val mutableEntries = mutableListOf<HistoryEntry>()
    val entries: List<HistoryEntry>
        get() = mutableEntries.toList()

    var numTries: Int = 0
        private set
    var numCorrect: Int = 0
        private set
    var numWrong: Int = 0
        private set
    var correctPercentage: Double = 0.0
        private set

    fun addEntry(newEntry: HistoryEntry) {
        mutableEntries.add(newEntry)
        numTries++
        if (newEntry.correct) numCorrect++ else numWrong++
        correctPercentage = numCorrect.toDouble() / numTries
        notifyViews(this)
    }
}