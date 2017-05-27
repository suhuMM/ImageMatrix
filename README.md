模仿美图秀秀图片处理
=================
从三个方面对图片进行处理： 
色度:
--------------------------------------------
ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, vHue);
        hueMatrix.setRotate(1, vHue);
        hueMatrix.setRotate(2, vHue);
饱和度:
--------------------------------------------
ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(vSaturation);
亮度处理:
--------------------------------------------
ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(vLum, vLum, vLum, 1);
混合效果：
--------------------------------------------
ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);
--------------------------------------------
#[效果图1](https://github.com/suhuMM/ImageMatrix/image/image_1.png)
#[效果图2](https://github.com/suhuMM/ImageMatrix/image/image_2.png)
