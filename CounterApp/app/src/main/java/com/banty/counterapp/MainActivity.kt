package com.banty.counterapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private val compositeDisposable = CompositeDisposable()

    private lateinit var presenter: MainPresenter

    override fun bind(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun updateCount(count: Int) {
        output.text = count.toString()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        presenter.init()


        incrementButton.setOnClickListener {
            presenter.increment()
        }

        decrementButton.setOnClickListener {
            presenter.decrement()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}


