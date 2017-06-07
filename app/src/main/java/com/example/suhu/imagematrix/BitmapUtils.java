package com.example.suhu.imagematrix;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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
     * @param bmp 传入的图片
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
        } else {
            return bmp;
        }
    }


    /**
     * @param bm 算法：
     *           B.r = 255-B.r
     *           B.g = 255-B.g
     *           B.b = 255-B.g
     * @method 底片效果
     * @author suhu
     * @time 2017/6/6 13:32
     */
    public static Bitmap negativeImage(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color, r, g, b, a;
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);
        for (int i = 0; i < oldPx.length; i++) {
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }

            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }

            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }
            newPx[i] = Color.argb(a, r, g, b);
        }

        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }


    /**
     * @param bm 算法：
     *           r1 = (int)(0.393*r+0.769*g+0.189*b);
     *           g1 = (int)(0.349*r+0.686*g+0.168*b);
     *           b1 = (int)(0.272*r+0.534*g+0.131*b);
     * @method 老照片
     * @author suhu
     * @time 2017/6/6 13:51
     */
    public static Bitmap oldImage(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color, r, g, b, r1, g1, b1, a1;
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);
        for (int i = 0; i < oldPx.length; i++) {
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a1 = Color.alpha(color);

            r1 = (int) (0.393 * r + 0.769 * g + 0.189 * b);
            g1 = (int) (0.349 * r + 0.686 * g + 0.168 * b);
            b1 = (int) (0.272 * r + 0.534 * g + 0.131 * b);

            if (r1 > 255) {
                r1 = 255;
            } else if (r1 < 0) {
                r1 = 0;
            }

            if (g1 > 255) {
                g1 = 255;
            } else if (r1 < 0) {
                g1 = 0;
            }

            if (b1 > 255) {
                b1 = 255;
            } else if (r1 < 0) {
                b1 = 0;
            }

            newPx[i] = Color.argb(a1, r1, g1, b1);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }


    /**
     * @param bm 算法：用前一个像素点的RGB值减去当前像素值加上127作为当前像素的RGB值
     *           B.r = C.r-B.r+127
     *           B.g = C.g-B.g+127
     *           B.b = C.b-B.b+127
     * @method 浮雕效果
     * @author suhu
     * @time 2017/6/6 14:17
     */
    public static Bitmap reliefImage(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color_c, color, cr, cg, cb, r, g, b, a;

        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);


        for (int i = 1; i < oldPx.length; i++) {
            color_c = oldPx[i - 1];
            cr = Color.red(color_c);
            cg = Color.green(color_c);
            cb = Color.blue(color_c);

            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = cr - r + 127;
            g = cg - g + 127;
            b = cb - b + 127;

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }

            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }

            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }

            newPx[i] = Color.argb(a,r,g,b);
        }
        bmp.setPixels(newPx,0,width,0,0,width,height);

        return bmp;
    }

    /**
     * @param bm 算法：
     *           B.r = 255-B.r
     *           B.g = 255-B.g
     *           B.b = 255-B.g
     * @method 处理一部分（不合成）
     * @author suhu
     * @time 2017/6/6 13:32
     */
    public static Bitmap halfNegativeImage(Bitmap bm) {
        int width = bm.getWidth();
        int newWidth = width/2;
        int height = bm.getHeight();
        int color_c, color, cr, cg, cb,r, g, b, a;
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);
        for (int i = 0; i < oldPx.length; i++) {

            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            //左边的点修改成底片效果
            if(i%width<=newWidth){

                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

            }else {
                //右边的照片浮雕效果
                color_c = oldPx[i - 1];
                cr = Color.red(color_c);
                cg = Color.green(color_c);
                cb = Color.blue(color_c);

                r = cr - r + 127;
                g = cg - g + 127;
                b = cb - b + 127;

            }

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }

            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }

            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }

            newPx[i] = Color.argb(a, r, g, b);
        }

        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }

    /**
     * @param bm 算法：
     *           B.r = 255-B.r
     *           B.g = 255-B.g
     *           B.b = 255-B.g
     * @method 处理一部分(合成)
     * @author suhu
     * @time 2017/6/6 13:32
     */
    public static Bitmap syntheticHalfNegativeImage(Bitmap bm) {
        int width = bm.getWidth();
        int newWidth = width/2;
        int height = bm.getHeight();
        int color, r, g, b, a;
        Bitmap bmp = Bitmap.createBitmap(newWidth, height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[newWidth * height];
        int[] newPx = new int[newWidth * height];
        bm.getPixels(oldPx, 0, newWidth, 0, 0, newWidth, height);
        for (int i = 0; i < oldPx.length; i++) {
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }

            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }

            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }
            newPx[i] = Color.argb(a, r, g, b);
        }

        bmp.setPixels(newPx, 0, newWidth, 0, 0, newWidth, height);
        //合成照片
        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(bm,0,0,null);
        canvas.drawBitmap(bmp,0,0,null);
        canvas.save();
        return bitmap;
    }


    /**
     *@method 使用颜色矩阵处理效果
     *@author suhu
     *@time 2017/6/7 13:37
     *@param bm
     *@param matrix
     *
     *
    */
    public static Bitmap matrixManageBitmap(Bitmap bm,float[] matrix){
        int width = bm.getWidth();
        int height = bm.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(matrix);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bm,0,0,paint);
        canvas.save();
        return bmp;
    }


}
