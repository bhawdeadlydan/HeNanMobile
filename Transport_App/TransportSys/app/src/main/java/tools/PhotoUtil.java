package tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 4/12/2016.
 */
public class PhotoUtil {

    public static String getPhotopath(String filePath) {
        // 照片全路径
        String fileName = "";
        // 文件夹路径
        String pathUrl = filePath;

        DateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String imageName = "IMG_"+format.format(new Date())+".jpg";

        File file = new File(pathUrl);
//        if(!file.exists())
//            file.mkdirs();// 创建文件夹

        fileName = pathUrl + "/" + imageName;
        return fileName;
    }

    public static Bitmap getBitmapFromUrl(String url, double width, double height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 设置了此属性一定要记得将值设置为false
        Bitmap bitmap = BitmapFactory.decodeFile(url);
        // 防止OOM发生
        options.inJustDecodeBounds = false;
        int mWidth = bitmap.getWidth();
        int mHeight = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = 1;
        float scaleHeight = 1;

        // 按照固定宽高进行缩放
        // 这里希望知道照片是横屏拍摄还是竖屏拍摄
        // 因为两种方式宽高不同，缩放效果就会不同
        // 这里用了比较笨的方式
        if(mWidth <= mHeight) {
            scaleWidth = (float) (width/mWidth);
            scaleHeight = (float) (height/mHeight);
        } else {
            scaleWidth = (float) (height/mWidth);
            scaleHeight = (float) (width/mHeight);
        }
        // 按照固定大小对图片进行缩放
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, mWidth, mHeight, matrix, true);
        // 用完了记得回收
        //bitmap.recycle();
        return newBitmap;
    }

    public static String saveScalePhoto(Bitmap bitmap) {
        // 照片全路径
        String fileName = "";
        // 文件夹路径
        String pathUrl = Environment.getExternalStorageDirectory()+"/TransportSys1/";

        DateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String imageName = "IMG_"+format.format(new Date());

        FileOutputStream fos = null;
        File file = new File(pathUrl);
        file.mkdirs();// 创建文件夹
        fileName = pathUrl + imageName + ".jpg";
        try {
            fos = new FileOutputStream(fileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG,90, fos);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
}
