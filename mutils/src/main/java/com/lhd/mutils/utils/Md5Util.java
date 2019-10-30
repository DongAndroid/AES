package com.lhd.mutils.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*******************************************
 * 作者： 刘海东
 * 时间： 2019/2/12
 * 类名：com.lhd.mutils.utils.Md5Util
 * 说明：
 ********************************************
 */
public class Md5Util {
    private volatile static Md5Util md5Util;
    private  Md5Util(){}
    public  static Md5Util getInstance(){
        if(md5Util==null){
            synchronized (Md5Util.class){
                if(md5Util==null){
                    md5Util=new Md5Util();
                }
            }
        }
        return md5Util;
    }
/**
 * 生成字符串的MD5值
 * */
    public String encode(String str){
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f'};
        try {
            byte[] strTemp = str.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte tmp[] = mdTemp.digest();
            char strs[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                strs[k++] = hexDigits[byte0 >>> 4 & 0xf];
                strs[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(strs).toLowerCase();
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 生成文件的MD5值
     * */
    public String encode(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            return bigInt.toString(16);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "错误";
    }
}
