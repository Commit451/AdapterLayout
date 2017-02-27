package com.commit451.adapterlayout.kotlin

import android.support.v7.widget.RecyclerView
import com.commit451.adapterlayout.AdapterLayout

fun RecyclerView.ViewHolder.getAdapterLayoutAdapterPosition(): Int {
    return AdapterLayout.getAdapterPosition(this)
}

fun RecyclerView.ViewHolder.getAdapterLayoutLayoutPosition(): Int {
    return AdapterLayout.getLayoutPosition(this)
}
