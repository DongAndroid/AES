package com.lhd.mutils.utils;

import com.lhd.mutils.MUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/*******************************************
 * 作者： 刘海东
 * 时间： 2019/2/11
 * 类名：com.lhd.aes.AESUtil
 * 说明：AES加密解密，utf-8
 ********************************************
 */
public class AESUtil {

    private volatile static AESUtil aesUtil;

    private AESUtil(){}

    public static AESUtil getInstance(){
        if(aesUtil==null){
            synchronized (ByteUtils.class){
                if(aesUtil==null){
                    aesUtil=new AESUtil();
                }
            }
        }
        return aesUtil;
    }






    /**
     * AES加密字符串
     *
     * @param content
     *            需要被加密的字符串
     * @param password
     *            加密需要的密码
     * @param iv
     *      *     偏移量
     * @return 密文
     */
    public  String encrypt(String content, String password,String iv) {
        try {

            Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            localCipher.init(1, createKey(password), createIV(iv));
            byte[]   paramArrayOfByte = localCipher.doFinal(content.getBytes());

            return new String(MUtils.byteUtils.bytesToHex(paramArrayOfByte));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解密AES加密过的字符串
     *
     * @param
     *
     * @param password
     *            加密时的密码
     * @return 明文
     */
    public  String decrypt(String context, String password,String iv) {
        try {
            Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            localCipher.init(2, createKey(password),createIV(iv));
            byte[] bytes1=localCipher.doFinal(MUtils.byteUtils.hexToByteArray(context));
            return new String(bytes1,"UTF-8"); // 明文

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *
     * 创建密匙key
     * */
    private   SecretKeySpec  createKey(String pwd){
        while (pwd.length()<16){
            pwd+="0";
        }
        SecretKeySpec keySpec = new SecretKeySpec(pwd.getBytes(), "AES");
        return keySpec;
    }
    /**
     *
     * 创建iv
     * */
    private   IvParameterSpec  createIV(String iv){
        while (iv.length()<16){
            iv+="0";
        }
        try{
            return   new IvParameterSpec(iv.getBytes("UTF-8"));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
