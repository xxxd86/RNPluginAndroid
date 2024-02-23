package com.example.asbaselibrary.base

interface DeviceChange {
    fun onNightChange(onSuccess:() ->Unit):Boolean
    fun onLanguageChange(onSuccess: () -> Unit):Boolean

    fun onScreanChange(onSuccess: () -> Unit)
}