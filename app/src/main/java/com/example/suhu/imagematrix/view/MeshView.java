package com.example.suhu.imagematrix.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.suhu.imagematrix.R;

/**
 * Created by suhu on 2017/6/20.
 */

public class MeshView extends View {
    private Drawable drawable;
    private Bitmap bitmap;

    private final int WIDTH = 30;
    private final int HEIGHT = 30;
    private final int DISTORTION = 80000;
    private final int COUNT = (WIDTH + 1) * (HEIGHT + 1);
    private final float[] verts = new float[COUNT * 2];
    private final float[] orig = new float[COUNT * 2];


    public MeshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MeshView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setFocusable(true);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MeshView);
        drawable = array.getDrawable(R.styleable.MeshView_image);
        bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth()
                , drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565
        );
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        array.recycle();

        animationBitmap(bitmap);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        warp(event.getX(), event.getY());
        return true;
    }

    private void animationBitmap(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int index = 0;
        for (int y = 0; y <= HEIGHT; y++) {
            float fy = height * y / HEIGHT;
            for (int x = 0; x <= WIDTH; x++) {
                float fx = width * x / WIDTH;
                //奇数 x(脚标为偶数)
                orig[index * 2] = verts[index * 2] = fx;
                //偶数 y
                orig[index * 2 + 1] = verts[index * 2 + 1] = fy;
                index += 1;
            }
        }
        setBackgroundColor(Color.WHITE);
    }

    private void warp(float cx, float cy) {
        for (int i = 0; i < COUNT * 2; i += 2) {
            float dx = cx - orig[i];
            float dy = cy - orig[i + 1];
            float dd = dx * dx + dy * dy;
            //计算每个坐标点与当前点(cx,cy)之间的距离
            float d = (float) Math.sqrt(dd);
            //计算扭曲度，距离当前点(cx,cy)越远，扭曲度越小
            float pull = DISTORTION / (dd * d);
            if (pull >= 1) {
                verts[i] = cx;
                verts[i + 1] = cy;
            } else {
                verts[i] = orig[i] + dx * pull;
                verts[i + 1] = orig[i + 1] + dx * pull;
            }
            //通知重绘
            invalidate();
        }
        invalidate();
    }


}
