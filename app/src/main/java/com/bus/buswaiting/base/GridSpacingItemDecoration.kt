package com.bus.buswaiting.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bus.buswaiting.tool.Tool
import com.bus.buswaiting.tool.Tool.toDp
import kotlin.math.abs

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean,
    private val getSpanSize: (Int) -> Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val spanSize = getSpanSize(position)

        if (spanSize == 1) {
            val column = position - 1 % spanCount // item column
            Tool.logLongMessage("Michael","column : $column")
            if (includeEdge) {
                outRect.left = getLeftSpacing(column)
                outRect.right = getRightSpacing(column)
                outRect.top = 10.toDp()
            } else {
                outRect.left = spacing * column / (spanCount - 1)
                outRect.right = spacing - outRect.left
            }

        } else {
            outRect.setEmpty()
        }
    }

    private fun getRightSpacing(column: Int): Int {
        if (column != 0 && abs(spanCount % column) == 1){
            return spacing
        }
        return spacing / 2
    }

    private fun getLeftSpacing(column: Int): Int {
        if (column  == 0){
            return spacing
        }
        return spacing / 2
    }
}