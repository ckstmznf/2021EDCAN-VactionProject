package com.example.a2021edcanvactionproject.Activity.TodoDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.ActivityTodoDetailBinding
import com.example.a2021edcanvactionproject.model.Todo

class TodoDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityTodoDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo_detail)

        val todo = intent.getSerializableExtra("todoData") as Todo
//        Log.d("todoData Kind", todo.kind)
        binding.kind = todo.kind
    }
}