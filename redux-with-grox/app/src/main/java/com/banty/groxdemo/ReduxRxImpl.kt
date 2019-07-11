package com.banty.groxdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.groupon.grox.Store
import com.groupon.grox.rxjava2.RxStores.states
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RxPresenter.MyView {
    private lateinit var rxPresenter: RxPresenter

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rxPresenter = RxPresenter()

        compositeDisposable.add(
            states(rxPresenter.getStore())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { updateUI(it) }
        )

        incrementButton.setOnClickListener {
            rxPresenter.increment()
        }

        decrementButton.setOnClickListener {
            rxPresenter.decrement()
        }
    }

    override fun updateUI(counterState: CounterState) {
        output.text = counterState.value.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}

class RxPresenter {
    private val store = Store<CounterState>(CounterState(0))

    fun increment() = store.dispatch(Actions.IncrementAction())
    fun decrement() = store.dispatch(Actions.DecrementAction())

    fun getStore() = store

    interface MyView : Store.StateChangeListener<CounterState> {
        override fun onStateChanged(newCounterState: CounterState?) {
            newCounterState?.let { updateUI(it) }
        }

        fun updateUI(counterState: CounterState)
    }
}


