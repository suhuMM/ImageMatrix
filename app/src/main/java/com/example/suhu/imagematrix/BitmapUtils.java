package com.example.suhu.imagematrix;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by suhu on 2017/5/27.
 */

public class BitmapUtils {
    /**
     * 图片去色,返回灰度图片
     *
     * @param bmp
     *          传入的图片
     * @return 去色后的图片
     */
    public static Bitmap toGrayscale(Bitmap bmp) {
        if (bmp != null) {
            int width, height;
            Paint paint = new Paint();
            height = bmp.getHeight();
            width = bmp.getWidth();
            Bitmap bm = Bitmap.createBitmap(width, height,
                    Bitmap.Config.RGB_565);
            Canvas c = new Canvas(bm);
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0);
            ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
            paint.setColorFilter(f);
            c.drawBitmap(bmp, 0, 0, paint);
            return bm;
        }else{
            return bmp;
        }
    }
}
