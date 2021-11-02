package com.example.a2021edcanvactionproject.Fragment.More

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvactionproject.Activity.Splash.DB
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {
    lateinit var binding : FragmentMoreBinding
    val kindList = DB.getKinds()
    val kindListAdapter = KindListAdapter(kindList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_more, container, false)
        val root = binding.root

        initClickEvent()

        if(kindList.isEmpty()){
            binding.txtMoreAddPls.visibility = View.VISIBLE
        }   else{
            binding.recycleMoreKondList.adapter = kindListAdapter
        }

        return root
    }

    fun initClickEvent() = with(binding){
        btnMoreAdd.setOnClickListener {
            val dialog = View.inflate(activity, R.layout.dialog_input, null)
            AlertDialog.Builder(activity)
                .setView(dialog)
                .setPositiveButton("추가") { p0, p1 ->
                    val inputKind = dialog.findViewById<EditText>(R.id.editText)
                    val kind = inputKind.text.toString()
                    DB.addKind(kind)
                    kindList.add(kind)
                    kindListAdapter.notifyDataSetChanged()

                    if (kindList.size == 1) binding.txtMoreAddPls.visibility = View.INVISIBLE

                    p0!!.dismiss()
                }
                .setNegativeButton("취소") { p0, p1 ->
                    p0!!.dismiss()
                }
                .show()
        }
    }

}