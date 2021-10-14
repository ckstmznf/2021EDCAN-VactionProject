package com.example.a2021edcanvactionproject.Fragment.Main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.Activity.AddTodo.AddTodoActivity
import com.example.a2021edcanvactionproject.Activity.Main.Main2Activity
import com.example.a2021edcanvactionproject.Activity.Main.TodoListAdapter
import com.example.a2021edcanvactionproject.Activity.Main.TodoListDecoration
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.FragmentMainBinding
import com.example.a2021edcanvactionproject.model.*

class MainFragment : Fragment() {
    lateinit var binding : FragmentMainBinding
    private val todoData = getDataDay()
    private val adapter = TodoListAdapter(todoData)

    private val todoAddResultCallback = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == AppCompatActivity.RESULT_OK){
            val todo = it.data!!.getSerializableExtra("todoData") as Todo
            addTodoData(todoData = todo)
            adapter.items.add(todo)
            adapter.notifyDataSetChanged()
            binding.title = "오늘 계획 ${todoData.size}개"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        val root = binding.root

        binding.title = "오늘 계획 ${todoData.size}개"



        binding.recycleMainTodoList.adapter = adapter
        binding.recycleMainTodoList.addItemDecoration(TodoListDecoration(100))


        return root
    }

    private fun ActivityClickEvent(){
        with(binding){
            btnMainAddTodo.setOnClickListener {
                val intent = Intent(requireContext(), AddTodoActivity::class.java)
                todoAddResultCallback.launch(intent)
            }

            btnMainReset.setOnClickListener {
                editor.clear()
                editor.apply()

                val intent = Intent(requireContext(), Main2Activity::class.java)
                startActivity(intent)
            }

            btnMainLog.setOnClickListener {
                Log.d("todoLog", getData().toString())
                Log.d("todoLog todoData", todoData.toString())
                Log.d("todoLog adapter", adapter.items.toString())
            }
        }


    }
}