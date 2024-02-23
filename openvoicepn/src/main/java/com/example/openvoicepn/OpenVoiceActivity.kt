package com.example.openvoicepn

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.asbaselibrary.base.baseActivity.BaseActivityLate
import com.example.asbaselibrary.utils.DeviceUtils
import com.example.openvoicepn.databinding.ActivityOpenVoiceBinding
import com.example.openvoicepn.utils.OnlyAudioRecorder
import com.example.openvoicepn.utils.PKJChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * @param 为实现OpenVoice Python嵌入工作,并实现PluginActivity底层框架
 */
class OpenVoiceActivity : BaseActivityLate<ActivityOpenVoiceBinding>(ActivityOpenVoiceBinding::inflate){
    override fun initView() {
        super.initView()
        val audioRecord = OnlyAudioRecorder.instance
        DeviceUtils.checkLocationPermission(this, Manifest.permission.RECORD_AUDIO, lackPermission = {
            Toast.makeText(this,"请开启权限",Toast.LENGTH_SHORT).show()
        }, havePermission = {
            Toast.makeText(this,"权限已开启",Toast.LENGTH_SHORT).show()
        })
        binding.start.setOnClickListener {
            audioRecord.startRecord()//开始录音,保存路径为/storage/emulated/0/zzz
        }
        binding.end.setOnClickListener {
            audioRecord.stopRecord()//停止录音 保存路径为/storage/emulated/0/zzz
        }
        /**
         * @param 实现调用python-openvoice，使用输入文字进行转换
         */
        binding.generntext.setOnClickListener {
            /**
             * 测试
             * 需要实现传入文字然后转换为个体声音
             * @exception chaquopy 因缺乏相关库的支持所以目前无法使用  先搁置
             * @param 先实现音频的传入
             */
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    PKJChannel.getRFromPython_KOTLIN("tor","text",{
                         Log.v("TAGOVICESOURCE",it.toString())
                    },binding.editTextText.text,this@OpenVoiceActivity)
                }
            }

        }
        /**
         * @param 实现读取声音然后转换
         */
        binding.genernown.setOnClickListener {

        }
    }

}