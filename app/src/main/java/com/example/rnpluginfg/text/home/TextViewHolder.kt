package com.example.rnpluginfg.text.home

import android.content.Intent
import com.example.asbaselibrary.base.baseAdapter.BaseViewHolder
import com.example.rnpluginfg.databinding.TextitemBinding

class FriendViewHolder(private val binding: TextitemBinding):BaseViewHolder<Friends>(binding){
    override fun bind(position: Int, t: Friends?) {
        setImage(binding.image,t?.src, default = true)
        binding.textview.text = t?.name
    }
}

