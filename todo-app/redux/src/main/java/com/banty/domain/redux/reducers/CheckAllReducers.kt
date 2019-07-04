package com.banty.domain.redux.reducers

import com.banty.domain.redux.TodoAction
import com.banty.domain.redux.TodoReducer
import com.banty.domain.redux.TodoState

class CheckAllReducers(private val action : TodoAction.CheckAll) : TodoReducer {

    override fun newState(currentState: TodoState): TodoState {
        currentState.todos.forEach {todo->
            todo.completed = action.completed
        }

        return currentState
    }
}