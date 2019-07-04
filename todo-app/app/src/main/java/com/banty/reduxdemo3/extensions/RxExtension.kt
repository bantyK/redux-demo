package com.banty.reduxdemo3.extensions

import com.banty.reduxdemo3.MainView
import io.reactivex.disposables.Disposable

fun Disposable.bind(view: MainView) {
    view.bind(this)
}