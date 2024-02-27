package com.example.rnpluginfg.text.home

import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.asbaselibrary.base.baseActivity.BaseWebActivity
import com.example.rnpluginfg.databinding.ActivityTestBinding
import com.example.rnpluginfg.textFragment.HomeViewModel


/**
 * 测试activity
 */
class TestActivity : BaseWebActivity<HomeViewModel,ActivityTestBinding>(ActivityTestBinding::inflate) {
    override fun initView() {
         super.initView()
//        binding.wvWebview.loadUrl("http://www.baidu.com")

////重写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
//
//
////重写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
//        binding.wvWebview.setWebViewClient(object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//                view.loadUrl(url)
//                return true
//            }
//        })
         setWebView(binding.wvWebview)
         loadUrl("http://www.baidu.com")
    }

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