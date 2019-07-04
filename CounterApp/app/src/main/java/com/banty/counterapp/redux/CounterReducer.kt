package com.banty.counterapp.redux

interface CounterReducer {

    fun newState(currentState:CounterState) : CounterState
}