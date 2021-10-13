package com.example.a2021edcanvactionproject.DB

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a2021edcanvactionproject.model.Todo

@Entity(tableName = "todo_database")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val _id : Int,

    @ColumnInfo(name = "todo")
    @Embedded
    val todo : Todo,

    @ColumnInfo(name = "year")
    val year : Int,

    @ColumnInfo(name = "month")
    val month : Int,

    @ColumnInfo(name = "day")
    val day : Int,
)
