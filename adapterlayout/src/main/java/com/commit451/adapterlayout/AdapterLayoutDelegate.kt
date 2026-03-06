package com.commit451.adapterlayout

import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView

/**
 * Does all the hard to work to map a [RecyclerView.Adapter] to a
 * [ViewGroup]. See [AdapterLinearLayout] for an example on how to create your own
 */
class AdapterLayoutDelegate(
    private val viewGroup: ViewGroup,
) {

    @Suppress("UNCHECKED_CAST")
    private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null

    /**
     * Checks for if the data changes and changes the views accordingly
     */
    private val observer = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            // too general, we just have to completely recreate
            recreateViews()
            reindex()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            super.onItemRangeChanged(positionStart, itemCount)
            updateViews(positionStart, itemCount)
            reindex()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            super.onItemRangeChanged(positionStart, itemCount, payload)
            updateViews(positionStart, itemCount)
            reindex()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            addViews(positionStart, itemCount)
            reindex()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            removeViews(positionStart, itemCount)
            reindex()
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount)
            // this should probably be smarter and just move relevant views
            recreateViews()
            reindex()
        }
    }

    /**
     * Set the adapter which will add and remove views from this layout
     *
     * @param adapter the adapter
     */
    @Suppress("UNCHECKED_CAST")
    fun setAdapter(@Nullable adapter: RecyclerView.Adapter<*>?) {
        this.adapter?.let {
            try {
                it.unregisterAdapterDataObserver(observer)
            } catch (_: Exception) {
            }
        }

        this.adapter = adapter as? RecyclerView.Adapter<RecyclerView.ViewHolder>
        this.adapter?.registerAdapterDataObserver(observer)
        recreateViews()
    }

    /**
     * Returns the adapter which was passed via [setAdapter]
     *
     * @return the adapter
     */
    fun getAdapter(): RecyclerView.Adapter<*>? = adapter

    /**
     * Return the [RecyclerView.ViewHolder] at the specified position.
     *
     * @param index the position at which to get the ViewHolder
     * @return the ViewHolder at the index, or null if none exists
     */
    @Nullable
    fun getViewHolderAt(index: Int): RecyclerView.ViewHolder? {
        val view = viewGroup.getChildAt(index) ?: return null
        return view.getTag(R.id.adapter_layout_list_holder) as? RecyclerView.ViewHolder
    }

    private fun addViews(positionStart: Int, itemCount: Int) {
        val end = positionStart + itemCount
        for (i in positionStart until end) {
            addViewAt(i)
        }
    }

    private fun addViewAt(index: Int) {
        val safeAdapter = adapter ?: return
        addViewAt(safeAdapter.getItemViewType(index), index)
    }

    private fun addViewAt(viewType: Int, index: Int) {
        val safeAdapter = adapter ?: return
        val viewHolder = safeAdapter.onCreateViewHolder(viewGroup, viewType)
        // setting min sdk high enough to avoid leaks doing this
        viewHolder.itemView.setTag(R.id.adapter_layout_list_holder, viewHolder)
        viewHolder.itemView.setTag(R.id.adapter_layout_list_view_type, viewType)
        viewHolder.itemView.setTag(R.id.adapter_layout_list_position, index)
        viewGroup.addView(viewHolder.itemView)
        safeAdapter.onBindViewHolder(viewHolder, index)
    }

    private fun updateViews(positionStart: Int, itemCount: Int) {
        val safeAdapter = adapter ?: return
        val end = positionStart + itemCount
        for (i in positionStart until end) {
            val viewHolder = getViewHolderAt(i) ?: continue
            safeAdapter.onBindViewHolder(viewHolder, i)
        }
    }

    private fun removeViews(positionStart: Int, itemCount: Int) {
        viewGroup.removeViews(positionStart, itemCount)
    }

    /**
     * Updates all the views to match the dataset changing. Its kinda a last resort since we would
     * prefer to just adjust the views that were changed or removed
     */
    private fun recreateViews() {
        val safeAdapter = adapter
        if (safeAdapter == null) {
            viewGroup.removeAllViews()
            return
        }

        var i = 0
        while (i < safeAdapter.itemCount) {
            val viewType = safeAdapter.getItemViewType(i)
            // This means the view could already exist
            if (i < viewGroup.childCount) {
                val child = viewGroup.getChildAt(i)
                val savedViewType = child.getTag(R.id.adapter_layout_list_view_type) as? Int
                val savedViewHolder = child.getTag(R.id.adapter_layout_list_holder) as? RecyclerView.ViewHolder

                if (savedViewType != null && savedViewType == viewType && savedViewHolder != null) {
                    // perfect, it exists and is the right type, so just bind it
                    safeAdapter.onBindViewHolder(savedViewHolder, i)
                } else {
                    // it already existed, but something was wrong. So remove it and recreate it
                    addViewAt(viewType, i)
                    viewGroup.removeView(child)
                }
            } else {
                // Creating a brand new view
                addViewAt(viewType, i)
            }
            i++
        }

        // Outside the bounds of the dataset, so remove it
        if (i < viewGroup.childCount) {
            viewGroup.removeViews(i, viewGroup.childCount - i)
        }
    }

    private fun reindex() {
        val count = adapter?.itemCount ?: return
        for (i in 0 until count) {
            val child: View = viewGroup.getChildAt(i)
            child.setTag(R.id.adapter_layout_list_position, i)
        }
    }
}
