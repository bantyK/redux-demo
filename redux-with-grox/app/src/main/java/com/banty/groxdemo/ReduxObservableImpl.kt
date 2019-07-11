package com.banty.groxdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.groupon.grox.Store
import kotlinx.android.synthetic.main.activity_main.*

class ReduxObservableImpl : AppCompatActivity(), ObservablePresenter.MyView {
    private lateinit var presenter: ObservablePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ObservablePresenter(this)
        incrementButton.setOnClickListener {
            presenter.increment()
        }

        decrementButton.setOnClickListener {
            presenter.decrement()
        }
    }

    override fun updateUI(state: CounterState) {
        output.text = state.value.toString()
    }
}

/// Separate class
class ObservablePresenter(private val view: MyView) {
    private val store = Store(CounterState(0))

    init {
        store.subscribe(this::view.get())
    }

    fun increment() = store.dispatch(Actions.IncrementAction())
    fun decrement() = store.dispatch(Actions.DecrementAction())

    interface MyView : Store.StateChangeListener<CounterState> {
        override fun onStateChanged(newState: CounterState?) {
            newState?.let { updateUI(it) }
        }

        fun updateUI(state: CounterState)
    }
}

