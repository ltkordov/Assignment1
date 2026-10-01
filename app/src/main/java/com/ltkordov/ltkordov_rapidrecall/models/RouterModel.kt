package com.ltkordov.ltkordov_rapidrecall.models

import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TModel

// In my mind I considered just saying "The current page is a concern of the VIEW and thus doesn't need a model/controller
// But I ended up still making one for it to demonstrate MVC best practices.
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