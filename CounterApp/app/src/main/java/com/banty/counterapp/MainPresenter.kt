package com.banty.counterapp

import com.banty.counterapp.redux.CounterAction
import com.banty.counterapp.redux.CounterStore

class MainPresenter(val view: MainView) {

    private val store = CounterStore()

    fun init() {
        view.bind(
            store.actionDispatcher()
                .subscribe { state ->
                    view.updateCount(state.value)
                })
    }

    fun increment() {
        store.dispatcher(CounterAction.IncrementAction)
    }


    fun decrement() {
        store.dispatcher(CounterAction.DecrementAction)
    }

}