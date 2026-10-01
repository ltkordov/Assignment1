package com.ltkordov.ltkordov_rapidrecall.controllers

import com.ltkordov.ltkordov_rapidrecall.models.RouterModel
import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TCommand

// This is a very simple controller, but it does an important thing: It enforces a set of specific screen names that can be called
// The model already validates that the string is one of these options, but it would fail silently and cause confusion for the user if there was a typo anywhere
// By only exposing named and typed functions, we make sure the behaviour is what we want
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