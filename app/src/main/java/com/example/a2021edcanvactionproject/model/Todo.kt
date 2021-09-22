package com.example.a2021edcanvactionproject.model

import java.io.Serializable
import java.time.LocalTime

data class Time(
    val mine: Int,
    val second: Int,
)

data class Todo(
    val kind : String,
    val st : Boolean,

    var set : Int = 0,
    val setCount : Int = 0,

    var time : Time = Time(0, 0),

    val alarm : Boolean = false,
    val alarmTime : LocalTime? = null,
    val completion : Boolean = false
)