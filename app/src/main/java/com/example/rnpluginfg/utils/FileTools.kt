package com.example.rnpluginfg.utils

import android.app.Activity
import android.content.Intent
import java.io.File

/**
 * @param 日常读取文件，打开文件页面，图片页面
 *
 */
object FileTools {
    fun writeToFile_OneLine(file:File,words:String){
        file.printWriter().use { out ->
            out.println(words)
        }
    }
    fun intentToFileReturn(activity: Activity, fileType:String, fileNumber:Int, callBack:()->Unit){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("*/*")//fileType
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        activity.startActivityForResult(intent,101)
    }

}