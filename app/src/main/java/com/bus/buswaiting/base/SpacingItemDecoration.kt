package com.bus.buswaiting.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bus.buswaiting.tool.Tool
import com.bus.buswaiting.tool.Tool.toDp
import kotlin.math.abs

class SpacingItemDecoration(
    private val spacing: Int,
    private val totalSize: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        outRect.left = spacing
        outRect.right = spacing
        when (position) {
            0 -> {
                outRect.top = spacing
                outRect.bottom = spacing / 2
            }
            totalSize -> {
                outRect.top = spacing / 2
                outRect.bottom = spacing
            }
            else -> {
                outRect.top = spacing / 2
                outRect.bottom = spacing / 2
            }
        }

    }
}