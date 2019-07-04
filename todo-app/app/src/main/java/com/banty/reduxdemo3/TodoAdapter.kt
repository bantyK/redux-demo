package com.banty.reduxdemo3


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.banty.domain.model.Todo

class TodoAdapter : ListAdapter<Todo, TodoViewHolder>(TodoDiffCallBack()) {

    var onCompletedChanged: (todo: Todo) -> Unit = { }
    var onEditCallBack: (todo: Todo) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        getItem(holder.adapterPosition).apply {
            holder.text.text = text
            holder.checkbox.isChecked = completed
            holder.checkbox.setOnClickListener {
                onCompletedChanged.invoke(copy(completed = holder.checkbox.isChecked))
            }
            holder.itemView.setOnClickListener {
                onEditCallBack.invoke(this)
            }
        }
    }

}

class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val text: AppCompatTextView = view.findViewById(R.id.text)
    val checkbox: AppCompatCheckBox = view.findViewById(R.id.checkbox)
}

class TodoDiffCallBack : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}