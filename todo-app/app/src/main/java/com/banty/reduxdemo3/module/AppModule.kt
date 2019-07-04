package com.banty.reduxdemo3.module

import com.banty.domain.model.TodoRepository
import com.banty.domain.redux.Redux
import com.banty.reduxdemo3.MainPresenter
import com.banty.reduxdemo3.MainView
import org.koin.dsl.module

val appModule = module {
    single { TodoRepository() }
}

val reduxModule = module {
    single { Redux(get()) }
}

val presenterModule = module {
    factory { (view: MainView) -> MainPresenter(view) }
}