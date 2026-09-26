package com.ltkordov.ltkordov_rapidrecall.controllers

import com.ltkordov.ltkordov_rapidrecall.models.GameParametersModel
import com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`.TCommand

class GameSetupController(
    private val model: GameParametersModel
) {
    val increaseLength = TCommand {
        model.setLength(model.length + 1)
    }
    val decreaseLength = TCommand {
        model.setLength(model.length - 1)
    }
}