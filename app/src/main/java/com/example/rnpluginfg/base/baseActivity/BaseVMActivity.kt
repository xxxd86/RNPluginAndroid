package com.example.rnpluginfg.base.baseActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.rnpluginfg.base.baseViewModel.BaseViewModel
import java.lang.reflect.Modifier
import java.lang.reflect.ParameterizedType

/**
 * MVVM框架基础
 */
abstract class BaseVMActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {
    lateinit var viewModel: VM
    lateinit var binding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(getViewModelClass())
        binding = getViewBinding()
        setContentView(binding.root)
    }

    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<VM>
    }

    abstract fun getViewBinding(): VB
}
