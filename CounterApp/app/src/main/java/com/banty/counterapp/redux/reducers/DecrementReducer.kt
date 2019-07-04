package com.banty.counterapp.redux.reducers

import com.banty.counterapp.redux.CounterAction
import com.banty.counterapp.redux.CounterReducer
import com.banty.counterapp.redux.CounterState

class DecrementReducer(private val action: CounterAction.DecrementAction) : CounterReducer {

    override fun newState(currentState: CounterState): CounterState {
        return CounterState(value = currentState.value - 1)
    }
}