package com.example.a2021edcanvactionproject.Fragment.Main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.Activity.Splash.DB
import com.example.a2021edcanvactionproject.Model.Todo
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.FragmentMainBinding
import kotlin.math.roundToInt

class MainFragment : Fragment() {
    lateinit var binding : FragmentMainBinding
    private lateinit var todoData : MutableList<Todo>
    private lateinit var adapter : TodoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        val root = binding.root

        initClickEvent()

        todoData = DB.getDataDay()
        adapter = TodoListAdapter(todoData, true)

        val complete = todoData.filter { it.completion } as MutableList<Todo>
        val todoCompletePresent = (complete.size.toFloat() / todoData.size.toFloat() * 100).roundToInt()


        binding.title = if(todoCompletePresent == 100) "오늘 계획 ${todoData.size}개 모두 완료!\n수고하셨습니다!"
                        else "오늘 계획 ${todoData.size}개\n${complete.size}개 실천중 (${todoCompletePresent}%)"
        binding.recycleMainTodoList.adapter = adapter
        binding.recycleMainTodoList.addItemDecoration(TodoListDecoration(100))


        return root
    }

    private fun initClickEvent(){
        with(binding){
//            btnMainLog.setOnClickListener {
//                Log.d("todoLog", DB.getData().toString())
//                Log.d("todoLog todoData", DB.getDataDay().toString())
//
//                Log.d("todoLog kinds", DB.getKinds().toString())
//            }
//
//            btnMainClear.setOnClickListener {
//                DB.clear()
//            }
        }
    }
}