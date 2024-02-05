package com.example.rnpluginfg.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rnpluginfg.R
import com.example.rnpluginfg.base.baseActivity.BaseActivity
import com.example.rnpluginfg.base.baseActivity.BaseActivityLate
import com.example.rnpluginfg.databinding.ActivityHomeBinding
import com.example.rnpluginfg.textFragment.MyFragment

/**
 * @author wh
 * @param 为实现应用通用界面，底部导航栏，以及Fragment封装,期望实现map,add
 */
class HomeActivity : BaseActivityLate<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .add(binding.fragmentContainer.id,MyFragment::class.java,null)
            .commit()
    }

    /**
     * 初始化Fragment
     */
    fun initView(){

    }

}