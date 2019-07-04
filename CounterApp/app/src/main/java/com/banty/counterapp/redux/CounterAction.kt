package com.banty.counterapp.redux

sealed class CounterAction {
    object IncrementAction : CounterAction()

    object DecrementAction : CounterAction()
}