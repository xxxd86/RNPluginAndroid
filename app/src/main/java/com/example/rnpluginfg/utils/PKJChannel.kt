package com.example.rnpluginfg.utils

import android.content.Context
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

/**
 * @param 为实现JAVA,KOTLIN,C++,PYTHON方法进行互相调用所使用的一个东西
 *
 */
object PKJChannel {
    /**
     * @获取Python回调
     */
    fun getRFromPython_KOTLIN(callback:(PyObject)->Unit,context: Context){
         // 初始化Python环境
        if (!Python.isStarted()){
            Python.start( AndroidPlatform(context));
        }
        val python=Python.getInstance(); // 初始化Python环境
        val pyObject=python.getModule("text");//"text"为需要调用的Python文件名
        val res=pyObject.callAttr("sayHello");//"sayHello"为需要调用的函数名
        callback.invoke(res)
    }

}