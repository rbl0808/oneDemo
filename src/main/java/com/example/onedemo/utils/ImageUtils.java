package com.example.onedemo.utils;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author : rbl
 * @date : 2023/4/4
 */
public class ImageUtils {
    public String creatrImage() {
        BufferedImage before = null;
        try {
            before = ImageIO.read(new File("/Users/rbl/Desktop/截图/8.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedImage after = getBuffredImage(before, before.getWidth(), before.getHeight());

        return toBase64(after,"png");
    }

    private BufferedImage getBuffredImage(BufferedImage otherImage, int width, int height) {
        BufferedImage outputImg = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

        Graphics g = outputImg.createGraphics();
        g.drawImage(otherImage, 0, 0, width, height, null);
        g.dispose();
        return outputImg;
    }

    public  String toBase64(BufferedImage bufferedImage, String type) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, type, byteArrayOutputStream);
            // BASE64加密
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encode(byteArrayOutputStream.toByteArray());
            return String.format("data:image/%s;base64,%s", type, base64);
        } catch (IOException e) {
            System.out.println("图片资源转换BASE64失败");
            //异常处理
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ImageUtils().creatrImage());
    }

}
