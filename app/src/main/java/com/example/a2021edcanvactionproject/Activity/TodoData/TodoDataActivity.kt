package com.example.a2021edcanvactionproject.Activity.TodoData

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.ActivityTodoDataBinding
import com.example.a2021edcanvactionproject.model.Todo
import com.example.a2021edcanvactionproject.model.gson

class TodoDataActivity : AppCompatActivity() {
    lateinit var binding : ActivityTodoDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo_data)

        val todoData = gson.fromJson(intent.getStringExtra("todoData"), Todo::class.java)
        Log.d("todoData", todoData.toString())
        Log.d("todoData Kind", todoData.kind)

        binding.kind = todoData.kind
    }
}