package com.lhd.mutils;

import com.lhd.mutils.utils.AESUtil;
import com.lhd.mutils.utils.AppUtil;
import com.lhd.mutils.utils.Base64Util;
import com.lhd.mutils.utils.ByteUtils;
import com.lhd.mutils.utils.DateUtil;
import com.lhd.mutils.utils.DeviceInfoUtil;
import com.lhd.mutils.utils.DisplayUtil;
import com.lhd.mutils.utils.FileUtil;
import com.lhd.mutils.utils.ImageUtil;
import com.lhd.mutils.utils.Md5Util;
import com.lhd.mutils.utils.NetUtil;
import com.lhd.mutils.utils.NumberUtil;
import com.lhd.mutils.utils.PhoneUtils;

/*******************************************
 * 作者： 刘海东
 * 时间： 2019/2/12
 * 类名：com.lhd.mutils.MUtils
 * 说明：工具类，持有各个工具实例
 ********************************************
 */
public class MUtils {
    public static final String UTF_8="UTF-8";
    public static final String BG2312="gb2312";
    public static AESUtil aesUtil=AESUtil.getInstance();
    public static ByteUtils byteUtils=ByteUtils.getInstance();
    public static Base64Util base64Util=Base64Util.getInstance();
    public static Md5Util md5Util=Md5Util.getInstance();
    public static ImageUtil imageUtil=ImageUtil.getInstance();
    public static DateUtil dateUtil=DateUtil.getInstance();
    public static DeviceInfoUtil deviceInfoUtil=DeviceInfoUtil.getInstance();
    public static DisplayUtil displayUtil=DisplayUtil.getInstance();
    public static AppUtil appUtil=AppUtil.getInstance();
    public static NumberUtil numberUtil=NumberUtil.getInstance();
    public static PhoneUtils phoneUtils=PhoneUtils.getInstance();
    public static NetUtil netUtil=NetUtil.getInstance();
    public static FileUtil fileUtil=FileUtil.getInstance();
    public static void main(String[] s){


        println(numberUtil.format(123.123,"#00"));

    }
    private static String println(String str){
        System.out.println(str);
        return str;
    }
}
