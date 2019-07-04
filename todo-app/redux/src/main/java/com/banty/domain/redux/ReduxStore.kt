package com.banty.domain.redux

import com.banty.domain.model.TodoRepository
import com.banty.domain.redux.reducers.AddTodoReducer
import com.banty.domain.redux.reducers.CheckAllReducers
import com.banty.domain.redux.reducers.DeleteTodoReducer
import com.banty.domain.redux.reducers.GenerateDataReducer
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ReduxStore(private val todoRepository: TodoRepository) {

    private val actionDispatcher = PublishSubject.create<TodoAction>()

    private var currentState: TodoState = TodoState()

    fun actionDispatcher(): Observable<TodoState> {
        return actionDispatcher.map { action ->
            val reducer: TodoReducer = when (action) {
                is TodoAction.AddTodo -> AddTodoReducer(action)
                is TodoAction.DeleteTodo -> DeleteTodoReducer(action)
                is TodoAction.GenerateData -> GenerateDataReducer(todoRepository)
                is TodoAction.CheckAll -> CheckAllReducers(action)
            }

            reducer.newState(currentState.clone())
        }.doAfterNext { newState -> currentState = newState.clone() }
    }

    fun dispatcher(todoAction: TodoAction) {
        actionDispatcher.onNext(todoAction)
    }

}