package com.example.a2021edcanvactionproject.Fragment.Main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.Activity.Splash.DB
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    lateinit var binding : FragmentMainBinding
    private val todoData = DB.getDataDay()
    private val adapter = TodoListAdapter(todoData, true)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        val root = binding.root

        initClickEvent()

        binding.title = "오늘 계획 ${todoData.size}개"
        binding.recycleMainTodoList.adapter = adapter
        binding.recycleMainTodoList.addItemDecoration(TodoListDecoration(100))


        return root
    }

    private fun initClickEvent(){
        with(binding){
            btnMainLog.setOnClickListener {
                Log.d("todoLog", DB.getData().toString())
                Log.d("todoLog todoData", DB.getDataDay().toString())

                Log.d("todoLog kinds", DB.getKinds().toString())
            }

            btnMainClear.setOnClickListener {
                DB.clear()
            }
        }
    }
}