package com.example.voiceapplication.base

interface DeviceChange {
    fun onNightChange(onSuccess:() ->Unit):Boolean
    fun onLanguageChange(onSuccess: () -> Unit):Boolean
}