package org.dukecon.android.ui.features.speaker

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import org.dukecon.android.ui.features.event.EventsAdapter

internal class DividerItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

    private val divider: Drawable

    init {
        val a = context.obtainStyledAttributes(intArrayOf(android.R.attr.listDivider))
        divider = a.getDrawable(0)
        a.recycle()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        for (i in 0..childCount) {
            val view = parent.getChildAt(i)
            if (view != null) {
                val holder = parent.getChildViewHolder(view)
                if (holder.adapterPosition != 0) {
                    val left = 0
                    val top = holder.itemView.top
                    val right = parent.width
                    val bottom = top + divider.intrinsicHeight
                    divider.setBounds(left, top, right, bottom)
                    divider.draw(c)
                }
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val holder = parent.getChildViewHolder(view)
        if (holder is EventsAdapter.ViewHolder) {
            if (holder.adapterPosition != 0) {
                outRect.set(0, divider.intrinsicHeight, 0, 0)
            }
        }
    }
}