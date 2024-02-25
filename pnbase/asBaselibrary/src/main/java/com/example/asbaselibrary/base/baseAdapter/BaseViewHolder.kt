package com.example.asbaselibrary.base.baseAdapter

import android.content.Context
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.asbaselibrary.utils.DpUtils

/**
 * 是否使用懒加载可使用 activity已经实现懒加载，无需再使用
 */
abstract class BaseViewHolder<T>(private val layoutBinding: ViewBinding):RecyclerView.ViewHolder(layoutBinding.root) {
    open var data:MutableList<T?> = mutableListOf()
    open fun bind(position: Int):T?{
          return data.getOrNull(position)
    }




    /**
     * @param 获取HolderContext
     */
    fun getHolderContext(): Context {
        return layoutBinding.root.context
    }

    /**
     * @param 设置item的图片资源 , 默认defaultOption
     */
    fun setImage(view: ImageView?, url: String?,  options: RequestOptions? = null, default: Boolean = true) {

        if (url.isNullOrEmpty() || view == null) {
            return
        }
        if(default){
            val moptions =  RequestOptions().transform(
                CenterCrop(),
                RoundedCorners(DpUtils.dp2px(layoutBinding.root.context, 25)))
            Glide.with(layoutBinding.root)
                .load(url)
                .apply(moptions)
                .into(view)
        }else{
            if (options != null) {
                Glide.with(layoutBinding.root)
                    .load(url)
                    .apply(options)
                    .into(view)
            }
        }

    }

    /**
     * @param 初始化Lists
     */

    open fun setDataLists(mdata:MutableList<T?>){
         data = mdata
    }


}
