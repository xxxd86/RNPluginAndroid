package com.example.rnpluginfg.textFragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
import com.example.rnpluginfg.pluginHttpGet.WeChatQRCodeActivity
import com.example.rnpluginfg.utils.PKJChannel
import com.king.wechat.qrcode.scanning.WeChatCameraScanActivity

/**
 * @param 初步尝试在此页面实现语音识别转换功能 ————测试页面
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
            startActivityForResult(intent,101)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == WeChatCameraScanActivity.RESULT_OK){
            if (data != null) {
                Toast.makeText(activity,data.data.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }



}