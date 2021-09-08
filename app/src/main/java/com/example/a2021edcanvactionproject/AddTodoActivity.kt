package com.example.a2021edcanvactionproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.databinding.ActivityAddTodoBinding
import com.example.a2021edcanvactionproject.model.Todo
import com.example.a2021edcanvactionproject.model.addTodoData

//import com.example.a2021edcanvactionproject.model.addTodoData

class AddTodoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_todo)


        binding.btnAddTodoAddTodo.setOnClickListener {
            val kind = binding.spinAddTodoChoice.selectedItem.toString();
            val set = Integer.parseInt(binding.edtAddTodoSet.text.toString())// as Int

            val todo = Todo(kind, true, set)


            todoData.add(todo)
            addTodoData(todoData = todo)

            finish()
        }

    }
}