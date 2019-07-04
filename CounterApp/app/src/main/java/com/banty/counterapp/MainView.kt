package com.banty.counterapp

import io.reactivex.disposables.Disposable

interface MainView {
    fun bind(disposable: Disposable)

    fun updateCount(count: Int)
}
