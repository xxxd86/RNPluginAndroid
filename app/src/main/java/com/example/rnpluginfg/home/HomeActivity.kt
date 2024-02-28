package com.example.rnpluginfg.home


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.asbaselibrary.base.baseActivity.BaseActivityLate
import com.example.asbaselibrary.base.baseService.BaseRNService
import com.example.rnpluginfg.R
import com.example.rnpluginfg.databinding.ActivityHomeBinding
import com.example.rnpluginfg.textFragment.HomeFragment
import com.example.rnpluginfg.textFragment.MyFragment
import com.example.rnpluginfg.textFragment.TableFragment


/**
 * @author wh
 * @param 为实现应用通用界面，底部导航栏，以及Fragment封装,期望实现map,add
 * @param 实现文件访问权限
 */
class HomeActivity : BaseActivityLate<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    val fragmentManager = supportFragmentManager;  //使用fragmentmanager和transaction来实现切换效果
    val transaction = fragmentManager.beginTransaction();
    private var homeFragment: HomeFragment? = null
    private var tableFragment:TableFragment? = null
    private var myFragment:MyFragment? = null
    private var contentId = -1
    private var lastShowFragment: Fragment? = null
    private val REQUEST_CODE = 1024
    /**
     * 初始化BottomNavFragment
     */
    override fun initView(){
        super.initView()
        requestPermission()
        contentId = binding.container.id

        initFirstFragment()
        val intent = Intent(this, BaseRNService::class.java) //处理resource文件以及debugText文件的创立
        startService(intent)
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
    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 先判断有没有权限
            if (Environment.isExternalStorageManager()) {
                writeFile()
            } else {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.setData(Uri.parse("package:" + applicationContext.getPackageName()))
                startActivityForResult(intent, REQUEST_CODE)
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 先判断有没有权限
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                writeFile()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf<String>(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    REQUEST_CODE
                )
            }
        } else {
            writeFile()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                writeFile()
            } else {
                Toast.makeText(this,"写入失败",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                writeFile()
            } else {
                Toast.makeText(this,"写入失败",Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * 模拟文件写入
     */
    private fun writeFile() {
        Toast.makeText(this,"写入成功",Toast.LENGTH_SHORT).show()
    }

}