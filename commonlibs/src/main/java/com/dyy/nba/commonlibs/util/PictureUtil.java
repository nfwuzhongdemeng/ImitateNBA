package com.dyy.nba.commonlibs.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import com.dyy.nba.commonlibs.AppUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/6/28.
 */

public class PictureUtil {
    /**
     * dp转px
     * @param dpValue
     * @return
     */
    public static int dip2px(float dpValue) {
        final float scale = AppUtils.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     * @param pxValue
     * @return
     */
    public static int px2dip(float pxValue) {
        final float scale = AppUtils.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * bitmap转为base64
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

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
     * base64转为bitmap
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * R资源转Drawable
     * @param resId
     * @return
     */
    public static Drawable R2Drawable(int resId){
        Resources resources = AppUtils.getResources();
        Drawable drawable = resources.getDrawable(resId);
        return drawable;
    }

    /**
     * R资源转Bitmap
     * @param resId
     * @return
     */
    public static Bitmap R2Bitmap(int resId){
        Resources r = AppUtils.getResources();
        InputStream is = r.openRawResource(resId);
        BitmapDrawable bmpDraw = new BitmapDrawable(is);
        Bitmap bmp = bmpDraw.getBitmap();
        return bmp;
    }


    /**
     * drawable转bitmap
     * @param drawable
     * @return
     */
    public static Bitmap drawable2Bitmap(Drawable drawable){
        Bitmap bitmap = Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;
    }

    /**
     * bitmap转drawable
     * @param bitmap
     * @return
     */
    public static Drawable bitmap2Drawable(Bitmap bitmap){
        Drawable drawable =new BitmapDrawable(bitmap);
        return drawable;
    }
}
