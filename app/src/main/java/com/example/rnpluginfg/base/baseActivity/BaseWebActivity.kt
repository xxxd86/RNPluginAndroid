package com.example.rnpluginfg.base.baseActivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.viewbinding.ViewBinding

/**
 * @sample 一般适用于多文字界面
 */
abstract class BaseWebActivity<B : ViewBinding>(bindingFactory: (LayoutInflater) -> B) : BaseActivityLate<B>( bindingFactory ) {
    companion object{
        /**
         * 伴生对象中实现初始化
         */
        var url = "https://www.baidu.com"

        /**
         * 默认webClient
         */
        val webClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(url);
                return false;
            }
        }
    }
    open var WEB_URL = "http://example.com"
    open var webView:WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initView() {
        super.initView()
        initWebView()
    }

    /**
     * 实现
     */
    open fun initWebView(){


    }

    override fun onDestroy() {
        super.onDestroy()
        if (webView != null) {
            webView!!.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView!!.clearHistory();

            (webView!!.parent as ViewGroup).removeView(webView)
            webView!!.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}