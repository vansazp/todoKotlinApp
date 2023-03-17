package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    // list of todo items
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    // view holder for the todo items using the item.todo.xml
    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    // creating the view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        // we return a view holder with a layout that will inflate the elements
        // it gets a reference to the view
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)

        // update the ui
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo -> todo.isChecked }
        // notify something changed in the list to update the ui
        // this is not ideal now, check warning
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    // assign/set the data of our todos list to the text and checkBox elements
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            val tvTodoTitle = findViewById<TextView>(R.id.tvTodoTitle)
            val cbDone = findViewById<CheckBox>(R.id.cbDone)

            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)

            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    // return the number of items for the todo list
    override fun getItemCount(): Int {
        return todos.size
    }
}