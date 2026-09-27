package com.ltkordov.ltkordov_rapidrecall.controllers

import com.ltkordov.ltkordov_rapidrecall.models.RouterModel
import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TCommand

class RouterController(
    private val model: RouterModel
) {
    val navigateHome = TCommand {
        model.setScreen("Home")
    }
    val navigateToGameplay = TCommand {
        model.setScreen("Gameplay")
    }

    val navigateToHistory = TCommand {
        model.setScreen("History")
    }

    val navigateToSummary = TCommand {
        model.setScreen("Summary")
    }
}