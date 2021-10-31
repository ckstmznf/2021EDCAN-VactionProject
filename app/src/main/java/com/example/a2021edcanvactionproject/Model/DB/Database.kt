package com.example.a2021edcanvactionproject.Model.DB

import android.content.SharedPreferences
import android.util.Log
import com.example.a2021edcanvactionproject.Model.Todo
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate

class Database : DB{
    lateinit var pref : SharedPreferences
    lateinit var editor : SharedPreferences.Editor
    val gson = GsonBuilder().create()

    override fun getData(): JSONObject {
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

    override fun addDay(date: LocalDate) {
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

    override fun getDataDay(date: LocalDate): MutableList<Todo> {
        addDay(date)
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

    override fun addTodoData(date: LocalDate, todoData: Todo) {
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

    override fun todoCompleat(todo: Todo) {
        val toDayTodo = getDataDay()
        val resultOneDay = toDayTodo.filter{
            it != todo
        } as MutableList<Todo>

        todo.completion = true
        resultOneDay.add(todo)

        val resultOneDay_json = resultOneDay.map{
            gson.toJson(it)
        }

        val now = LocalDate.now()
        val allDayTodo = getData()

        allDayTodo.getJSONObject(now.year.toString())
            .getJSONObject(now.monthValue.toString())
            .put(now.dayOfMonth.toString(), JSONArray(resultOneDay_json))

        editor.putString("data", allDayTodo.toString())
        editor.apply()
    }

    override fun getKinds(): MutableList<String> {
        val kinds = JSONArray(pref.getString("kinds", "[]"))
        val result = mutableListOf<String>()

        for (i in 0 until kinds.length()){
            result.add(kinds[i].toString())
        }

        return result
    }

    override fun addKind(kind: String) {
        val kinds = getKinds()
        kinds.add(kind)

        editor.putString("kinds", gson.toJson(kinds))
        editor.apply()
    }

    override fun clear() {
        editor.clear()
        editor.apply()
    }
}