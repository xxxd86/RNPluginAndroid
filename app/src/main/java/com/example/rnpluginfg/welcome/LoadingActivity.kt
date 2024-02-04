package com.example.rnpluginfg.welcome

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.rnpluginfg.R
import com.example.rnpluginfg.base.baseActivity.BaseActivityLate
import com.example.rnpluginfg.base.baseActivity.BaseLoadingActivity
import com.example.rnpluginfg.databinding.ActivityLoadingBinding

class LoadingActivity : BaseLoadingActivity<LoadingViewModel, ActivityLoadingBinding>() {
    override fun getViewBinding(): ViewBinding  = ActivityLoadingBinding.inflate(layoutInflater)

}