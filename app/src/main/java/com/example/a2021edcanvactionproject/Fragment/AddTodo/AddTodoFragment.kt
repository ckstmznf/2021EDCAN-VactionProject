package com.example.a2021edcanvactionproject.Fragment.AddTodo

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.Activity.Main.MainActivity
import com.example.a2021edcanvactionproject.Activity.Splash.DB
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.FragmentAddTodoBinding
import com.example.a2021edcanvactionproject.Model.Time
import com.example.a2021edcanvactionproject.Model.Todo

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

        initClickEvent()

        val kinds = DB.getKinds()
        kinds.add(0, "운동 선택")
        kinds.add("운동 추가")

        with(binding){
            spinAddTodoChoice.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, kinds)
            spinAddTodoChoice.onItemSelectedListener = kindChanged
            edtAddTodoSet.addTextChangedListener(setChange)
            edtAddTodoSetCount.addTextChangedListener(setChange)
        }

        return root
    }


    val kindChanged = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            if(binding.spinAddTodoChoice.getItemAtPosition(p2) == "운동 추가"){
                val dialog = View.inflate(activity, R.layout.dialog_input, null)
                AlertDialog.Builder(activity)
                    .setView(dialog)
                    .setPositiveButton("추가", object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            val inputKind = dialog.findViewById<EditText>(R.id.editText)
                            val kind = inputKind.text.toString()
                            DB.addKind(kind)
                            p0!!.dismiss()
                        }
                    })
                    .setNegativeButton("취소", object : DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            p0!!.dismiss()
                        }
                    })
                    .show()
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }

    private fun initClickEvent() = with(binding){
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

        DB.addTodoData(todoData = todo)
        Toast.makeText(requireContext(), "운동을 추가하였습니다.", Toast.LENGTH_LONG).show()

        (activity as MainActivity).navController.navigate(R.id.action_addTodoFragment_to_mainFragment)
    }

    fun addTodoInputEmptyCheck() : Boolean = with(binding){
        if (kind == "운동 선택") {
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