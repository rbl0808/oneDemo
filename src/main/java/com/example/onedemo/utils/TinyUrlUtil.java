/**
 * FileName: TinyURL
 * Author: rbl
 * Date: 2019-6-1
 **/
package com.example.onedemo.utils;

public class TinyUrlUtil {
    private static int key = 1;

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        char[] c = longUrl.toCharArray();
        for (int i = 0; i < c.length; i++) {
            c[i] ^= key;
        }
        String encode = new String(c);
        return "http://" + encode;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        char[] c = shortUrl.substring(7).toCharArray();
        for (int i = 0; i < c.length; i++) {
            c[i] ^= key;
        }
        return new String(c);
    }

    public static void main(String[] args) {
        TinyUrlUtil tinyURL = new TinyUrlUtil();
        String Url = "https://leetcode.com/problems/design-tinyurl";
        String a = tinyURL.encode(Url);
        String b = tinyURL.decode(a);
        System.out.println(a);
        System.out.println(b);
    }
}
