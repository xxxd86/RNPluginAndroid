package com.example.rnpluginfg.text.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.asbaselibrary.base.baseAdapter.BaseAdapter
import com.example.rnpluginfg.databinding.TextitemBinding

/**
 * @property 测试Adapter,绑定item viewbinding
 */
class TextAdapter(activity: AppCompatActivity) : BaseAdapter<TextitemBinding, TextViewHolder>(TextitemBinding::inflate,activity) {
    private var data = mutableListOf<Friends?>()
    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
          holder.bind(position)
          holder.setDataLists(data)
    }

    override fun setOnclickListener(listener: () -> Unit?) {
        itemClick = listener
    }

    override fun createViewHolder(itemView: View, itemBinding: TextitemBinding): TextViewHolder {
        return TextViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onNightChange(onSuccess: () -> Unit): Boolean {
        TODO("Not yet implemented")
    }

    override fun onLanguageChange(onSuccess: () -> Unit): Boolean {
        TODO("Not yet implemented")
    }

    override fun onScreanChange(onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(d: List<Friends>){
        data = ArrayList(d)
        notifyDataSetChanged()
    }



}
