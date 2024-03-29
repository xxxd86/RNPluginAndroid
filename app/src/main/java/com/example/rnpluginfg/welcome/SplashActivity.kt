package com.example.rnpluginfg.welcome

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.window.SplashScreen
import androidx.viewbinding.ViewBinding
import com.example.rnpluginfg.MainActivity
import com.example.rnpluginfg.Manifest
import com.example.rnpluginfg.base.baseActivity.BaseLoadingActivity
import com.example.rnpluginfg.databinding.ActivitySplashBinding
import com.example.rnpluginfg.utils.DeviceUtils


/**
 * 需求：
 * 在App应用退出后（此时App是在后台运行的，并不是进程被杀死），每当用户再次将该应用切换到前台显示时，总能向用户展示3S的广告页。
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseLoadingActivity<SplashViewModel,ActivitySplashBinding>() {
    private val AD_TIMEOUT = 2000
    private val MSG_AD_TIMEOUT = 1001

    // 收到广告展示超时消息时的回调处理
    private val timeoutHandler = Handler(object:Handler.Callback{
        override fun handleMessage(p0: Message): Boolean {
            if (hasWindowFocus()) {
                jump();
            }
            return false;
        }
    })
    /**
     * 暂停标志位。
     * 在开屏广告页面展示时：
     * 按返回键退出应用时需设置为true，以确保应用主界面不被拉起；
     * 切换至其他界面时需设置为false，以确保从其他页面回到开屏广告页面时仍然可以正常跳转至应用主界面；
     */
    private var  hasPaused = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadAd()
    }
    private fun jump(){
        if (!hasPaused) {
            hasPaused = true;
            val intent = Intent(this,MainActivity::class.java)
            startActivityForResult(intent,101)
            finish();
        }

    }
    private fun loadAd(){
        val orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//获取当前的状态

        // 发送延时消息，保证广告显示超时后，APP首页可以正常显示
        timeoutHandler.removeMessages(MSG_AD_TIMEOUT);
        timeoutHandler.sendEmptyMessageDelayed(MSG_AD_TIMEOUT, AD_TIMEOUT.toLong());
    }

    override fun onStop() {
        // 移除消息队列中等待的超时消息
        timeoutHandler.removeMessages(MSG_AD_TIMEOUT);
        hasPaused = true;
        super.onStop();
    }

    override fun onRestart() {
        super.onRestart();
        hasPaused = false;
        jump();
    }
    fun getResourceFromIntern(){
        DeviceUtils.checkLocationPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION,
            lackPermission = {
                /**
                 * 获取网络
                 */
            },
            havePermission = {
                /**
                 * 进行操作
                 */
            }

            )
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    override fun getViewBinding(): ViewBinding  =  ActivitySplashBinding.inflate(layoutInflater)
}