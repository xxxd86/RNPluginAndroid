package com.example.rnpluginfg.utils

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

/**
 * 页面控制以及权限控制
 */
object DeviceUtils {
    /**
     * 整个页面都是图片，隐藏状态栏和底部导航栏。
     */
    private fun setFullScreen(activity:AppCompatActivity?){
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        activity?.supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= 28) {
            val params = activity?.window?.attributes
            params?.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            activity?.window?.attributes = params
        }
    }

    /**
     * 透明状态栏
     */
    private fun setTransTableScreen(activity:AppCompatActivity?){
        activity?.window?.statusBarColor = Color.TRANSPARENT;
    }
    fun setScreen(screenState: ScreenState,activity:AppCompatActivity?){
        when(screenState){
            ScreenState.STATE_FULL -> setFullScreen(activity)
            ScreenState.STATE_PAT  -> setTransTableScreen(activity)
            else -> {}
        }
    }
    fun getInternPermission(){

    }
    private fun lacksPermission(mContexts: Context, permission: String): Boolean {

        return ContextCompat.checkSelfPermission(mContexts, permission) ==
                PackageManager.PERMISSION_DENIED
    }
    fun checkLocationPermission(activity: AppCompatActivity,mPermissions:String,lackPermission:()->Unit,havePermission:()->Unit) {
        if (lacksPermission(activity.applicationContext,mPermissions)) {
            /*
            缺少精确定位，提醒开启精确定位
             */
//            ToastUtil.show(activity.applicationContext, "请选择精确位置")
            lackPermission.invoke()
//            AmapTools.getLocationPermission(activity)
        } else {
            //权限开启
            havePermission.invoke()
        }
    }
}

/**
 * @param 设置屏幕的状态
 * 主要分为三个状态
 * 1.全屏 —— 广告加载
 * 2.状态栏符合最近的颜色状态
 * 3.普通状态
 */
enum class ScreenState{
      STATE_FULL,STATE_PAT,STATE_COM
}