package com.example.rnpluginfg.text.home

import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asbaselibrary.base.baseActivity.BaseActivityLate
import com.example.rnpluginfg.databinding.ActivityTestBinding
import com.example.rnpluginfg.databinding.TextitemBinding

/**
 * 测试activity
 */
class TestActivity : BaseActivityLate<ActivityTestBinding>(ActivityTestBinding::inflate) {
    private  var TextAdapter:TextAdapter? = null
    private val FriendsLists = arrayListOf<Friends>()
    override fun initView() {
        super.initView()
        FriendsLists.add(Friends("https://th.bing.com/th/id/OIP.00HEmqYJSK44tQgKfX9dWAHaEo?rs=1&pid=ImgDetMain","你好"))
        FriendsLists.add(Friends("https://th.bing.com/th/id/OIP.00HEmqYJSK44tQgKfX9dWAHaEo?rs=1&pid=ImgDetMain","你好"))
        FriendsLists.add(Friends("https://th.bing.com/th/id/OIP.00HEmqYJSK44tQgKfX9dWAHaEo?rs=1&pid=ImgDetMain","你好"))
        TextAdapter = TextAdapter(
            this).apply {
            setData(FriendsLists)
            setOnclickListener {
                Toast.makeText(this@TestActivity,"你好",Toast.LENGTH_SHORT).show()
            }
        }
        binding.textecycle.apply {
            adapter = TextAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}