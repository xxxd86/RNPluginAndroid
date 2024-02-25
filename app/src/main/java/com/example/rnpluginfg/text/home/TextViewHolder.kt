package com.example.rnpluginfg.text.home

import android.content.Intent
import com.example.asbaselibrary.base.baseAdapter.BaseViewHolder
import com.example.rnpluginfg.databinding.TextitemBinding

class TextViewHolder( val binding: TextitemBinding) : BaseViewHolder<Friends>(binding) {

    //绑定Bind
    override fun bind(position: Int): Friends? {
        super.bind(position).apply {
            setImage(binding.image,this?.src)
            binding.textview.text = this?.name
            binding.image.setOnClickListener {

            }
        }
        return null

    }


    /**
     * 为mdata设置一些信息
     */
    override fun setDataLists(mdata: MutableList<Friends?>) {
        super.setDataLists(mdata)
    }

}

