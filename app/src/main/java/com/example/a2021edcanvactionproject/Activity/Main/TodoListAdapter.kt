package com.example.a2021edcanvactionproject.Activity.Main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a2021edcanvactionproject.Activity.TodoDetail.TodoDetailActivity
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.model.Todo

class TodoListAdapter(val items: MutableList<Todo>) : RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {
    class TodoListViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.txt_todoListItem_kind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.todo_list_item, parent, false)

        val holder = TodoListViewHolder(layout)
//        holder.view.setOnClickListener{
//            Toast.makeText()
//        }



        holder.view.setOnClickListener {
            val intent = Intent(parent.context, TodoDetailActivity::class.java)
            intent.putExtra("todoData", items[holder.adapterPosition])

            parent.context.startActivity(intent)
        }


        return holder
    }


    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.title.text = items[position].kind
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
