package com.weber.cathaybktest.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomDividerItemDecoration(var spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = spacing
    }
}