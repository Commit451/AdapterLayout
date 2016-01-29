package com.commit451.adapterlayout.sample;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Adapter;
import android.widget.LinearLayout;

import com.commit451.adapterlayout.AdapterLayoutDelegate;

/**
 * LinearLayout with {@link Adapter} support. See {@link AdapterLayoutDelegate} for
 * the good bits, and follow the convention here to create your own {@link android.support.v7.widget.RecyclerView.Adapter}
 * backed {@link android.view.ViewGroup}
 */
public class AdapterFlowLayout extends LinearLayout {

    private AdapterLayoutDelegate mAdapterLayoutDelegate;

    public AdapterFlowLayout(Context context) {
        super(context);
    }

    public AdapterFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdapterFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public AdapterFlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (mAdapterLayoutDelegate == null) {
            mAdapterLayoutDelegate = new AdapterLayoutDelegate(this);
        }
        mAdapterLayoutDelegate.setAdapter(adapter);
    }

    @Nullable
    public RecyclerView.Adapter getAdapter() {
        if (mAdapterLayoutDelegate != null) {
            return mAdapterLayoutDelegate.getAdapter();
        }
        return null;
    }

    @Nullable
    public RecyclerView.ViewHolder getViewHolderAt(int index) {
        return mAdapterLayoutDelegate.getViewHolderAt(index);
    }
}
