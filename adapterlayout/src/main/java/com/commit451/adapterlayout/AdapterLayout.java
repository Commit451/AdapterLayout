package com.commit451.adapterlayout;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Provides access to some of the public API of AdapterLayout in a way that will be available for any given
 * AdapterLayout
 */
public class AdapterLayout {

    /**
     * Get the position in the AdapterLayout of the View holder.
     * Use this instead of {@link RecyclerView.ViewHolder#getAdapterPosition()}
     *
     * @param viewHolder the holder to find the position of
     * @return the index of the holder, or -1 if not found
     */
    public static int getAdapterPosition(RecyclerView.ViewHolder viewHolder) {
        return getPosition(viewHolder);
    }

    /**
     * Get the position in the AdapterLayout of the View holder.
     * Use this instead of {@link RecyclerView.ViewHolder#getLayoutPosition()}
     *
     * @param viewHolder the holder to find the position of
     * @return the index of the holder, or -1 if not found
     */
    public static int getLayoutPosition(RecyclerView.ViewHolder viewHolder) {
        return getPosition(viewHolder);
    }

    private static int getPosition(RecyclerView.ViewHolder viewHolder) {
        return (int) viewHolder.itemView.getTag(R.id.adapter_layout_list_position);
    }
}
