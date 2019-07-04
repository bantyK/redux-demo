package com.banty.domain.redux

import com.banty.domain.model.Todo

sealed class TodoAction {
    data class AddTodo(val todo: Todo) : TodoAction()
    data class DeleteTodo(val id: String) : TodoAction()
    data class CheckAll(val completed:Boolean) : TodoAction()
    object GenerateData : TodoAction()
}