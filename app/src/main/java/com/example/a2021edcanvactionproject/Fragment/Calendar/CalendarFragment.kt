package com.example.a2021edcanvactionproject.Fragment.Calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.Activity.Splash.DB
import com.example.a2021edcanvactionproject.Fragment.Main.TodoListAdapter
import com.example.a2021edcanvactionproject.Fragment.Main.TodoListDecoration
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.FragmentCalendarBinding
import java.time.LocalDate

class CalendarFragment : Fragment() {
    lateinit var binding : FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        val root = binding.root

        showList(LocalDate.now())

        binding.calendarCalendar.setOnDateChangeListener{ view, year, month, day ->
            showList(LocalDate.of(year, month + 1, day))
        }
        binding.recycleCalendarTodoList.addItemDecoration(TodoListDecoration(70))

        return root
    }

    fun showList(date : LocalDate){
        val todoList = DB.getDataDay(date)
        with(binding){
            if(todoList.size == 0){
                recycleCalendarTodoList.visibility = View.INVISIBLE
                txtCalendarNullMSG.visibility = View.VISIBLE
                txtCalendarNullMSG.text = "${date.monthValue}월 ${date.dayOfMonth}일은 계획이 없습니다."
            }   else{
                recycleCalendarTodoList.visibility = View.VISIBLE
                txtCalendarNullMSG.visibility = View.INVISIBLE
                recycleCalendarTodoList.adapter = TodoListAdapter(todoList, LocalDate.now().isEqual(date))
            }
        }
    }
}