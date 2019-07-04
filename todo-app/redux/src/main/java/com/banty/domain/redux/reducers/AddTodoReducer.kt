package com.banty.domain.redux.reducers

import com.banty.domain.redux.TodoAction
import com.banty.domain.redux.TodoReducer
import com.banty.domain.redux.TodoState

class AddTodoReducer(private val action: TodoAction.AddTodo) : TodoReducer {
    override fun newState(currentState: TodoState): TodoState {
        val todos = currentState.todos.toMutableList().apply { add(action.todo) }
        return currentState.copy(todos = todos)
    }
}