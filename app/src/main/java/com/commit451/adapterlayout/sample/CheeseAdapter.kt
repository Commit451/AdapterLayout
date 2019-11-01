package com.commit451.adapterlayout.sample

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.commit451.adapterlayout.AdapterLayout

/**
 * Adapter for the recyclerview, which holds cheeses
 */
class CheeseAdapter(private val listener: (cheese: Cheese) -> Unit) : RecyclerView.Adapter<CheeseViewHolder>() {

    private val values = mutableListOf<Cheese>()

    fun setData(cheeses: Collection<Cheese>) {
        values.clear()
        values.addAll(cheeses)
        notifyDataSetChanged()
    }

    fun add(cheese: Cheese) {
        values.add(cheese)
        notifyItemInserted(values.size - 1)
    }

    fun removeLast() {
        if (values.isNotEmpty()) {
            val removeIndex = values.size - 1
            remove(removeIndex)
        }
    }

    fun remove(index: Int) {
        values.removeAt(index)
        notifyItemRemoved(index)
    }

    fun changeMiddle() {
        if (values.isNotEmpty()) {
            val index = values.size / 2
            values[index].name = "Swiss"
            notifyItemChanged(index)
        }
    }

    fun changeAll() {
        if (values.isNotEmpty()) {
            for (cheese in values) {
                cheese.name = "Swiss"
            }
            notifyItemRangeChanged(0, values.size)
        }
    }

    fun clear() {
        if (values.isNotEmpty()) {
            val size = values.size
            values.clear()
            notifyItemRangeRemoved(0, size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder {
        val holder = CheeseViewHolder.inflate(parent)
        holder.itemView.setOnClickListener {
            //Takes the place of holder.getAdapterPosition()
            val position = AdapterLayout.getAdapterPosition(holder)
            val cheese = getItemAt(position)
            listener.invoke(cheese)
        }
        holder.buttonRemove.setOnClickListener {
            val position = AdapterLayout.getAdapterPosition(holder)
            remove(position)
        }
        return holder
    }

    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        val cheese = getItemAt(position)
        holder.bind(cheese)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    private fun getItemAt(position: Int): Cheese {
        return values[position]
    }
}
