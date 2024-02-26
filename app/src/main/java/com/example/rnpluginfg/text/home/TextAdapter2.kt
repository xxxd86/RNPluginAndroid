package com.example.rnpluginfg.text.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rnpluginfg.databinding.TextitemBinding

class TextAdapter2 : RecyclerView.Adapter<FriendViewHolder>(){
    private var data = mutableListOf<Friends>()
    private var onItemClick: ((Friends) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TextitemBinding.inflate(inflater,parent,false)
        return  FriendViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(position, data.getOrNull(position))
    }
    fun setOnItemClickListener(listener: (Friends) -> Unit) {
        onItemClick = listener
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(d: List<Friends>) {
        data = ArrayList(d)
        notifyDataSetChanged()
    }
}