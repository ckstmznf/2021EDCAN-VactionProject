package com.example.a2021edcanvactionproject.model

import android.text.Html
import java.io.Serializable
import java.time.LocalTime
import kotlin.reflect.KProperty



data class Todo(
    val kind : String,
    val st : Boolean, // true이면 시간으로 설정, false이면 세드로 설정

    var set : Int = 0,
    var setCount : Int = 0,

    var time : Time = Time(0, 0),

    val alarm : Boolean = false,
    val alarmTime : LocalTime? = null,
    val completion : Boolean = false
) : Serializable{
    val setTotalCount : Int
        get() {
            return set * setCount
        }
}