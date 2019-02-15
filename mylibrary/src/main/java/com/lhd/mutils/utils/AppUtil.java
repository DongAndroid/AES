package com.lhd.mutils.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

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
}
