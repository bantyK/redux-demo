package com.banty.domain.redux.reducers

import com.banty.domain.redux.TodoAction
import com.banty.domain.redux.TodoReducer
import com.banty.domain.redux.TodoState

class DeleteTodoReducer(private val action: TodoAction.DeleteTodo) : TodoReducer {

    override fun newState(currentState: TodoState): TodoState {
        val todos = currentState.todos.filter { it.id != action.id }
        return currentState.copy(todos = todos)
    }
}