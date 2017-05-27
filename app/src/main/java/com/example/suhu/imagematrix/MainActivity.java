package com.example.suhu.imagematrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.main_hue)
    SeekBar hue;
    @BindView(R.id.main_saturation)
    SeekBar saturation;
    @BindView(R.id.main_lum)
    SeekBar lum;

    private static final int MID_VALUE = 50;
    private float mHue, mSaturation, mLum;
    private Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        setListener();
    }


    private void init() {
        bm = BitmapFactory.decodeResource(getResources(),R.mipmap.girl);
        image.setImageBitmap(BitmapUtils.toGrayscale(bm));
        //image.setImageBitmap(bm);
    }

    private void setListener() {
        hue.setOnSeekBarChangeListener(this);
        saturation.setOnSeekBarChangeListener(this);
        lum.setOnSeekBarChangeListener(this);
    }


    public Bitmap handleImageEffect(Bitmap bm, float vHue, float vSaturation, float vLum) {

        Bitmap bitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        ColorMatrix hueMatrix = new ColorMatrix();

        hueMatrix.setRotate(0, vHue);
        hueMatrix.setRotate(1, vHue);
        hueMatrix.setRotate(2, vHue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(vSaturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(vLum, vLum, vLum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm, 0, 0, paint);

        return bitmap;
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.main_hue:
                mHue = (progress - MID_VALUE) * 1.0F / MID_VALUE * 180;
                break;
            case R.id.main_saturation:
                mSaturation = progress * 1.0F / MID_VALUE;
                break;
            case R.id.main_lum:
                mLum = progress * 1.0F / MID_VALUE;
                break;
        }
        image.setImageBitmap(handleImageEffect(bm, mHue, mSaturation, mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
