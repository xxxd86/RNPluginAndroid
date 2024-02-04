package com.example.rnpluginfg.base

interface DeviceChange {
    fun onNightChange(onSuccess:() ->Unit):Boolean
    fun onLanguageChange(onSuccess: () -> Unit):Boolean
}