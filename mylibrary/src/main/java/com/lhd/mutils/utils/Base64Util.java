package com.lhd.mutils.utils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.lhd.mutils.MUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


/*******************************************
 * 作者： 刘海东
 * 时间： 2019/2/12
 * 类名：com.lhd.mutils.utils.Base64Util
 * 说明：base64工具类
 ********************************************
 */
public class Base64Util {

    private volatile static Base64Util base64Util;
    private Base64Util(){}
    public static Base64Util getInstance(){

        if(base64Util==null){
            synchronized (Base64Util.class){
                if(base64Util==null){
                    base64Util=new Base64Util();
                }
            }
        }
        return base64Util;
    }

    public String strToBase64(String str){
        try{
            str= Base64.encodeToString(str.getBytes(MUtils.UTF_8), Base64.DEFAULT);
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }
    public String base64ToStr(String str){
        try{
            str= new String(Base64.decode(str.getBytes(MUtils.UTF_8), Base64.DEFAULT),MUtils.UTF_8);
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }
    /**
     * bitmap转base64
     * */
    public String bitmapToBase64(Bitmap bitmap){
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;

    }
    /**
     * base64转bitmap
     * */
    public Bitmap base64ToBitmap(String str){
        byte[] bytes = Base64.decode(str, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


}
