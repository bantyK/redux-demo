package com.banty.counterapp.redux

data class CounterState(val value: Int = 0) : Cloneable {

    public override fun clone(): CounterState {
        return CounterState(value)
    }
}
