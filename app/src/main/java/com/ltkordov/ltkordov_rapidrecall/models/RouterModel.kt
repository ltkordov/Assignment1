package com.ltkordov.ltkordov_rapidrecall.models

import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TModel

class RouterModel: TModel<RouterModel>() {
    var screen: String = "Home"
        private set

    fun setScreen(newScreen: String) {
        if (newScreen == "Home" || newScreen == "Gameplay" || newScreen == "History" || newScreen == "Summary") {
            screen = newScreen
            notifyViews(this)
        }
    }
}