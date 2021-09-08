package com.example.a2021edcanvactionproject.model

import android.util.Log
import com.example.a2021edcanvactionproject.editor
import com.example.a2021edcanvactionproject.gson
import com.example.a2021edcanvactionproject.pref
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate
import java.time.LocalTime
import kotlin.random.Random

fun getData(): JSONObject {
    val date = LocalDate.now()

    val data_json = pref.getString("data", """
        {
           "${date.year}" : {
            "${date.monthValue}" : {
                "${date.dayOfMonth}" : []    
            }
           }
        }
    """.trimIndent())

    return JSONObject(data_json)
}

fun addDay(date : LocalDate = LocalDate.now()) {
    var data = getData()

    if(!data.has(date.year.toString()))
        data.put(date.year.toString(), JSONObject())
    val data_year = data.getJSONObject(date.year.toString())

    if(!data_year.has(date.monthValue.toString()))
        data_year.put(date.monthValue.toString(), JSONObject())
    val data_month = data_year.getJSONObject(date.monthValue.toString())

    if(!data_month.has(date.dayOfMonth.toString()))
        data_month.put(date.dayOfMonth.toString(), JSONArray())


    editor.putString("data", data.toString())
    editor.apply()
}

fun getDataDay(date : LocalDate = LocalDate.now()): MutableList<Todo> {
    addDay()
    var data = getData()
        .getJSONObject(date.year.toString())
        .getJSONObject(date.monthValue.toString())
        .getJSONArray(date.dayOfMonth.toString())

    val result = mutableListOf<Todo>()

    for(i in 0 until data.length()){
        result.add(
            gson.fromJson(data[i].toString(), Todo::class.java)
        )
    }

    return result
}

fun addTodoData(date : LocalDate = LocalDate.now(), todoData : Todo){
    val todoData_json = gson.toJson(todoData)

    addDay(date)

    val data_json = getData()
    data_json.getJSONObject(date.year.toString())
        .getJSONObject(date.monthValue.toString())
        .getJSONArray(date.dayOfMonth.toString())
        .put(todoData_json)

    editor.putString("data", data_json.toString())
    editor.apply()
}