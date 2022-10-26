package com.example.onedemo.utils;

import org.apache.commons.lang3.StringUtils;

import java.nio.file.FileSystems;
import java.nio.file.Path;


/**
 * @author zxd
 * @Date 2022/10/18 15:26
 */
public class PathUtil {

    public static String LIUNX = "Linux";
    public static String WINDOWS = "Windows";
    public static String MACOS = "Mac OS X";

    /**
     * 根据相对路径落去绝对路径
     *
     * @param absolutePath 绝对路径
     * @param relativePath 相对路径
     * @return
     */
    public static String getAbsolutePath(String absolutePath, String relativePath) {
        // use getParent() if basePath is a file (not a directory)
        Path resolvedPath = FileSystems.getDefault().getPath(absolutePath).getParent().resolve(relativePath);
        return String.valueOf(resolvedPath.normalize());
    }

    /**
     * 将路径转为当前系统路径
     */
    public static String convertPath(String path) {
        if (StringUtils.isBlank(path)) {
            return path;
        }
        if (isLinuxSystem()) {
            //转换成linux
            path = path.replaceFirst("([a-zA-Z]):", "/$1:").replace("\\", "/");
        } else {
            //转换成windows
            path = path.replace("\\", "/");
        }
        return path;
    }

    /**
     * 判断是否是Linux操作系统
     *
     * @return
     */
    public static Boolean isLinuxSystem() {
        return StringUtils.equals(PathUtil.LIUNX, System.getProperty("os.name"))
                || StringUtils.equals(PathUtil.MACOS, System.getProperty("os.name"));
    }

    public static void main(String[] args) {
        System.out.println(getAbsolutePath(System.getProperty("user.dir"), "../"));
        System.out.println(convertPath("/c:/Use/wxjjszhahngxiangda"));
//        System.out.println(convertPath("c:\\Use\\wxjjszhahngxiangda"));
    }
}
