package com.example.asbaselibrary.base.baseAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.example.asbaselibrary.base.DeviceChange

/**
 * 设置Lists的class以及设置binding
 * T 为 绑定List
 */
abstract class BaseAdapter<B : ViewBinding?, BH : ViewHolder>(val bindingFactory: (LayoutInflater) -> B,val activity: AppCompatActivity) :
    RecyclerView.Adapter<BH>(), DeviceChange {

    private var layoutInflater: LayoutInflater? = null
    val binding: B? by lazy { layoutInflater?.let { bindingFactory(it) } }
    open var itemClick:(()->Unit?)? = null //需要外接点击接口,自行调用
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BH {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        val itemBinding = binding ?: throw IllegalStateException("Binding cannot be null")

        // Inflate the view using the provided bindingFactory
        val itemView = itemBinding.root

        // Create and return an instance of your BaseViewHolder
        return createViewHolder(itemView, itemBinding)
    }

    // Implement this method in your derived class to create an instance of BaseViewHolder
    open fun createViewHolder(itemView: View, itemBinding: B): BH {
        // You should implement this method in your derived adapter class
        throw NotImplementedError("createViewHolder must be implemented in the derived class")
    }

    /**
     * 设置外部点击时间，无关参数
     */
    open fun setOnclickListener(listener:()->Unit?){
        itemClick = listener
    }

    /**
     * @param 设置跳转点击
     */
    open fun setStartActivityForResult(clazz: Any){
        val intent = Intent(activity,clazz::class.java)
        activity.startActivityForResult(intent,101)
    }


    // Other adapter methods can be implemented here...
}
