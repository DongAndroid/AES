package com.lhd.mutils.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/*******************************************
 * 作者： 刘海东
 * 时间： 2019/2/25
 * 类名：com.lhd.mutils.utils.NumberUtil
 * 说明：数字相关操作类
 ********************************************
 */
public class NumberUtil {


    private static volatile NumberUtil numberUtil;
    private DecimalFormat decimalFormat ;
    private NumberUtil() {
    }

    public static NumberUtil getInstance() {
        if (numberUtil == null) {
            synchronized (DisplayUtil.class) {
                if (numberUtil == null) {
                    numberUtil = new NumberUtil();
                }
            }
        }

        return numberUtil;
    }

    /**
     * 数字格式化位数#0.00
     *数字格式化位数，并显示百分比#0.00%
     * */
    public <T extends Number> String format(T number,String pattern){
        decimalFormat=new DecimalFormat(pattern);
        decimalFormat.applyPattern(pattern);
        return  decimalFormat.format(number);
    }
    /**
     * 格式化为货币的形式  并且四射五入
     *
     * */
    public <T extends Number> String formatCurrency(T number){
        NumberFormat decimalFormat=DecimalFormat.getCurrencyInstance();
        return  decimalFormat.format(number);
    }
    /**
     * 格式化为百分比
     *
     * */
    public <T extends Number> String formatPercent(T number){
        NumberFormat numberFormat3 = DecimalFormat.getPercentInstance();
        return numberFormat3.format(number);

    }
    /**
     * 格式化多位小数显示，防止变成科学计数法显示
     *
     * */
    public  String formatBigDecimal(String number,int newScale){
        return new BigDecimal(number).setScale(newScale,   BigDecimal.ROUND_HALF_UP)+"";
    }
    /**
     * 格式化多位小数显示，防止变成科学计数法显示
     *
     * */
    public  String formatBigDecimal(double number,int newScale){
        return new BigDecimal(number).setScale(newScale,   BigDecimal.ROUND_HALF_UP)+"";


    }
}
