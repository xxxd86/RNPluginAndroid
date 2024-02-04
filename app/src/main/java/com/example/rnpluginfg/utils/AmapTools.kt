package com.example.rnpluginfg.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object AmapTools {
    private const val mPermissions =
        Manifest.permission.ACCESS_FINE_LOCATION

    /**
     * 判断是否缺少权限
     */
    private fun lacksPermission(mContexts: Context, permission: String): Boolean {

        return ContextCompat.checkSelfPermission(mContexts, permission) ==
                PackageManager.PERMISSION_DENIED
    }

    /*
    请求精确定位
     */
    fun checkLocationPermission(activity: AppCompatActivity) {
        if (lacksPermission(activity.applicationContext, mPermissions)) {
            /*
            缺少精确定位，提醒开启精确定位
             */
//            ToastUtil.show(activity.applicationContext, "请选择精确位置")
            getLocationPermission(activity)
        } else {
            //权限开启

        }
    }
    private fun getLocationPermission(activity: AppCompatActivity) {
        val locationPermissionRequest = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }

                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
//                    ToastUtil.show(activity.applicationContext, "请选择精确位置")
                }

                else -> {
                    // No location access granted.
                }
            }
        }
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    /**
     * 获取定位信息
     * @author wh
     * @sample 默认获取高德位置 isAmap = true
     */
    fun getCurrentLocationLatLng(
        applicationContext: Context,
        isAmap: Boolean = true,
        onSuccess: (Double, Double, String) -> Unit = { _: Double, _: Double, _: String -> },
        onError: (String) -> Unit = {}
    ) {
        AMapLocationClient.updatePrivacyAgree(applicationContext, true)
        AMapLocationClient.updatePrivacyShow(applicationContext, true, true)
        //初始化定位
        val mLocationClient = AMapLocationClient(applicationContext)
        var mLocationOption: AMapLocationClientOption? = null
        //设置定位回调监听
        mLocationClient.setLocationListener { amapLocation ->
            if (amapLocation != null) {
                if (amapLocation.errorCode == 0) {
                    val address =
                        amapLocation.country + " " + amapLocation.address
                    if (isAmap){
                        onSuccess(amapLocation.longitude, amapLocation.latitude,address)
                    }else{
                        val latLng = mapPointGdTurnBaiDu(amapLocation.longitude, amapLocation.latitude)
                        onSuccess(latLng.latitude, latLng.longitude, address)
                    }
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    onError("location Error, ErrCode:" + amapLocation.errorCode + ", errInfo:" + amapLocation.errorInfo)
                }
            }
        }
        //初始化AMapLocationClientOption对象
        mLocationOption = AMapLocationClientOption()
        mLocationOption.locationMode =
            AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        // 设置为单次定位  : 默认为false
        mLocationOption.isOnceLocation = true
        mLocationOption.isLocationCacheEnable = true
        mLocationOption.httpTimeOut = 20000
        mLocationClient.setLocationOption(mLocationOption)
        //启动定位
        mLocationClient.startLocation()
    }
    fun mapPointGdTurnBaiDu(lon: Double, lat: Double): BDLating {
        val pi = 3.14159265358979324
        val z = sqrt(lon * lon + lat * lat) + 0.00002 * sin(lat * pi)
        val theta = atan2(lat, lon) + 0.000003 * cos(lon * pi)
        val bdLon = z * cos(theta) + 0.0065
        val bdLat = z * sin(theta) + 0.006
        return BDLating(bdLat, bdLon)
    }
    fun mapPointBaiduTurnDG(lon: Double, lat: Double): BDLating {
        val pi = 3.14159265358979324
        val z = sqrt(lon * lon + lat * lat) - 0.00002 * sin(lat * pi)
        val theta = atan2(lat, lon) - 0.000003 * cos(lon * pi)
        val bdLon = z * cos(theta) - 0.0065
        val bdLat = z * sin(theta) - 0.006
        return BDLating(bdLat, bdLon)
    }
    class BDLating( val latitude: Double ,val longitude: Double)

}