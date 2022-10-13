package com.example.onedemo.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zxd
 * @Date 2022/10/12 14:00
 */
public class FileUtil {

    /**
     * 获取指定文件夹下所有文件
     * @param dirFilePath 文件夹路径
     * @return
     */
    public List<File> getAllFile(String dirFilePath) {
        if (StringUtils.isBlank(dirFilePath)) {
            return null;
        }
        List<File> res = new ArrayList<>();
        getAllFile(new File(dirFilePath), res);
        return res;
    }


    // 参数传递File类型的目录
    public void getAllFile(File dir, List<File> res) {
        // 如果文件夹不存在或着不是文件夹，则返回 null
        if (Objects.isNull(dir) || !dir.exists() || dir.isFile()) {
            return;
        }
        // Lambada表达式
        File[] files = dir
                .listFiles((d, name) -> new File(d, name).isDirectory()
                        || name.toLowerCase().endsWith(".json"));
        /*
         * 1.ListFiles会先把传递的目录进行遍历后的结果封装为File对象
         * 2.之后若返回True，则会把这些对象一一传递给过滤器accept方法的参数pathname
         */
        assert files != null;
        for (File tmp : files) {
            if (tmp.isDirectory()) {// 判断是否为文件夹
                getAllFile(tmp, res);
            } else {
                res.add(tmp);
            }
        }
    }
}
