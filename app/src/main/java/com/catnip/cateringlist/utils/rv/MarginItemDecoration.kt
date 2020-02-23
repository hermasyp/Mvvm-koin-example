package com.catnip.cateringlist.utils.rv

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceHeight
            }
            left = spaceHeight
            right = spaceHeight
            bottom = spaceHeight
        }
    }
}