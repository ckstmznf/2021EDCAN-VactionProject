package com.example.a2021edcanvactionproject.Activity.Run

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.Activity.Completion.CompletionActivity
import com.example.a2021edcanvactionproject.Activity.Main.MainActivity
import com.example.a2021edcanvactionproject.Activity.Rest.RestActivity
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.ActivityRunBinding
import com.example.a2021edcanvactionproject.Model.Todo

class RunActivity : AppCompatActivity() {
    lateinit var binding : ActivityRunBinding
    var nowCount : Int = -1
    lateinit var todo : Todo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_run)

        todo = intent.getSerializableExtra("todoData") as Todo
        nowCount = intent.getIntExtra("nowCount", 0)

        initClickEvent()

        with(binding){
            txtRunKind.text = todo.kind
            txtRunRunTime.text = "$nowCount/${todo.set}"
            progressRun.max = todo.set
            progressRun.progress = nowCount
        }
    }

    fun initClickEvent() = with(binding){
        btnRunSetCompletion.setOnClickListener {
            if(nowCount + 1 == todo.set){
                val intent = Intent(this@RunActivity, CompletionActivity::class.java)
                startActivity(intent)
                finish()
            }   else{
                val intent = Intent(this@RunActivity, RestActivity::class.java)
                intent.putExtra("nowCount", nowCount + 1)
                intent.putExtra("todo", todo)
                startActivity(intent)
                finish()
            }
        }
    }
}