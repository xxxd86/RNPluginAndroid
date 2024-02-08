package com.example.rnpluginfg.home


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.rnpluginfg.R
import com.example.rnpluginfg.base.baseActivity.BaseActivityLate
import com.example.rnpluginfg.databinding.ActivityHomeBinding
import com.example.rnpluginfg.textFragment.HomeFragment
import com.example.rnpluginfg.textFragment.MyFragment
import com.example.rnpluginfg.textFragment.TableFragment


/**
 * @author wh
 * @param 为实现应用通用界面，底部导航栏，以及Fragment封装,期望实现map,add
 */
class HomeActivity : BaseActivityLate<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    val fragmentManager = supportFragmentManager;  //使用fragmentmanager和transaction来实现切换效果
    val transaction = fragmentManager.beginTransaction();
    private var homeFragment: HomeFragment? = null
    private var tableFragment:TableFragment? = null
    private var myFragment:MyFragment? = null
    private var contentId = -1
    private var lastShowFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    /**
     * 初始化BottomNavFragment
     */
    override fun initView(){
        contentId = binding.container.id

        initFirstFragment()

        binding.navBottom.setOnItemSelectedListener {item->
            if(item.itemId!=binding.navBottom.selectedItemId){
                when(item.itemId){
                    R.id.tab_home ->{
                        showFragment(homeFragment!!)
                    }
                    R.id.tab_friend ->{
                        if (tableFragment == null){
                            tableFragment = TableFragment.newInstance()
                            supportFragmentManager.beginTransaction()
                                .add(contentId,tableFragment!!,"TableFragment")
                                .commit()
                        }
                        showFragment(tableFragment!!)

                    }
                    R.id.tab_profile ->{
                        if (myFragment == null){
                            myFragment = MyFragment.newInstance()
                            supportFragmentManager.beginTransaction()
                                .add(contentId,myFragment!!,"TableFragment")
                                .commit()

                        }
                        showFragment(myFragment!!)
                    }
                }
                return@setOnItemSelectedListener true
            }
            false

        }
    }
    private fun showFragment(fragmentToShow: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.show(fragmentToShow)
        lastShowFragment?.let {
            transaction.hide(it)
        }
        lastShowFragment = fragmentToShow
        transaction.commit()
    }
    private fun initFirstFragment() {
        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .show(homeFragment!!)
            .add(contentId, homeFragment!!, "HomeFragment")
            .commit()
        lastShowFragment = homeFragment
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        intent.data
    }
}