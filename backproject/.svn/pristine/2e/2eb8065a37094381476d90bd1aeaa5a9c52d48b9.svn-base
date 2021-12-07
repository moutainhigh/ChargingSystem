package com.egintra.common.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * utf8与字符转换
 *
 * @author liushihao
 * @date 2021/8/5
 */
public class Utf8Utils {

    /**
     * 将中文字符串转换为utf-8格式
     *
     * @param xmlDoc 中文字符
     * @return 结果
     */
    public static String encodeUTF8(String xmlDoc) {
        String str = "";
        try {
            str = URLEncoder.encode(xmlDoc, "utf-8");
            return str;
        } catch (Exception e) {
            str = e.toString();
        }

        return str;
    }

    /**
     * 将utf-8转换为中文字符串格式
     *
     * @param str 参数
     * @return 返回结果
     * @throws Exception
     */
    public static String decodeUTF8(String str) throws Exception {
        String xmlDoc = "";
        try {
            xmlDoc = URLDecoder.decode(str, "utf-8");
        } catch (Exception e) {
            xmlDoc = e.toString();
        }

        return xmlDoc;
    }
}
