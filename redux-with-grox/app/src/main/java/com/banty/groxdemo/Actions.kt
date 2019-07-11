package com.banty.groxdemo

import com.groupon.grox.Action

sealed class Actions {
    class DecrementAction : Action<CounterState> {
        override fun newState(oldState: CounterState?): CounterState = CounterState(oldState!!.value - 1)
    }

    class IncrementAction : Action<CounterState> {
        override fun newState(oldState: CounterState?) = CounterState(oldState!!.value + 1)
    }
}