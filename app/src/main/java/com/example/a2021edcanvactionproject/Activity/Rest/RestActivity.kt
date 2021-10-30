package com.example.a2021edcanvactionproject.Activity.Rest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.Activity.Run.RunActivity
import com.example.a2021edcanvactionproject.Model.Todo
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.ActivityRestBinding

class RestActivity : AppCompatActivity() {
    lateinit var binding : ActivityRestBinding
    lateinit var todo : Todo
    var nowCount = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_rest)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rest)
        todo = intent.getSerializableExtra("todo") as Todo
        nowCount = intent.getIntExtra("nowCount", -1)

        with(binding){
            txtRestKind.text = todo.kind
            txtRestNowCount.text = "현재 ${nowCount}개 완료"
        }

        initClickEvent()
    }

    fun initClickEvent() = with(binding){
        btnRestContinue.setOnClickListener {
            val intent = Intent(this@RestActivity, RunActivity::class.java)
            intent.putExtra("nowCount", nowCount)
            intent.putExtra("todoData", todo)
            startActivity(intent)
            finish()
        }
    }
}