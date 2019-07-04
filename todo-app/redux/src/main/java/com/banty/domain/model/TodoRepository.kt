package com.banty.domain.model

class TodoRepository {
    companion object {
        val todos = listOf(
            Todo("Redux"),
            Todo("MVP"),
            Todo("MVVM"),
            Todo("MVI")
        )
    }

    fun generateFakeData(): List<Todo> {
        return listOf(
            Todo("Redux"),
            Todo("MVP"),
            Todo("MVVM"),
            Todo("MVI")
        )
    }
}