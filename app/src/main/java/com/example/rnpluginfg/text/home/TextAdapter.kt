package com.example.rnpluginfg.text.home

import android.annotation.SuppressLint
import com.example.asbaselibrary.base.baseAdapter.BaseAdapter
import com.example.rnpluginfg.databinding.TextitemBinding
import java.lang.Exception

/**
 * @property 测试Adapter,绑定item viewbinding
 */
class TextAdapter: BaseAdapter<TextitemBinding, TextAdapter.FriendViewHolder>(TextitemBinding::inflate) {
    @Volatile
    var madapter: TextAdapter? = null

    fun getInstance(list: List<Friends>): TextAdapter {
        if(madapter == null) {
            synchronized(TextAdapter::class.java){
                if (madapter == null){
                    val adapter by lazy { TextAdapter().apply {
                        setData(list)
                    }}
                    madapter = adapter
                    return madapter as TextAdapter
                }
            }
        }
        throw Exception()
    }


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
    inner class FriendViewHolder(binding: TextitemBinding):
        BaseViewHolder<TextitemBinding>(binding)

}
