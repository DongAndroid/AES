package com.lhd.mutils.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
    public static List<PackageInfo> getDeviceApp(Context context) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packageInfos = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        return packageInfos;
    }


    /**
     * 判断当前APP是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);

            return packageInfo != null;
        } catch (Exception e) {
            System.out.println("判断APP是否安装失败===>>信息" + e.getMessage());
        }
        return false;
    }


    /**
     * 获取APP信息
     *
     * @param context
     * @param packageName
     * @return
     */
    public static PackageInfo getPackageInfo(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);

            return packageInfo;
        } catch (Exception e) {
            System.out.println("获取APP失败信息" + e.getMessage());
        }

        return null;
    }

    /**
     * 获取版本号
     *
     * @param context
     * @param packageName
     * @return
     */
    public static int getVersionCode(Context context, String packageName) {
        PackageInfo packageInfo = getPackageInfo(context, packageName);
        if (packageInfo != null) {
            return packageInfo.versionCode;
        }
        return -1;
    }

    /**
     * 获取版本名称
     *
     * @param context
     * @param packageName
     * @return
     */
    public static String getVersionName(Context context, String packageName) {
        PackageInfo packageInfo = getPackageInfo(context, packageName);
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return "";
    }


    /**
     * 获取APP名称
     *
     * @param context
     * @param packageName
     * @return
     */
    public static String getAppLable(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        PackageManager pm = context.getPackageManager();

        PackageInfo packageInfo = getPackageInfo(context, packageName);

        if (packageInfo != null) {
            return packageInfo.applicationInfo.loadLabel(pm).toString();
        }

        return null;
    }

}
