package com.example.a2021edcanvactionproject.Model.DB

import com.example.a2021edcanvactionproject.Model.Todo
import org.json.JSONObject
import java.time.LocalDate

interface DB {
    fun getData(): JSONObject
    fun addDay(date : LocalDate = LocalDate.now())
    fun getDataDay(date : LocalDate = LocalDate.now()): MutableList<Todo>
    fun addTodoData(date : LocalDate = LocalDate.now(), todoData : Todo)
}