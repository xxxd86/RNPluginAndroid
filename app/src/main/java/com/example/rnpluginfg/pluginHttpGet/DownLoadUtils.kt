package com.example.rnpluginfg.pluginHttpGet

import android.util.Log
import com.example.rnpluginfg.retrofit.DownLoadListener
import com.example.rnpluginfg.retrofit.DownLoadService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.concurrent.Executors


object DownLoadUtils {
    val sBufferSize  = 8192

    fun download(url:String,path:String,downloadListener:DownLoadListener){
          val retrofit = Retrofit.Builder()
              .baseUrl("http://example.com/")
              //通过线程池获取一个线程，指定callback在子线程中运行。
              .callbackExecutor(Executors.newSingleThreadExecutor())
              .build()
        val service = retrofit.create(DownLoadService::class.java)
        val call = service.download(url)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                /**
                 * 转入文件字节流  response.body()?.byteStream()  response.body().contentLength(),
                 */
                //将Response写入到从磁盘中，详见下面分析
                //注意，这个方法是运行在子线程中的
                if(response.isSuccessful){
                    Log.v("tggg","OK")
//                    writeResponseToDisk(path, response, downloadListener);
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable){
                downloadListener.onFail("网路错误")
            }

        })
    }
    fun writeResponseToDisk(path: String,response: Response<ResponseBody>,downloadListener: DownLoadListener){
        //从response获取输入流以及总大小
        writeFileFromIS(File(path),response.body()?.byteStream(),response.body()?.contentLength(),downloadListener)

    }
    fun writeFileFromIS(file:File,mis:InputStream?,totalLength:Long?,downloadListener: DownLoadListener){
        downloadListener.onStart()
        if (!file.exists()) {
            if (!file.parentFile?.exists()!!) file.parentFile?.mkdir()
            try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
                downloadListener.onFail("createNewFile IOException")
            }
        }
        var os: OutputStream? = null
        var currentLength: Long = 0

        try {
            os = BufferedOutputStream(FileOutputStream(file))
            val data = ByteArray(sBufferSize)
            var len: Int = 0
            while (mis?.read(data, 0, sBufferSize).also {
                    if (it != null) {
                        len = it
                    }
                } != -1) {
                os.write(data, 0, len)
                currentLength += len.toLong()
                //计算当前下载进度
                downloadListener.onProgress((100 * currentLength / totalLength!!).toInt())
            }
            //下载完成，并返回保存的文件路径
            downloadListener.onFinish(file.absolutePath)
        } catch (e: IOException) {
            e.printStackTrace()
            downloadListener.onFail("IOException")
        } finally {
            try {
                mis?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            try {
                os?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }
}
