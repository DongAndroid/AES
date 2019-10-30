package com.lhd.mutils.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/***************************************************
 * <P/>项目: AES
 * <P/>类名: com.lhd.mutils.utils.FileUtil
 * <P/>创建时间: 2019/10/29 9:04
 * <P/>作者: 刘海东
 * <P/>说明: 文件相关操作
 ****************************************************
 */
public class FileUtil {

    private volatile static FileUtil fileUtil;

    private FileUtil(){}

    public static FileUtil getInstance(){
        if(fileUtil==null){
            synchronized (ByteUtils.class){
                if(fileUtil==null){
                    fileUtil=new FileUtil();
                }
            }
        }
        return fileUtil;
    }
    // 这个方法是获取内部存储的根路径 /data 
    public String getDataDirectory(){
        return Environment.getDataDirectory().getAbsolutePath();
    }
    // 这个方法是获取某个应用在内部存储中的files路径  /data/user/0/packname/files 
    public String getFilesAbsolutePath(Context context){
        return context.getFilesDir().getAbsolutePath();
    }
    // 这个方法是获取某个应用在内部存储中的cache路径 /data/user/0/packname/cache 
    public String getCacheAbsolutePath(Context context){
        return context.getCacheDir().getAbsolutePath();
    }
    // 这个方法是获取某个应用在内部存储中的自定义路径 /data/user/0/packname/app_myFile 
    public String getMyFileAbsolutePath(Context context){
        return context.getDir("myFile", context.MODE_PRIVATE).getAbsolutePath();
    }
    // 这个方法是获取外部存储的根路径 /storage/emulated/0 
    public String getExternalStorageDirectory(){
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
    // 这个方法是获取外部存储的根路径 /storage/emulated/0 
    public String getExternalStoragePublicDirectory(Context context){
        return Environment.getExternalStoragePublicDirectory("").getAbsolutePath();
    }
    //  这个方法是获取某个应用在外部存储中的files路径,大于4.4 /storage/emulated/0/Android/data/packname/files 
    public String getExternalFilesDir(Context context){
        return context.getExternalFilesDir("").getAbsolutePath();
    }
    // 这个方法是获取某个应用在外部存储中的cache路径大于4.4 /storage/emulated/0/Android/data/packname/cache 
    public String getExternalCacheDir(Context context){
        return context.getExternalCacheDir().getAbsolutePath();
    }

    public boolean hasFile(String path){
        File file=new File(path);
        return file.exists();
    }
    public long getFileLength(String path){
        File file=new File(path);
        return file.length();
    }
    public String  getFileType(String path){
        File file=new File(path);
        String type[]=file.getName().split(".");
        if(type!=null&&type.length>1){
            return type[type.length-1];
        }
        return "";


    }
    public void mkdirs(String path){
        File file=new File(path);

        if(!file.exists()){
            file.mkdirs();
        }
    }
    public void delFile(String path){
        File file=new File(path);
        if(file.exists()){
            file.delete();
        }
    }
    public boolean creatFile(String path){
        try{
            File file=new File(path);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            if(!file.exists()){
                file.createNewFile();
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }


}
