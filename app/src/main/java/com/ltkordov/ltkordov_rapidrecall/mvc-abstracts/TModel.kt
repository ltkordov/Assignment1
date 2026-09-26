package com.ltkordov.ltkordov_rapidrecall.`mvc-abstracts`

abstract class TModel<M> {
    private val views = mutableListOf<TView<M>>()

    fun addView(view: TView<M>) {
        if (view !in views) {
            views.add(view)
        }
    }
    fun deleteView(view: TView<M>) {
        views.remove(view)
    }
    protected fun notifyViews(model: M) {
        views.forEach { view -> view.update(model) }
    }
}