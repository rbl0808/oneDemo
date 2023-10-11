package com.example.onedemo.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : rbl
 * @date : 2023/9/26
 */
public class HttpUtils {
    public static void main(String[] args) throws IOException {
        StringBuilder res = new StringBuilder();
        String s1 = "MKT053852";
        String s2 = "MKT046127";

        List<String> strings = new ArrayList<>();

        File file = new File("/Users/rbl/Downloads/phone.txt");


        List<String> lines = Files.readAllLines(Paths.get("/Users/rbl/Downloads/phone.txt"));
        //for循环遍历读取每一行并输出
        for (String line : lines) {
            strings.add(line);
        }

            int i = 0;
        for (String mob : strings) {
            i++;
            String param = "{\n" +
                    "    \"sceneType\": \"3000\",\n" +
                    "    \"phoneNum\": \"" + mob + "\",\n" +
                    "    \"provinceCode\": \"600104\",\n" +
                    "    \"lanCode\": \"600104\"\n" +
                    "}";
            try {
                String response = HttpUtil.post("http://10.135.139.149:31406/data-center/data/center/qryUserSceneInfo", param);
                System.out.println(i + response);

                if (response != null && !"".equals(response)) {

                    JSONObject data = JSON.parseObject(response, new Feature[]{Feature.OrderedField});
                    if (data != null && data.containsKey("result")) {
                        String result = data.getString("result");
                        res.append(mob).append(",")
                                .append(result.contains(s1)).append(",")
                                .append(result.contains(s2)).append(",")
                                .append(result).append(";\n");
                    }
                }
            } catch (Exception e) {

            }

        }
        System.out.println(res.toString());



    }
}
