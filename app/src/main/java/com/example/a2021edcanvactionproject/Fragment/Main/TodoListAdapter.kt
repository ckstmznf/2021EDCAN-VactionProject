package com.example.a2021edcanvactionproject.Fragment.Main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a2021edcanvactionproject.Activity.TodoDetail.TodoDetailActivity
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.Model.Todo

class TodoListAdapter(val items: MutableList<Todo>, val toDay : Boolean) : RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {
    class TodoListViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.txt_todoListItem_kind)
        val line = view.findViewById<ImageView>(R.id.img_todoListItem_line)
        val checkIcon = view.findViewById<ImageView>(R.id.img_todoListItem_checkIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.todo_list_item, parent, false)

        val holder = TodoListViewHolder(layout)

        holder.view.setOnClickListener {
            val intent = Intent(parent.context, TodoDetailActivity::class.java)
            intent.putExtra("todoData", items[holder.adapterPosition])
            intent.putExtra("toDay", toDay)

            parent.context.startActivity(intent)
        }


        return holder
    }


    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) = with(holder){
        title.text = items[position].kind

        if(items[position].completion){
            //완료한 tood인 경우에
            checkIcon.visibility = View.VISIBLE
            line.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
