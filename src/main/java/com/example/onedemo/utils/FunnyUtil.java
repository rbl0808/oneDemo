package com.example.onedemo.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author : rbl
 * @date : 2022/11/11
 */
public class FunnyUtil {

    //随机生成汉字
    private String  getRandomChinese() {
        String res = "";
        int hightPos;
        int lowPos;

        Random random = new Random();
        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            res = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {}

        return res;
    }

}
