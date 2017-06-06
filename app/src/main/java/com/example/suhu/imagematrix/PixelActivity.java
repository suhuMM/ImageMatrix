package com.example.suhu.imagematrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PixelActivity extends AppCompatActivity {

    @BindView(R.id.imageView2)
    ImageView imageView2;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixel);
        ButterKnife.bind(this);

        init();

    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.girl);
    }


    @OnClick({R.id.button7, R.id.button8, R.id.button9, R.id.button10,R.id.button11})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button7:
                imageView2.setImageBitmap(BitmapUtils.negativeImage(bitmap));
                break;
            case R.id.button8:
                imageView2.setImageBitmap(BitmapUtils.oldImage(bitmap));
                break;
            case R.id.button9:
                imageView2.setImageBitmap(BitmapUtils.reliefImage(bitmap));
                break;
            case R.id.button10:
                imageView2.setImageBitmap(BitmapUtils.halfNegativeImage(bitmap));
                break;
            case R.id.button11:
                imageView2.setImageBitmap(BitmapUtils.syntheticHalfNegativeImage(bitmap));
                break;
        }
    }
}
