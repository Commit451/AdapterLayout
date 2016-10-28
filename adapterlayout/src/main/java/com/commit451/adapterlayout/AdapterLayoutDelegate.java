package com.commit451.adapterlayout;

import android.support.annotation.Nullable;
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
     * Checks for if the data changes and changes the views accordingly
     */
    private RecyclerView.AdapterDataObserver mObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            //too general, we just have to completely recreate
            recreateViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            updateViews(positionStart, itemCount, null);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
            updateViews(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            addViews(positionStart, itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            removeViews(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            //TODO this should probably be smarter and just move relevant views
            recreateViews();
        }
    };

    /**
     * Create a new delegate, acting as the bridge between the adapter and the ViewGroup
     *
     * @param viewGroup the ViewGroup which will have views added and removed from
     */
    public AdapterLayoutDelegate(ViewGroup viewGroup) {
        mViewGroup = viewGroup;
    }

    /**
     * Set the adapter which will add and remove views from this layout
     *
     * @param adapter the adapter
     */
    public void setAdapter(@Nullable RecyclerView.Adapter adapter) {
        if (mAdapter != null) {
            try {
                mAdapter.unregisterAdapterDataObserver(mObserver);
            } catch (Exception ignored) {
            }
        }

        mAdapter = adapter;
        if (mAdapter != null) {
            mAdapter.registerAdapterDataObserver(mObserver);
        }
        recreateViews();
    }

    /**
     * Returns the adapter which was passed via {@link #setAdapter(RecyclerView.Adapter)}
     *
     * @return the adapter
     */
    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    /**
     * Return the {@link android.support.v7.widget.RecyclerView.ViewHolder} at the specified position.
     *
     * @param index the position at which to get the ViewHolder
     * @return the ViewHolder at the index, or null if none exists
     */
    @Nullable
    public RecyclerView.ViewHolder getViewHolderAt(int index) {
        View view = mViewGroup.getChildAt(index);
        if (view == null) {
            return null;
        }
        return (RecyclerView.ViewHolder) view.getTag(R.id.adapter_layout_list_holder);
    }

    private void addViews(int positionStart, int itemCount) {
        final int end = positionStart + itemCount;
        for (int i = positionStart; i < end; i++) {
            addViewAt(i);
        }
    }

    private void addViewAt(int index) {
        addViewAt(mAdapter.getItemViewType(index), index);
    }

    private void addViewAt(int viewType, int index) {
        RecyclerView.ViewHolder viewHolder = mAdapter.onCreateViewHolder(mViewGroup, viewType);
        //setting the lib to min 4.0 to avoid leaks from doing this
        viewHolder.itemView.setTag(R.id.adapter_layout_list_holder, viewHolder);
        viewHolder.itemView.setTag(R.id.adapter_layout_list_view_type, viewType);
        viewHolder.itemView.setTag(R.id.adapter_layout_list_position, index);
        mViewGroup.addView(viewHolder.itemView);
        mAdapter.onBindViewHolder(viewHolder, index);
    }

    private void updateViews(int positionStart, int itemCount, @Nullable Object payload) {
        final int end = positionStart + itemCount;
        for (int i = positionStart; i < end; i++) {
            RecyclerView.ViewHolder viewHolder = getViewHolderAt(i);
            mAdapter.onBindViewHolder(viewHolder, i);
        }
    }

    private void removeViews(int positionStart, int itemCount) {
        mViewGroup.removeViews(positionStart, itemCount);
    }

    /**
     * Updates all the views to match the dataset changing. Its kinda a last resort since we would
     * prefer to just adjust the views that were changed or removed
     */
    private void recreateViews() {
        if (mAdapter == null) {
            mViewGroup.removeAllViews();
            return;
        }
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
                        //perfect, it exists and is the right type, so just bind it
                        mAdapter.onBindViewHolder(savedViewHolder, i);
                    } else {
                        //it already existed, but something was wrong. So remove it and recreate it
                        addViewAt(viewType, i);
                        mViewGroup.removeView(child);
                    }
                } else {
                    //Creating a brand new view
                    addViewAt(viewType, i);
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
