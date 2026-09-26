package com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`

// Implemented as described in "MVC and Android" slides, slide 39
interface TView<M> {
    fun update(model: M)
}