package com.banty.reduxdemo3

import com.banty.domain.model.Todo
import com.banty.domain.redux.Redux
import com.banty.domain.redux.TodoAction
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainPresenter(val view: MainView) : KoinComponent {

    private val redux: Redux by inject()

    fun initialise() {
        view.bind(redux.actionDispatcher()
            .subscribe { state ->
                view.updateTodo(state.todos)
            })

    }

    fun addTodo(text: String) {
        redux.dispatcher(TodoAction.AddTodo(Todo(text)))
    }

    fun deleteTodo(id: String) {
        redux.dispatcher(TodoAction.DeleteTodo(id))
    }

    fun generateData() {
        redux.dispatcher(TodoAction.GenerateData)
    }
}