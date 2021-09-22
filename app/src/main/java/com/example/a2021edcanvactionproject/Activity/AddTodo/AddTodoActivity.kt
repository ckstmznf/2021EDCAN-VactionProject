package com.example.a2021edcanvactionproject.Activity.AddTodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.Activity.Main.MainActivity
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.ActivityAddTodoBinding
import com.example.a2021edcanvactionproject.model.*

//import com.example.a2021edcanvactionproject.model.addTodoData

class AddTodoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddTodoBinding
    var choiceSetTime = true //시간, 세트 선택, true면 시간, false면 세트

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_todo)

        ActivityInputEvent()
    }

    private fun ActivityInputEvent() = with(binding){
        btnAddTodoAddTodo.setOnClickListener {
            saveTodoData()
        }


        btnAddTodoSetTime.setOnClickListener {
            linearAddTodoInputTime.visibility = View.VISIBLE
            edtAddTodoSet.visibility = View.INVISIBLE
            choiceSetTime = true
        }

        btnAddTodoSetSet.setOnClickListener {
            linearAddTodoInputTime.visibility = View.INVISIBLE
            edtAddTodoSet.visibility = View.VISIBLE
            choiceSetTime = false
        }
    }

    private fun saveTodoData() {
        var kind : String
        var set : String
        var timeM : String
        var timeS : String

        with(binding){
            kind = spinAddTodoChoice.selectedItem.toString();
            set = edtAddTodoSet.text.toString()// as Int
            timeM = edtAddTodoTimeM.text.toString()
            timeS = edtAddTodoTimeS.text.toString()
        }

        if(kind == "운동 종류"){
            Toast.makeText(this@AddTodoActivity, "운동 종류를 선택해주세요.", Toast.LENGTH_LONG).show()
            return
        }   else if(!choiceSetTime && set.isEmpty()){
            binding.edtAddTodoSet.error = "세트를 입력하세요."
            return
        }   else if(choiceSetTime && timeM == ""){
            binding.edtAddTodoTimeM.error = "운동할 시간을 입력하세요."
            return
        }   else if(choiceSetTime && timeS == ""){
            binding.edtAddTodoTimeS.error = "운동할 시간을 입력하세요."
            return
        }


        val todo = Todo(
            kind = kind,
            st = choiceSetTime
        )

        if(choiceSetTime){
            todo.time = Time(timeM.toInt(), timeS.toInt())
        }   else{
            todo.set = set.toInt()
        }

        addTodoData(todoData = todo)


        val intent = Intent(this@AddTodoActivity, MainActivity::class.java)
        intent.putExtra("todoData", gson.toJson(todo))
        setResult(RESULT_OK, intent)
        finish()
    }
}