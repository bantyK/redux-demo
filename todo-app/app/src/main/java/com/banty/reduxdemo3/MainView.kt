package com.banty.reduxdemo3

import com.banty.domain.model.Todo
import io.reactivex.disposables.Disposable

interface MainView {

    fun bind(disposable: Disposable)

    fun updateTodo(todos: List<Todo>)

}