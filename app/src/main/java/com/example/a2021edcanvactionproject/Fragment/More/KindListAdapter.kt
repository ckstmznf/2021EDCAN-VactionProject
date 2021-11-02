package com.example.a2021edcanvactionproject.Fragment.More

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a2021edcanvactionproject.R


class KindListAdapter(val items : MutableList<String>) : RecyclerView.Adapter<KindListAdapter.Holder>() {
    class Holder(val view : View) : RecyclerView.ViewHolder(view) {
        val kindTitle = view.findViewById<TextView>(R.id.txt_kindListItem_kind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.kind_list_item, parent, false)

        return Holder(layout)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.kindTitle.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }
}