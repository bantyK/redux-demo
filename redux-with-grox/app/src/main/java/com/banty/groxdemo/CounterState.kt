package com.banty.groxdemo

data class CounterState(val value: Int = 0) : Cloneable {
    public override fun clone(): CounterState = CounterState(value)
}
