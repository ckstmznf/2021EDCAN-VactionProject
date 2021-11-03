package com.example.a2021edcanvactionproject.Fragment.AddTodo

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.ChoiceKindDroupdownLayoutBinding

class SpinnerAdapter(
    context : Context,
    @LayoutRes
    private val resId : Int,
    val items : MutableList<String>
) : ArrayAdapter<String>(context, resId, items) {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): String? {
        return items[position]
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ChoiceKindDroupdownLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.txtKindDroupDownTitle.text = items[position]
        binding.txtKindDroupDownTitle.setTextColor(ContextCompat.getColor(context, R.color.black))
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ChoiceKindDroupdownLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.txtKindDroupDownTitle.text = items[position]

        return binding.root
    }
}