package com.example.rnpluginfg.textFragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.rnpluginfg.R
import com.example.rnpluginfg.databinding.FragmentTableBinding
import com.example.rnpluginfg.utils.FileTools
import com.chaquo.python.Kwarg;
import com.chaquo.python.PyObject;
import com.chaquo.python.android.AndroidPlatform;
import com.chaquo.python.Python;
import com.example.openvoicepn.OpenVoiceActivity
import com.example.rnpluginfg.fgopencv.PluginHttpcv
import com.example.rnpluginfg.pluginHttpGet.DownLoadUtils
import com.example.rnpluginfg.pluginHttpGet.WeChatQRCodeActivity
import com.example.rnpluginfg.utils.PKJChannel
import com.king.camera.scan.CameraScan
import com.king.wechat.qrcode.scanning.WeChatCameraScanActivity

/**
 * @param 初步尝试在此页面实现语音识别转换功能 ————测试页面fragment
 */
class TableFragment : Fragment() {

    companion object {
        fun newInstance() = TableFragment()
    }

    private lateinit var viewModel: TableViewModel
    private lateinit var binding:FragmentTableBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTableBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TableViewModel::class.java)
        initView()
    }
    fun initView(){
        binding.testbutton.setOnClickListener {
            val intent = Intent(requireActivity(),WeChatQRCodeActivity::class.java)
            startActivityForResult(intent,-1)
        }
        /**
         * 实现下载插件,添加到grideview中
         * 学习broadcastview  "http://lc-PksCkBWu.cn-n1.lcfile.com/U5l0Mvu58YNgozbvKFCia3bVtgwAJzft/plugintext.zip"
         */
        binding.button.setOnClickListener {
            PluginHttpcv.newInstance().opencvRunPlugin(activity?.applicationContext,"http://lc-PksCkBWu.cn-n1.lcfile.com/U5l0Mvu58YNgozbvKFCia3bVtgwAJzft/plugintext.zip",Environment.getExternalStorageDirectory().path.toString()+"/zzz/plugintext.zip")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == -1){
            val url = data?.getStringExtra(CameraScan.SCAN_RESULT)
            /**
             * 模拟添加icon,设置按键可见
             */
            binding.voice.apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    /**
                     * 模拟插件进入，实现下载插件等功能，目前先进行模拟功能实现
                     */
                    val intent = Intent(requireActivity(),OpenVoiceActivity::class.java)
                    startActivityForResult(intent,101)
                }
            }
        }

    }



}