package com.example.rnpluginfg.base.baseView

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView

/**
 * 实现网页加载到android,实现的路径为go语言创建网页的建立
 */
class BaseWebView: WebView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        privateBrowsing: Boolean
    ) : super(context, attrs, defStyleAttr, privateBrowsing)

}