package com.banty.domain.redux

import com.banty.domain.model.Todo

sealed class TodoAction {
    data class AddTodo(val todo: Todo) : TodoAction()
//    data class UpdateTodo(val id: String, val text: String, val completed: Boolean) : TodoAction()
    data class DeleteTodo(val id: String) : TodoAction()
    object GenerateData : TodoAction()
}