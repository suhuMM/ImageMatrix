package com.example.suhu.imagematrix;

/**
 * Created by suhu on 2017/6/7.
 */

public class Matrix {

    /**
     * 灰度效果
     */
    public static final float[] GRAY =
            {
                    0.33F, 0.59F, 0.11F, 0, 0,
                    0.33F, 0.59F, 0.11F, 0, 0,
                    0.33F, 0.59F, 0.11F, 0, 0,
                        0,     0,     0, 1, 0
            };


    /**
     * 图像反转
     * */
    public static final float[] REVERSE =
            {
                    -1,  0,  0, 1, 1,
                     0, -1,  0, 1, 1,
                     0,  0, -1, 1, 1,
                     0,  0,  0, 1, 0
            };


    /**
     * 怀旧效果
     * */
    public static final float[] NOSTALGIA =
            {
                    0.393F, 0.769F, 0.189F, 0, 0,
                    0.349F, 0.686F, 0.168F, 0, 0,
                    0.272F, 0.534F, 0.131F, 0, 0,
                         0,      0,      0, 1, 0
            };


    /**
     *去色效果
     * */
    public static final float[] DISCOLORATION =
            {
                    1.5F, 1.5F, 1.5F, 0, -1,
                    1.5F, 1.5F, 1.5F, 0, -1,
                    1.5F, 1.5F, 1.5F, 0, -1,
                       0,    0,    0, 1,  0
            };


    /**
     * 高饱和度
     * */
    public static final float[] SATURATED =
            {
                     1.438F, -0.122F, -0.016F, 0, -0.03F,
                    -0.062F,  1.378F, -0.016F, 0, -0.02F,
                    -0.062F, -0.122F,  1.483F, 0, -0.02F,
                          0,       0,       0, 1,      0
            };

}
