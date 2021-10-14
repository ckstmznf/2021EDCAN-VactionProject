package com.example.a2021edcanvactionproject.Activity.Splash

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import com.example.a2021edcanvactionproject.Activity.Main.Main2Activity
import com.example.a2021edcanvactionproject.Activity.MainActivity
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.model.editor
import com.example.a2021edcanvactionproject.model.pref


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        todoDB = TodoDatabase.getInstance(applicationContext)

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
        editor = pref.edit()

        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(this, MainActivity::class.java) //화면 전환
            startActivity(intent)
            finish()
        }, 1500) //딜레이 타임 조절

    }
}