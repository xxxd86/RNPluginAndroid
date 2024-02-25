package com.example.asbaselibrary.base.baseAdapter

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.example.asbaselibrary.base.DeviceChange

/**
 * 使用recyclerview实例,处理滑动冲突
 * 并绑定DeviceChange
 */
class BaseRecyclerView : RecyclerView,DeviceChange{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onNightChange(onSuccess: () -> Unit): Boolean {
        TODO("Not yet implemented")
    }

    override fun onLanguageChange(onSuccess: () -> Unit): Boolean {
        TODO("Not yet implemented")
    }

    override fun onScreanChange(onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }
}