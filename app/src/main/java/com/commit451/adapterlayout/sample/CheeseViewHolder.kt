package com.commit451.adapterlayout.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

/**
 * The view holder related to each Cheese item
 */
class CheeseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {

        fun inflate(parent: ViewGroup): CheeseViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cheese, parent, false)
            return CheeseViewHolder(view)
        }
    }

    private val title: TextView = view.findViewById(R.id.text)
    val buttonRemove: View = view.findViewById(R.id.button_remove)

    fun bind(cheese: Cheese) {
        title.text = cheese.name
    }
}
