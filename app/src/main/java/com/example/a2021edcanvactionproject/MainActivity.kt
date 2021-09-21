package com.example.a2021edcanvactionproject

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.databinding.ActivityMainBinding
import com.example.a2021edcanvactionproject.model.*
import com.google.gson.GsonBuilder




class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val todoData = getDataDay()
    private val adapter = TodoListAdapter(todoData)

    private val todoAddResultCallback = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){
            val todoJson = it.data!!.getStringExtra("todoData").toString()
            Log.d("todo get", todoJson)
            val todo = gson.fromJson(todoJson, Todo::class.java)
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