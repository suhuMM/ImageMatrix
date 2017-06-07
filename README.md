模仿美图秀秀图片处理
=================
从三个方面对图片进行处理： 
--------------------------------------------
色度:
ColorMatrix hueMatrix = new ColorMatrix();
--------------------------------------------
        hueMatrix.setRotate(0, vHue);
        hueMatrix.setRotate(1, vHue);
        hueMatrix.setRotate(2, vHue);
饱和度:
ColorMatrix saturationMatrix = new ColorMatrix();
--------------------------------------------
        saturationMatrix.setSaturation(vSaturation);
亮度处理:
ColorMatrix lumMatrix = new ColorMatrix();
--------------------------------------------
        lumMatrix.setScale(vLum, vLum, vLum, 1);
        
混合效果：
ColorMatrix imageMatrix = new ColorMatrix();
--------------------------------------------
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);
        
![image_1](https://github.com/suhuMM/ImageMatrix/edit/master/image/image_1.png)
--
![image_2](https://github.com/suhuMM/ImageMatrix/edit/master/image/image_2.png)
--




推荐一个封装比较好的工具类：

博客：http://rance935.com/blog/?p=152

GitHub：https://github.com/Blankj/AndroidUtilCode

