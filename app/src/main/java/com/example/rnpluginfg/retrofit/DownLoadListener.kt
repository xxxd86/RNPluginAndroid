package com.example.rnpluginfg.retrofit

interface DownLoadListener {
    fun onStart() //下载开始


    fun onProgress(progress: Int) //下载进度


    fun onFinish(path: String?) //下载完成


    fun onFail(errorInfo: String?) //下载失败

}