package com.commit451.adapterlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Adapter;
import android.widget.LinearLayout;

/**
 * LinearLayout with {@link Adapter} support. See {@link AdapterLayoutDelegate} for
 * the good bits, and follow the convention here to create your own {@link android.support.v7.widget.RecyclerView.Adapter}
 * backed {@link android.view.ViewGroup}
 */
public class AdapterLinearLayout extends LinearLayout {

    private AdapterLayoutDelegate adapterLayoutDelegate;

    public AdapterLinearLayout(Context context) {
        super(context);
    }

    public AdapterLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public AdapterLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AdapterLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapterLayoutDelegate == null) {
            adapterLayoutDelegate = new AdapterLayoutDelegate(this);
        }
        adapterLayoutDelegate.setAdapter(adapter);
    }

    public RecyclerView.Adapter getAdapter() {
        if (adapterLayoutDelegate != null) {
            return adapterLayoutDelegate.getAdapter();
        }
        return null;
    }

    @Nullable
    public RecyclerView.ViewHolder getViewHolderAt(int index) {
        if (adapterLayoutDelegate != null) {
            return adapterLayoutDelegate.getViewHolderAt(index);
        }
        return null;
    }
}
