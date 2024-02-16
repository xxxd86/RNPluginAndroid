package com.example.rnpluginfg.pluginHttpGet

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

/**
 * @param 基础Okhttp3导入，需要对文件进行分类
 * @sample 首先进行插件网络化，尝试第一步，文件读取，并读取python,java或者c++程序，使用OKHttps
 */
class BaseOkHttp {
    val client = OkHttpClient()
    companion object{
        fun newInstance() = BaseOkHttp()
    }

    @Throws(IOException::class)
    fun run(url: String): String {
        val request: Request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).execute().use { response -> return response.body!!.string() }
    }
}