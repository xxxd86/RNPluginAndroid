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
    private val FriendsLists = arrayListOf<Friends>()
    private val madapter by lazy { TextAdapter().apply {
        setData(FriendsLists)
    }}
    override fun initView() {
        super.initView()
        FriendsLists.add(Friends("https://th.bing.com/th/id/R.466bb61cd7cf4e8b7d9cdf645add1d6e?rik=YRZKRLNWLutoZA&riu=http%3a%2f%2f222.186.12.239%3a10010%2fwmxs_161205%2f002.jpg&ehk=WEy01YhyfNzzQNe1oIqxwgbTnzY7dMfmZZHkqpZB5WI%3d&risl=&pid=ImgRaw&r=0","你好"))
        FriendsLists.add(Friends("https://th.bing.com/th/id/R.466bb61cd7cf4e8b7d9cdf645add1d6e?rik=YRZKRLNWLutoZA&riu=http%3a%2f%2f222.186.12.239%3a10010%2fwmxs_161205%2f002.jpg&ehk=WEy01YhyfNzzQNe1oIqxwgbTnzY7dMfmZZHkqpZB5WI%3d&risl=&pid=ImgRaw&r=0","你好"))
        FriendsLists.add(Friends("https://th.bing.com/th/id/R.466bb61cd7cf4e8b7d9cdf645add1d6e?rik=YRZKRLNWLutoZA&riu=http%3a%2f%2f222.186.12.239%3a10010%2fwmxs_161205%2f002.jpg&ehk=WEy01YhyfNzzQNe1oIqxwgbTnzY7dMfmZZHkqpZB5WI%3d&risl=&pid=ImgRaw&r=0","你好"))
//        TextAdapter = TextAdapter(
//            this).apply {
//            setData(FriendsLists)
//            setOnclickListener {
//                Toast.makeText(this@TestActivity,"你好",Toast.LENGTH_SHORT).show()
//            }
//        }
        binding.textecycle.apply {
            adapter = madapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}