package com.commit451.adapterlayout;

import android.annotation.TargetApi;
import android.content.Context;
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

    private AdapterLayoutDelegate mAdapterLayoutDelegate;

    public AdapterLinearLayout(Context context) {
        super(context);
        init();
    }

    public AdapterLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdapterLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public AdapterLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mAdapterLayoutDelegate = new AdapterLayoutDelegate(this);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mAdapterLayoutDelegate.setAdapter(adapter);
    }
}
