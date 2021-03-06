package com.example.a2021edcanvactionproject.Fragment.Main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TodoListDecoration(val divHeight : Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = divHeight
    }
}