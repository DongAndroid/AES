package com.lhd.mutils.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import java.util.List;

/**
 * @author: 刘海东
 * @date: 2019/02/13
 * @desc: APP 基础信息工具类
 * @version:
 * @update:
 */

public class DeviceInfoUtil {

    private static volatile DeviceInfoUtil deviceInfoUtil;
    private DeviceInfoUtil(){}
    public static DeviceInfoUtil getInstance(){
        if(deviceInfoUtil==null){
            synchronized (DeviceInfoUtil.class){
                if(deviceInfoUtil==null){
                    deviceInfoUtil=new DeviceInfoUtil();
                }
            }
        }
        return deviceInfoUtil;
    }
    /**
     * 获取当前设备安装的APP
     *
     * @param context
     * @return
     */
    public  List<PackageInfo> getDeviceApp(Context context) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packageInfos = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        return packageInfos;
    }
    /**
     * 获取当前设备版本
     *

     * @return
     */
    public int getBuild_VERSION(){
        return Build.VERSION.SDK_INT;
    }

}
