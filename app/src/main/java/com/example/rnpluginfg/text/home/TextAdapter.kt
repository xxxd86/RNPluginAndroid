package com.example.rnpluginfg.text.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.asbaselibrary.base.baseAdapter.BaseAdapter
import com.example.asbaselibrary.base.baseAdapter.BaseViewHolder
import com.example.rnpluginfg.databinding.TextitemBinding

/**
 * @property 测试Adapter,绑定item viewbinding
 */
class TextAdapter: BaseAdapter<TextitemBinding, TextAdapter.FriendViewHolder>(TextitemBinding::inflate) {
    private var data = mutableListOf<Friends?>()

    override fun onBind(holder: FriendViewHolder, position: Int) {
        holder.binding.textview.text = "你好"
        holder.setImage(holder.binding.image,data.getOrNull(position)?.src)
    }

    override fun createViewHolder(binding: TextitemBinding): FriendViewHolder {
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        onBind(holder,position)
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
    class FriendViewHolder(binding: TextitemBinding):
        BaseViewHolder<TextitemBinding>(binding){

    }

}
