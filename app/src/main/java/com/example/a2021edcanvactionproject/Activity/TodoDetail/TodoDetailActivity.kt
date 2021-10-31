package com.example.a2021edcanvactionproject.Activity.TodoDetail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.Activity.Run.RunActivity
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.ActivityTodoDetailBinding
import com.example.a2021edcanvactionproject.Model.Todo

class TodoDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityTodoDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo_detail)

        val todo = intent.getSerializableExtra("todoData") as Todo
        Log.d("todoData", todo.toString())
        val toDay = intent.getBooleanExtra("toDay", true)

        with(binding){
            kind = todo.kind
            if(todo.st){
                //시간으로 설정했을때
                txtTodoDetailTimeM.text = "${todo.time.mine}분"
                txtTodoDetailTimeS.text = "${todo.time.second}초"
            }   else{
                //세트로 설정했을때
                linearTodoDetailSet.visibility = View.VISIBLE
                linearTodoDetailTime.visibility = View.INVISIBLE

                txtTodoDetailSet.text = "${todo.set}세트"
                txtTodoDetailSetCount.text = "${todo.setCount}회"
                txtTodoDetailSetTotalCount.text = "${todo.setTotalCount}회"
            }

            if(toDay){
                btnTodoDetailTodoStart.visibility = View.VISIBLE
                btnTodoDetailTodoStart.setOnClickListener {
                    val intent = Intent(this@TodoDetailActivity, RunActivity::class.java)
                    intent.putExtra("todoData", todo)
                    startActivity(intent)
                }
            }   else{
                btnTodoDetailTodoStart.visibility = View.INVISIBLE
            }
        }
    }
}