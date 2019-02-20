package com.lhd.mutils.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;

import java.util.List;

/*******************************************
 * 作者： 刘海东
 * 时间： 2019/2/15
 * 类名：com.lhd.mutils.utils.AppUtil
 * 说明：
 ********************************************
 */
public class AppUtil {
    private volatile static AppUtil appUtil;
    private AppUtil(){}
    public static AppUtil getInstance(){

        if(appUtil==null){
            synchronized (AppUtil.class){
                if(appUtil==null){
                    appUtil=new AppUtil();
                }
            }
        }
        return appUtil;
    }
    public void openApp(Context context,String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();

            packageManager.getInstalledApplications(0);
            PackageInfo pi = packageManager.getPackageInfo(packageName, 0);

            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(pi.packageName);

            List<ResolveInfo> apps = packageManager.queryIntentActivities(resolveIntent, 0);

            ResolveInfo ri = apps.iterator().next();
            if (ri != null ) {
                String packagen = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);

                ComponentName cn = new ComponentName(packagen, className);

                intent.setComponent(cn);
                context.startActivity(intent);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

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
     * 判断当前APP是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public  boolean isAppInstalled(Context context, String packageName) {
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
    public  PackageInfo getPackageInfo(Context context, String packageName) {
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
    public  int getVersionCode(Context context, String packageName) {
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
    public  String getVersionName(Context context, String packageName) {
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
    public  String getAppLable(Context context, String packageName) {
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
