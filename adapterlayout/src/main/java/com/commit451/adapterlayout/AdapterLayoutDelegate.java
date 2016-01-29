package com.commit451.adapterlayout;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


/**
 * Does all the hard to work to map a {@link android.support.v7.widget.RecyclerView.Adapter} to a
 * {@link ViewGroup}. See {@link AdapterLinearLayout} for an example on how to create your own
 */
public class AdapterLayoutDelegate {

    private RecyclerView.Adapter mAdapter;
    private ViewGroup mViewGroup;

    /**
     * Checks for if the data changes
     */
    private RecyclerView.AdapterDataObserver mObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            updateViews();
        }
    };

    /**
     * Create a new delegate, acting as the bridge between the adapter and the ViewGroup
     * @param viewGroup the ViewGroup which will have views added and removed from
     */
    public AdapterLayoutDelegate(ViewGroup viewGroup) {
        mViewGroup = viewGroup;
    }

    /**
     * Set the adapter which will add and remove views from this layout
     * @param adapter the adapter
     */
    public void setAdapter(RecyclerView.Adapter adapter) {
        if (mAdapter != null) {
            try {
                mAdapter.unregisterAdapterDataObserver(mObserver);
            } catch (Exception ignored) {}
        }

        mAdapter = adapter;
        mAdapter.registerAdapterDataObserver(mObserver);
        updateViews();
    }

    /**
     * Updates all the views to match the dataset changing
     */
    private void updateViews() {
        for (int i = 0; i < mAdapter.getItemCount() || i < mViewGroup.getChildCount(); i++) {

            //Within the bounds of the dataset
            if (i < mAdapter.getItemCount()) {
                int viewType = mAdapter.getItemViewType(i);
                //This means the view could already exist
                if (i < mViewGroup.getChildCount()) {
                    View child = mViewGroup.getChildAt(i);
                    Integer savedViewType = (Integer) child.getTag(R.id.adapter_layout_list_view_type);
                    RecyclerView.ViewHolder savedViewHolder = (RecyclerView.ViewHolder) child.getTag(R.id.adapter_layout_list_holder);

                    if (savedViewType != null && savedViewType == viewType && savedViewHolder != null) {
                        mAdapter.onBindViewHolder(savedViewHolder, i);
                    } else {
                        RecyclerView.ViewHolder viewHolder = mAdapter.onCreateViewHolder(mViewGroup, viewType);
                        viewHolder.itemView.setTag(R.id.adapter_layout_list_holder, viewHolder);
                        viewHolder.itemView.setTag(R.id.adapter_layout_list_view_type, viewType);
                        mAdapter.onBindViewHolder(viewHolder, i);
                        mViewGroup.addView(viewHolder.itemView, i);
                        mViewGroup.removeView(child);
                    }
                } else {
                    //Creating a brand new view
                    RecyclerView.ViewHolder viewHolder = mAdapter.onCreateViewHolder(mViewGroup, viewType);
                    viewHolder.itemView.setTag(R.id.adapter_layout_list_holder, viewHolder);
                    viewHolder.itemView.setTag(R.id.adapter_layout_list_view_type, viewType);
                    mAdapter.onBindViewHolder(viewHolder, i);
                    mViewGroup.addView(viewHolder.itemView);
                }
            } else {
                //Outside the bounds of the dataset, so remove it
                if (i < mViewGroup.getChildCount()) {
                    View child = mViewGroup.getChildAt(i);
                    mViewGroup.removeView(child);
                }
            }
        }
    }
}
