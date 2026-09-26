package com.ltkordov.ltkordov_rapidrecall

import java.util.Date

data class HistoryEntry(
    val correct: Boolean,
    val sequence: String,
    val guess: String,
    val sequenceLength: Int,
    val time: Date
)
