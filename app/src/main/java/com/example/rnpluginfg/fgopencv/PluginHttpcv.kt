package com.example.rnpluginfg.fgopencv
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.rnpluginfg.pluginHttpGet.DownLoadUtils
import com.example.rnpluginfg.retrofit.DownLoadListener
import com.example.rnpluginfg.utils.PKJChannel
/**
 * @param 为实现二维码扫描后，进行插件下载，本质为文件下载，然后对插件进行分析
 * @sample 使用opencv-python进行读取
 */
class PluginHttpcv {
    companion object{
        fun newInstance() = PluginHttpcv()
    }
    fun opencvRunPlugin(context: Context?, url:String, path:String){
         DownLoadUtils.download(url,path,object :DownLoadListener{
             override fun onStart() {

             }

             override fun onProgress(progress: Int) {

             }

             override fun onFinish(path: String?) {
                 Log.v("TAGS","下载完成$path")
             }

             override fun onFail(errorInfo: String?) {
                 Log.v("TAGS","下载失败$errorInfo")
             }

         })
    }
}