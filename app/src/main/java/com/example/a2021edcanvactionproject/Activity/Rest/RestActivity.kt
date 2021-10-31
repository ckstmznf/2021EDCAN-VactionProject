package com.example.a2021edcanvactionproject.Activity.Rest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.Activity.Run.RunActivity
import com.example.a2021edcanvactionproject.Model.Todo
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.ActivityRestBinding
import java.util.*

class RestActivity : AppCompatActivity() {
    lateinit var binding : ActivityRestBinding
    lateinit var todo : Todo

    var progressMax = -1
    var nowCount = -1

    var timer = Timer()
    var timerTask: TimerTask = object : TimerTask() {
        override fun run() = with(binding){
            if(timeS != 0){
                timeS -= 1
            }   else if(timeM != 0){
                timeS = 60
                timeS--
                timeM--
            }

            progressRest.progress = progressMax - (timeM * 60 + timeS)

            if(timeS == 0 && timeM == 0){
                timer.cancel()
                gotoContinue_()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rest)
        todo = intent.getSerializableExtra("todo") as Todo
        nowCount = intent.getIntExtra("nowCount", -1)


        with(binding){
            txtRestKind.text = todo.kind

            timeM = 0
            timeS = 10

            progressMax = timeM * 60 + timeS
            progressRest.max = progressMax
        }

        initClickEvent()

        timer.schedule(timerTask, 0, 1000);

    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    fun initClickEvent() = with(binding){
        btnRestContinue.setOnClickListener {
            gotoContinue_()
        }
    }

    fun gotoContinue_(){
        val intent = Intent(this@RestActivity, RunActivity::class.java)
        intent.putExtra("nowCount", nowCount)
        intent.putExtra("todoData", todo)
        startActivity(intent)
        finish()
    }
}