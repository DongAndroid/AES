package com.lhd.mutils.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Half;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*******************************************
 * 作者： 刘海东
 * 时间： 2019/2/13
 * 类名：com.lhd.mutils.utils.ImageUtil
 * 说明：图片相关操作
 ********************************************
 */
public class ImageUtil {
    private volatile static ImageUtil imageUtil;
    private ImageUtil(){}
    public static ImageUtil getInstance(){
        if(imageUtil==null){
            synchronized (ImageUtil.class){
                if(imageUtil==null){
                    imageUtil=new ImageUtil();
                }
            }
        }
        return imageUtil;
    }
    /**
     * 从path读取图片，
     * */
    public Bitmap getBitmpa(String src){

        return getBitmpa(src,0);
    }
    /**
     * 从path读取图片，指定压缩比例
     * * */
    public Bitmap getBitmpa(String src,int inSampleSize){
        File file=new File(src);
        return getBitmpa(file,inSampleSize,0,0);
    }
    /**
     * 从path读取图片，按尺寸压缩
     * * */
    public Bitmap getBitmpa(String src,int w ,int h){
        File file=new File(src);
        return getBitmpa(file,0,w,h);
    }
    /**
     * 从file读取图片
     * */
    public Bitmap getBitmpa(File file) {
        return   getBitmpa(file, 0,0,0);

    }
    /**
     * 从file读取图片，根据比例压缩
     * */
    public Bitmap getBitmpa(File file,int inSampleSize) {
        return   getBitmpa(file, inSampleSize,0,0);

    }
    /**
     * 从file读取图片，根据尺寸压缩
     * */
    public Bitmap getBitmpa(File file,int w,int h) {
        return   getBitmpa(file, 0,w,h);

    }
    /**
     * 从file读取图片,不指定压缩时默认压缩,比例优先
     * */
    public Bitmap getBitmpa(File file,int inSampleSize,int w,int h){
        try{
            if(!file.exists()){
                return null;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            if(inSampleSize>0){
                options.inSampleSize=inSampleSize;
                Bitmap bmp = BitmapFactory.decodeFile(file.getPath(), options);
                options = null;
                return bmp;
            }else if(w==0||h==0) {
                options.inSampleSize=1;
                Bitmap bmp = BitmapFactory.decodeFile(file.getPath(), options);
                options = null;
                return bmp;
            }else{
                BitmapFactory.Options tempOptions = new BitmapFactory.Options();
                tempOptions.inJustDecodeBounds=true;
                Bitmap tempBm = BitmapFactory.decodeFile(file.getPath(), tempOptions);
                if(tempOptions.outWidth>tempOptions.outHeight){
                    options.inSampleSize=tempOptions.outWidth/w;
                }else {
                    options.inSampleSize=tempOptions.outHeight/h;
                }
                Bitmap bmp = BitmapFactory.decodeFile(file.getPath(), options);
                options = null;
                tempBm=null;
                return bmp;
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public  int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public  Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }
    /**
     * 按比例压缩图片
     */
    public  Bitmap getCompressPhoto(Bitmap bitmap,int inSampleSize) {
        Matrix matrix = new Matrix();
        matrix.setScale(inSampleSize, inSampleSize);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);

    }
    /**
     * 按尺寸压缩图片
     */
    public  Bitmap getCompressPhoto(Bitmap bitmap, int w, int h) {
        return Bitmap.createScaledBitmap(bitmap, w, h*(bitmap.getWidth()/w), true);

    }
    /*bitmap保存文件*/
    public  File saveBitmapFile(Bitmap bitmap, String path) {
        File tempFile = new File(path);
        try {

            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(tempFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;

    }

}
