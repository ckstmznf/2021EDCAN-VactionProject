package com.example.a2021edcanvactionproject.Activity.Splash

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import com.example.a2021edcanvactionproject.Activity.Main.MainActivity
import com.example.a2021edcanvactionproject.Model.DB.Database
import com.example.a2021edcanvactionproject.R

lateinit var DB : Database

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        DB = Database()

        DB.pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
        DB.editor = DB.pref.edit()


//        DB.clear()

        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(this, MainActivity::class.java) //화면 전환
            startActivity(intent)
            finish()
        }, 1500) //딜레이 타임 조절

    }
}