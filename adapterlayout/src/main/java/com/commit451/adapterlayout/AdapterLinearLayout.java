package com.commit451.adapterlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Adapter;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * LinearLayout with {@link Adapter} support. See {@link AdapterLayoutDelegate} for
 * the good bits, and follow the convention here to create your own {@link RecyclerView.Adapter}
 * backed {@link android.view.ViewGroup}
 */
@SuppressWarnings("unused")
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

    @Nullable
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
