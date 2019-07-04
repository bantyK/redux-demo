package com.banty.reduxdemo3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.banty.domain.model.Todo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_todo.view.*

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter(this)

    private val compositeDisposable = CompositeDisposable()

    private val adapter = TodoAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.onCompletedChanged = { todo -> Log.d("Todo#", todo.text) }
        adapter.onEditCallBack = { todo -> showEditDialog(todo) }
        presenter.initialise()

        checkboxAll.setOnClickListener {
            presenter.checkAll(checkboxAll.isChecked)
        }
    }

    private fun showEditDialog(todo: Todo) {
        AlertDialog.Builder(this).apply {
            val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.dialog_add_todo, null)
            setView(view)
            val editTodo = view.editTodo
            editTodo.setText(todo.text)
            editTodo.setSelection(todo.text.length)
            setTitle(R.string.add_todo)
            setPositiveButton(R.string.update) { dialog, _ ->
                if (editTodo.text.isNullOrEmpty()) {
                    Toast.makeText(this@MainActivity, R.string.add_todo, Toast.LENGTH_LONG).show()
                } else {
//                    presenter.updateTodo(todo.copy(text = editTodo.text.toString()))
                    dialog.dismiss()
                }
            }
            setNeutralButton(R.string.delete) { dialog, _ ->
                presenter.deleteTodo(todo.id)
                dialog.dismiss()
            }
            setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
        }.show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_todo, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.add -> {
                AlertDialog.Builder(this).apply {
                    val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.dialog_add_todo, null)
                    setView(view)
                    val editTodo = view.editTodo
                    setTitle(R.string.add_todo)
                    setPositiveButton(R.string.add) { dialog, _ ->
                        if (editTodo.text.isNullOrEmpty()) {
                            Toast.makeText(this@MainActivity, R.string.add_todo, Toast.LENGTH_LONG).show()
                        } else {
                            presenter.addTodo(editTodo.text.toString())
                            dialog.dismiss()
                        }
                    }
                    setNegativeButton(R.string.cancel) { dialog, _ ->
                        dialog.dismiss()
                    }
                }.show()
            }
            R.id.generate -> {
                presenter.generateData()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    override fun bind(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun updateTodo(todos: List<Todo>) {
        adapter.submitList(todos)
    }

}
