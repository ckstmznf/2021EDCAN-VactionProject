package com.example.a2021edcanvactionproject.Activity.TodoDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.ActivityTodoDetailBinding
import com.example.a2021edcanvactionproject.model.Todo

class  TodoDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityTodoDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo_detail)

        val todo = intent.getSerializableExtra("todoData") as Todo

        with(binding){
            kind = todo.kind
            if(todo.st){
                //시간으로 설정했을때
                txtTodoDetailTimeM.text = "${todo.time.mine.toString()}분"
                txtTodoDetailTimeS.text = "${todo.time.second.toString()}초"
            }   else{
                linearTodoDetailSet.visibility = View.VISIBLE
                linearTodoDetailTime.visibility = View.INVISIBLE

                txtTodoDetailSet.text = "${todo.set.toString()}세트"
                txtTodoDetailSetCount.text = "${todo.setCount.toString()}회"
                txtTodoDetailSetTotalCount.text = "${todo.setTotalCount.toString()}회"
            }
        }

    }
}