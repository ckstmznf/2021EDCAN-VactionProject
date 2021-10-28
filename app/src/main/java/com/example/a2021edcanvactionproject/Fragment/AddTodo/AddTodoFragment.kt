package com.example.a2021edcanvactionproject.Fragment.AddTodo

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.example.a2021edcanvactionproject.Activity.Main.MainActivity
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.FragmentAddTodoBinding
import com.example.a2021edcanvactionproject.Model.Time
import com.example.a2021edcanvactionproject.Model.Todo
import com.example.a2021edcanvactionproject.Model.addTodoData

class AddTodoFragment : Fragment() {
    lateinit var binding : FragmentAddTodoBinding
    var choiceSetTime = true //시간, 세트 선택, true면 시간, false면 세트

    lateinit var kind: String
    lateinit var set: String
    lateinit var setCount: String
    lateinit var timeM: String
    lateinit var timeS: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_todo, container, false)
        val root = binding.root

        ActivityInputEvent()

        binding.edtAddTodoSet.addTextChangedListener(setChange)
        binding.edtAddTodoSetCount.addTextChangedListener(setChange)

        return root
    }


    private fun ActivityInputEvent() = with(binding){
        btnAddTodoAddTodo.setOnClickListener {
            saveTodoData()
        }


        btnAddTodoSetTime.setOnClickListener {
            linearAddTodoInputTime.visibility = View.VISIBLE
            linearAddTodoInputSet.visibility = View.INVISIBLE
            txtAddTodoSetTotalCount.visibility = View.INVISIBLE
            choiceSetTime = true
        }

        btnAddTodoSetSet.setOnClickListener {
            linearAddTodoInputTime.visibility = View.INVISIBLE
            linearAddTodoInputSet.visibility = View.VISIBLE
            txtAddTodoSetTotalCount.visibility = View.VISIBLE
            choiceSetTime = false
        }
    }

    val setChange = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {
            var set = binding.edtAddTodoSet.text.toString()
            var setCount = binding.edtAddTodoSetCount.text.toString()

            set = if(TextUtils.isEmpty(set)) "0" else set
            setCount  = if(TextUtils.isEmpty(setCount)) "0" else setCount

            val totalCount = set.toInt() * setCount.toInt()

            binding.txtAddTodoSetTotalCount.text = totalCount.toString() + "회"
        }
    }

    private fun saveTodoData() {
        with(binding) {
            kind = spinAddTodoChoice.selectedItem.toString();
            set = edtAddTodoSet.text.toString()// as Int
            setCount = edtAddTodoSetCount.text.toString()
            timeM = edtAddTodoTimeM.text.toString()
            timeS = edtAddTodoTimeS.text.toString()
        }

        if(addTodoInputEmptyCheck()) {return}

        val todo = Todo(
            kind = kind,
            st = choiceSetTime,
        )

        if (choiceSetTime) {
            todo.time = Time(timeM.toInt(), timeS.toInt())
        } else {
            todo.set = set.toInt()
            todo.setCount = setCount.toInt()
        }

        addTodoData(todoData = todo)
        Toast.makeText(requireContext(), "운동을 추가하였습니다.", Toast.LENGTH_LONG).show()

        (activity as MainActivity).navController.navigate(R.id.action_addTodoFragment_to_mainFragment)
    }

    fun addTodoInputEmptyCheck() : Boolean = with(binding){
        if (kind == "운동 종류") {
            Toast.makeText(requireContext(), "운동 종류를 선택해주세요.", Toast.LENGTH_LONG).show()
        }
        else if (!choiceSetTime && set.isEmpty()) {
            edtAddTodoSet.error = "세트를 입력하세요."
        }
        else if (!choiceSetTime && setCount.isEmpty()) {
            edtAddTodoSetCount.error = "한세트당 할 개수를 입력하세요."
        }
        else if (choiceSetTime && timeM == "") {
            edtAddTodoTimeM.error = "운동할 시간을 입력하세요."
        }
        else if (choiceSetTime && timeS == "") {
            edtAddTodoTimeS.error = "운동할 시간을 입력하세요."
        }
        else{
            return false
        }

        return true
    }
}