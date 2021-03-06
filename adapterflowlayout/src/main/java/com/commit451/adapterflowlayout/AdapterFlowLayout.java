package com.commit451.adapterflowlayout;

import android.content.Context;
import android.util.AttributeSet;

import com.commit451.adapterlayout.AdapterLayoutDelegate;
import com.wefika.flowlayout.FlowLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * {@link com.wefika.flowlayout.FlowLayout} with {@link androidx.recyclerview.widget.RecyclerView.Adapter} support.
 */
@SuppressWarnings("unused")
public class AdapterFlowLayout extends FlowLayout {

    private AdapterLayoutDelegate adapterLayoutDelegate;

    public AdapterFlowLayout(Context context) {
        super(context);
    }

    public AdapterFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdapterFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
