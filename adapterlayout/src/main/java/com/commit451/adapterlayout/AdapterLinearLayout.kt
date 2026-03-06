@file:Suppress("unused")

package com.commit451.adapterlayout

import android.content.Context
import android.util.AttributeSet
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView

/**
 * LinearLayout with [Adapter] support. See [AdapterLayoutDelegate] for
 * the good bits, and follow the convention here to create your own [RecyclerView.Adapter]
 * backed [android.view.ViewGroup]
 */
class AdapterLinearLayout : LinearLayout {

    private var adapterLayoutDelegate: AdapterLayoutDelegate? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes,
    )

    @get:Nullable
    var adapter: RecyclerView.Adapter<*>?
        get() = adapterLayoutDelegate?.getAdapter()
        set(value) {
            if (adapterLayoutDelegate == null) {
                adapterLayoutDelegate = AdapterLayoutDelegate(this)
            }
            adapterLayoutDelegate?.setAdapter(value)
        }

    @Nullable
    fun getViewHolderAt(index: Int): RecyclerView.ViewHolder? {
        return adapterLayoutDelegate?.getViewHolderAt(index)
    }
}
