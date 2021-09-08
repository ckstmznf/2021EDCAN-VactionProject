package com.example.a2021edcanvactionproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.a2021edcanvactionproject.model.Todo

class TodoListAdapter(private val items : MutableList<Todo>) : BaseAdapter(){
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): Todo {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var convertView = p1

        if(convertView == null){
            convertView = LayoutInflater.from(p2?.context).inflate(R.layout.todo_list_item, p2, false)
        }

        val item = items[p0]
        convertView!!.findViewById<TextView>(R.id.txt_todoListItem_kind).text = item.kind
        convertView!!.findViewById<TextView>(R.id.txt_todoListItem_set).text = "${item.set}회"

        return convertView
    }
}