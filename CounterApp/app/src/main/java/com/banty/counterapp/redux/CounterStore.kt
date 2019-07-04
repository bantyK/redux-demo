package com.banty.counterapp.redux

import com.banty.counterapp.redux.reducers.DecrementReducer
import com.banty.counterapp.redux.reducers.IncrementReducer
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class CounterStore {

    private val dispatcher = PublishSubject.create<CounterAction>()

    private var currentState: CounterState = CounterState()

    fun actionDispatcher(): Observable<CounterState> {
        return dispatcher.map { action ->
            val reducer = when (action) {
                is CounterAction.IncrementAction -> IncrementReducer(action)
                is CounterAction.DecrementAction -> DecrementReducer(action)
            }

            reducer.newState(currentState.clone())
        }.doAfterNext { newState -> currentState = newState.clone() }
    }

    fun dispatcher(counterAction: CounterAction) {
        dispatcher.onNext(counterAction)
    }

}