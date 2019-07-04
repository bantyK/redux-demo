package com.banty.domain.redux

import com.banty.domain.model.Todo

data class TodoState(
    val todos: List<Todo> = emptyList()
) : Cloneable {

    public override fun clone(): TodoState {
        val newTodos = mutableListOf<Todo>().apply {
            todos.forEach {
                add(it.copy())
            }
        }

        return TodoState(newTodos)
    }
}