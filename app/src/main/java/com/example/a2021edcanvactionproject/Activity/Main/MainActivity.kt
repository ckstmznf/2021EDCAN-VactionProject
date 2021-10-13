package com.example.a2021edcanvactionproject.Activity.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.Activity.AddTodo.AddTodoActivity
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.ActivityMainBinding
import com.example.a2021edcanvactionproject.model.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val todoData = getDataDay()
    private val adapter = TodoListAdapter(todoData)

    private val todoAddResultCallback = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){
            val todo = it.data!!.getSerializableExtra("todoData") as Todo
            addTodoData(todoData = todo)
            adapter.items.add(todo)
            adapter.notifyDataSetChanged()
            binding.title = "오늘 계획 ${todoData.size}개"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        ActivityClickEvent()
        binding.title = "오늘 계획 ${todoData.size}개"



        binding.recycleMainTodoList.adapter = adapter
        binding.recycleMainTodoList.addItemDecoration(TodoListDecoration(100))
    }

    private fun ActivityClickEvent(){
        with(binding){
            btnMainAddTodo.setOnClickListener {
                val intent = Intent(this@MainActivity, AddTodoActivity::class.java)
                todoAddResultCallback.launch(intent)
            }

            btnMainReset.setOnClickListener {
                editor.clear()
                editor.apply()

                val intent = Intent(this@MainActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            btnMainLog.setOnClickListener {
                Log.d("todoLog", getData().toString())
                Log.d("todoLog todoData", todoData.toString())
                Log.d("todoLog adapter", adapter.items.toString())
            }
        }


    }
}