package com.ltkordov.ltkordov_rapidrecall.models

import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TModel

class GameParametersModel: TModel<GameParametersModel>() {
    var length: Int = 1
        private set

    fun setLength(newLength: Int) {
        if (newLength in 1..10) {
            length = newLength;
            notifyViews(this)
        }
    }
    val canIncreaseLength: Boolean
        get() = length < 10
    val canDecreaseLength: Boolean
        get() = length > 1
}