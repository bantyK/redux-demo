package com.banty.domain.redux

interface TodoReducer {
    fun newState(currentState: TodoState): TodoState
}