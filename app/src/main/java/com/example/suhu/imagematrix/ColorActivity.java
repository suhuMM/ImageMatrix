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

public class ColorActivity extends AppCompatActivity {

    @BindView(R.id.imageView3)
    ImageView imageView3;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.girl);
    }

    @OnClick({R.id.button4, R.id.button5, R.id.button6, R.id.button12, R.id.button13, R.id.button14})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button4:
                imageView3.setImageBitmap(BitmapUtils.matrixManageBitmap(bitmap,Matrix.GRAY));
                break;
            case R.id.button5:
                imageView3.setImageBitmap(BitmapUtils.matrixManageBitmap(bitmap,Matrix.REVERSE));
                break;
            case R.id.button6:
                imageView3.setImageBitmap(BitmapUtils.matrixManageBitmap(bitmap,Matrix.NOSTALGIA));
                break;
            case R.id.button12:
                imageView3.setImageBitmap(BitmapUtils.matrixManageBitmap(bitmap,Matrix.DISCOLORATION));
                break;
            case R.id.button13:
                imageView3.setImageBitmap(BitmapUtils.matrixManageBitmap(bitmap,Matrix.SATURATED));
                break;
            case R.id.button14:
                imageView3.setImageBitmap(bitmap);
                break;
        }
    }
}
