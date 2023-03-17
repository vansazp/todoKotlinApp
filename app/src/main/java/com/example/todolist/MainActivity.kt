package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    // declare the todo adapter
    private lateinit var todoAdapter: TodoAdapter

    // this is called when the app is launched
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // assign the todo adapter
        todoAdapter = TodoAdapter(mutableListOf())

        // declare the recycler view
        val rvTodoItems = findViewById<RecyclerView>(R.id.rvTodoItems)

        // assign the adapter to the recycler view's adapter
        rvTodoItems.adapter = todoAdapter

        // define how the items are arranged on our list
        // layout manager to display the list. using Linear layout
        // there are also others like Grid layout manager
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        // declare view elements from activity
        val etTodoTitle = findViewById<EditText>(R.id.etTodoTitle)
        val btnAddTodo = findViewById<Button>(R.id.btnAddTodo)
        val btnDeleteDoneTodos = findViewById<Button>(R.id.btnDeleteDoneTodos)

        // add button listener
        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }

        // delete button listener
        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }

    }
}