package com.example.a2021edcanvactionproject.Activity.Run

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.ActivityRunBinding
import com.example.a2021edcanvactionproject.Model.Todo

class RunActivity : AppCompatActivity() {
    lateinit var binding : ActivityRunBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_run)

        val todo = intent.getSerializableExtra("todoData") as Todo

        binding.txtRunKind.text = todo.kind
    }
}