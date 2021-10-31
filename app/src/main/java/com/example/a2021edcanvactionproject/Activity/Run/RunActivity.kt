package com.example.a2021edcanvactionproject.Activity.Run

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.Activity.Main.MainActivity
import com.example.a2021edcanvactionproject.Activity.Rest.RestActivity
import com.example.a2021edcanvactionproject.Activity.Splash.DB
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.ActivityRunBinding
import com.example.a2021edcanvactionproject.Model.Todo
import java.util.*

class RunActivity : AppCompatActivity() {
    lateinit var binding : ActivityRunBinding
    var nowCount : Int = -1
    lateinit var todo : Todo
    var timeMax = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_run)
        todo = intent.getSerializableExtra("todoData") as Todo

        binding.txtRunKind.text = todo.kind


        if(todo.st){
            //시간으로 설정한 경우

            with(binding){
                timeM = todo.time.mine
                timeS = todo.time.second

                timeMax = timeM * 60 + timeS

                progressRun.max = timeMax
                btnRunSetCompletion.visibility = View.INVISIBLE
            }

            timer.schedule(timerTask, 0, 1000)
        }   else{
            //세트로 설정한 경우
            nowCount = intent.getIntExtra("nowCount", 0)

            initSetClickEvent()

            with(binding){
                txtRunRunTime.visibility = View.INVISIBLE
                txtRunRunProgress.visibility = View.VISIBLE

                txtRunRunProgress.text = "$nowCount/${todo.set}"
                progressRun.max = todo.set
                progressRun.progress = nowCount
            }   
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if(todo.st){
            timer.cancel()
        }
    }

    val timer = Timer()
    val timerTask : TimerTask = object : TimerTask() {
        override fun run() = with(binding){
            if(timeS != 0){
                timeS -= 1
            }   else if(timeM != 0){
                timeS = 60
                timeS--
                timeM--
            }

            progressRun.progress = timeMax - (timeM * 60 + timeS)

            if(timeM == 0 && timeS == 0){
                timer.cancel()
                gotoMain()
            }
        }
    }

    fun initSetClickEvent() = with(binding){
        btnRunSetCompletion.setOnClickListener {
            if(nowCount + 1 == todo.set){
                gotoMain()
            }   else{
                val intent = Intent(this@RunActivity, RestActivity::class.java)
                intent.putExtra("nowCount", nowCount + 1)
                intent.putExtra("todo", todo)
                startActivity(intent)
                finish()
            }
        }
    }

    fun gotoMain(){
        DB.todoCompleat(todo)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("todo", todo)
        startActivity(intent)
        finish()
    }
}