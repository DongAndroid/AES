package com.lhd.mutils.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;

/*******************************************
 * 作者： 刘海东
 * 时间： 2019/6/3
 * 类名：com.lhd.mutils.utils.PhoneUtils
 * 说明：手机信息
 ********************************************
 */
public class PhoneUtils {
    private volatile static PhoneUtils phoneUtils;
    private PhoneUtils(){}
    public static PhoneUtils getInstance(){

        if(phoneUtils==null){
            synchronized (PhoneUtils.class){
                if(phoneUtils==null){
                    phoneUtils=new PhoneUtils();
                }
            }
        }
        return phoneUtils;
    }


    /**
     * 获取手机IMEI
     *
     * @param context
     * @return
     */
    public  String getIMEI(Context context) {
        try {
            //实例化TelephonyManager对象
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMEI号
            @SuppressLint("MissingPermission")  String imei = telephonyManager.getDeviceId();
            //在次做个验证，也不是什么时候都能获取到的啊
            if (imei == null) {
                imei = "";
            }
            return imei;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 获取手机IMSI
     */
    public  String getIMSI(Context context){
        try {
            TelephonyManager telephonyManager=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMSI号
            @SuppressLint("MissingPermission")  String imsi=telephonyManager.getSubscriberId();
            if(null==imsi){
                imsi="00000";
            }
            return imsi;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public  String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public  String getSystemModel() {
        return android.os.Build.MODEL;
    }



}
