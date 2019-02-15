package com.lhd.mutils.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*******************************************
 * 作者： 刘海东
 * 时间： 2019/2/13
 * 类名：com.lhd.mutils.utils.ImageUtil
 * 说明：
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
     * 从path读取图片，默认压缩
     * */
    public Bitmap getBitmpa(String src){

        return getBitmpa(src,0);
    }
    /**
     * 从path读取图片，默认压缩
     * */
    public Bitmap getBitmpa(String src,int inSampleSize){
        File file=new File(src);
        return getBitmpa(file,inSampleSize);
    }
    /**
     * 从file读取图片，默认压缩
     * */
    public Bitmap getBitmpa(File file) {
        return   getBitmpa(file, 0);

    }
    /**
     * 从file读取图片,不指定压缩时默认压缩
     * */
    public Bitmap getBitmpa(File file,int inSampleSize){
        try{
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            if(inSampleSize>0){
                options.inSampleSize=inSampleSize;
            }else{
                if(file.length()>512*1024){
                    options.inSampleSize = 2;
                }else if (file.length()>1024*1024){
                    options.inSampleSize = 4;
                }else if (file.length()>2048*1024){
                    options.inSampleSize = 8;
                }
            }
            Bitmap bmp = BitmapFactory.decodeFile(file.getPath(), options);
            options = null;
            return bmp;

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
     * 压缩图片
     */
    public  Bitmap getCompressPhoto(String path) {
        if(TextUtils.isEmpty(path)){
            return null;
        }
        File f=new File(path);
        if(!f.exists()){
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        if(f.length()>512*1024){
            options.inSampleSize = 2;
        }else if (f.length()>1024*1024){
            options.inSampleSize = 4;
        }else if (f.length()>2048*1024){
            options.inSampleSize = 8;
        }
        Bitmap bmp = BitmapFactory.decodeFile(path, options);
        options = null;

        return bmp;

    }
    /*bitmap保存文件*/
    public  File saveBitmapFile(Bitmap bitmap, String path) {
        File tempFile = new File(path);//
        if(tempFile!=null){
//            System.out.println(tempFile.getPath());
            Log.e("haha",tempFile.getPath());
        }
        try {

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
