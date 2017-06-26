package com.example.suhu.imagematrix.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.suhu.imagematrix.R;

/**
 * Created by suhu on 2017/6/23.
 */

public class XFView extends View{
    /**
     * bBitmap:背景图片
     * fBitmap：遮罩图片（由bBitmap生成的，大小和bBitmap一样）
     *
     * */
    private Bitmap bBitmap,fBitmap;
    /**
     * 画笔
     * */
    private Paint paint;
    /**
     * 画布
     * */
    private Canvas canvas;
    /**
     * path：滑动路径
     * */
    private Path path;
    /**
     * 距离左边的距离，保证图片居中
     * */
    private int left;
    /**
     * 画笔的宽度
     * */
    private int painWidth = 80;

    public XFView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    public XFView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //初始化图片
        bBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.girl);
        fBitmap = Bitmap.createBitmap(bBitmap.getWidth(),bBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        left = (getWidth()-bBitmap.getWidth())/4;

        //初始化画笔
        paint = new Paint();
        paint.setAlpha(0);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(painWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);

        //初始化路径
        path = new Path();

        //画遮罩图片
        canvas = new Canvas(fBitmap);
        canvas.drawColor(Color.GRAY);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(event.getX()+left,event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX()+left,event.getY());
                break;
        }
        canvas.drawPath(path,paint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bBitmap,-left,0,null);
        canvas.drawBitmap(fBitmap,-left,0,null);
    }
}
