package com.banty.reduxdemo3

import com.banty.domain.model.Todo
import com.banty.domain.redux.ReduxStore
import com.banty.domain.redux.TodoAction
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainPresenter(val view: MainView) : KoinComponent {

    private val store: ReduxStore by inject()

    fun initialise() {
        view.bind(store.actionDispatcher()
            .subscribe { state ->
                view.updateTodo(state.todos)
            })

    }

    fun addTodo(text: String) {
        store.dispatcher(TodoAction.AddTodo(Todo(text)))
    }

    fun deleteTodo(id: String) {
        store.dispatcher(TodoAction.DeleteTodo(id))
    }

    fun generateData() {
        store.dispatcher(TodoAction.GenerateData)
    }

    fun checkAll(isChecked:Boolean) {
        store.dispatcher(TodoAction.CheckAll(isChecked))
    }
}