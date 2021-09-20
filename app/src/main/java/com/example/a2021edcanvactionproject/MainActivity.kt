package com.example.a2021edcanvactionproject

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.databinding.ActivityMainBinding
import com.example.a2021edcanvactionproject.model.Todo
import com.example.a2021edcanvactionproject.model.getData
import com.example.a2021edcanvactionproject.model.getDataDay
import com.google.gson.GsonBuilder


lateinit var pref : SharedPreferences
lateinit var editor : SharedPreferences.Editor
val gson = GsonBuilder().create()


lateinit var todoData : MutableList<Todo>

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
        editor = pref.edit()


        binding.btnMainLog.setOnClickListener {
            Log.d("todo", getData().toString())
        }

        binding.btnMainAddTodo.setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)
        }


        todoData = getDataDay()
        Log.d("todoData", todoData.toString())
        var adapter = TodoListAdapter(todoData)

//        if (todoData.size > 0) {    //데이타가 추가, 수정되었을때
//            adapter.notifyDataSetChanged();
//        } else {    //뷰에 표시될 데이타가 없을때
//            adapter.notifyDataSetInvalidated();
//        }


        binding.listViewMainTodolist.adapter = adapter
    }
}