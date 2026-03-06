package com.commit451.adapterlayout

import androidx.recyclerview.widget.RecyclerView

/**
 * Provides access to some of the public API of AdapterLayout in a way that will be available for any given
 * AdapterLayout
 */
class AdapterLayout {
    companion object {
        /**
         * Get the position in the AdapterLayout of the View holder.
         * Use this instead of [RecyclerView.ViewHolder.getAdapterPosition]
         *
         * @param viewHolder the holder to find the position of
         * @return the index of the holder, or -1 if not found
         */
        @JvmStatic
        fun getAdapterPosition(viewHolder: RecyclerView.ViewHolder): Int {
            return getPosition(viewHolder)
        }

        /**
         * Get the position in the AdapterLayout of the View holder.
         * Use this instead of [RecyclerView.ViewHolder.getLayoutPosition]
         *
         * @param viewHolder the holder to find the position of
         * @return the index of the holder, or -1 if not found
         */
        @JvmStatic
        fun getLayoutPosition(viewHolder: RecyclerView.ViewHolder): Int {
            return getPosition(viewHolder)
        }

        private fun getPosition(viewHolder: RecyclerView.ViewHolder): Int {
            return viewHolder.itemView.getTag(R.id.adapter_layout_list_position) as Int
        }
    }
}
