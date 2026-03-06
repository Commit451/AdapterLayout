@file:Suppress("unused")

package com.commit451.adapterflowlayout

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.commit451.adapterlayout.AdapterLayoutDelegate
import com.wefika.flowlayout.FlowLayout

/**
 * [FlowLayout] with [RecyclerView.Adapter] support.
 */
class AdapterFlowLayout : FlowLayout {

    private var adapterLayoutDelegate: AdapterLayoutDelegate? = null

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

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
