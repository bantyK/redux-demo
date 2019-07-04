package com.banty.domain.redux.reducers

import com.banty.domain.model.TodoRepository
import com.banty.domain.redux.TodoReducer
import com.banty.domain.redux.TodoState

class GenerateDataReducer(private val todoRepository: TodoRepository) : TodoReducer {
    override fun newState(currentState: TodoState): TodoState {
        val todos = currentState.todos.toMutableList()
            .apply { addAll(todoRepository.generateFakeData()) }

        return currentState.copy(todos = todos)
    }
}