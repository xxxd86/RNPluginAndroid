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
     * @param 获取Python回调，目前使用于
     * @sample AI音频，AI聊天等与AI相关的内容
     */
    fun getRFromPython_KOTLIN(module:String,method:String,callback:(PyObject)->Unit,context: Context){
         // 初始化Python环境
        if (!Python.isStarted()){
            Python.start( AndroidPlatform(context));
        }
        val python=Python.getInstance(); // 初始化Python环境
        val pyObject=python.getModule(module);//"text"为需要调用的Python文件名
        val res=pyObject.callAttr(method);//"sayHello"为需要调用的函数名
        callback.invoke(res)
    }
    /**
     * @param 获取C++数据，关于OPENVR，OPENGL内容的转换,以及json推流操作
     */
    fun getRFromC_Kotlin(model:String,method:String,callback: (Any) -> Unit,context: Context){

    }
    /**
     * 有参获取class,
     */
    private fun object_Loader_Class(root:Any): Class<*> {
        val clazz2: Class<*> = root.javaClass
        return clazz2;
    }
    /**
     * 有参获取class method,
     */
    private fun object_Loader_Class_method(root:Any,methodName:String){
        val class_temp = object_Loader_Class(root)
        val newInstance: Any = class_temp.newInstance()
        val start = class_temp.getMethod(methodName);
        start.invoke(newInstance);
    }
    /**
     * root ClassLoader获取class
     */
    private fun root_Loader_Class(root:String): Class<*>? {
        val classLoader: ClassLoader = javaClass.classLoader as ClassLoader
        val aClass3 = classLoader.loadClass(root)
        return aClass3;
    }
    /**
     *  root ClassLoader 获取class method,
     */
    private fun root_Loader_Class_method(root:String,methodName:String){
        val class_temp = root_Loader_Class(root)
        val newInstance: Any? = class_temp?.newInstance()
        val start = class_temp?.getMethod(methodName);
        if (start != null) {
            start.invoke(newInstance)
        };
    }

}