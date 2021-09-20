package com.example.a2021edcanvactionproject.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.time.LocalTime

data class Time(
    var hour : Int,
    val mine : Int,
    var second : Int,
)

data class Todo(
    val kind : String,
    val st : Boolean,
    val set : Int = 0,
    val setCount : Int = 0,
    val time : Time = Time(0, 0, 0),

    val alarm : Boolean = false,
    val alarmTime : LocalTime? = null,
    val completion : Boolean = false
)