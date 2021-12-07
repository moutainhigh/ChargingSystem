package com.egintra.common.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * xml格式封装类
 *
 * @author liushihao
 * @date 2021/8/5
 */
public class XmlFileUtils {

    /**
     * xml格式开始部分
     *
     * @return 结果
     */
    public static String getXmlFileHead() {

        return "<?xml version=\"1.0\" encoding=\"GBK\"?>\n<root>\n";
    }

    /**
     * xml格式结尾部分
     *
     * @return 结果
     */
    public static String getXmlFileFoot() {

        return "</root>";
    }

    /**
     * 将对象每一个字段转化成<字段名>字段值</字段名>格式
     * 用于数据平台交互生成写入类接口所需xml格式数据
     *
     * @param bean 数据对象
     * @return 结果
     * @throws Exception
     */
    public static String writeXmlDoc(Object bean) throws Exception {
        Map p = BeanUtils.describe(bean);
        Set s = p.keySet();
        Iterator i = s.iterator();
        StringBuffer buff = new StringBuffer();
        buff.append("<vioViolation>\n");
        while (i.hasNext()) {
            Object key = i.next();
            if (key.equals("class")) {
            } else {
                Object value = p.get(key);
                buff.append("\n <");
                buff.append(key);
                buff.append(">");
                if (value == null || value.toString().equals("")) {
                    buff.append("");
                } else {
                    value = Utf8Utils.encodeUTF8(value.toString());
                    buff.append(value);
                }
                buff.append("</");
                buff.append(key);
                buff.append(">");
            }
        }
        buff.append("\n</vioViolation>\n");

        return buff.toString();
    }

    /**
     * 将对象每一个字段转化成<字段名>字段值</字段名>格式
     * 用于数据平台交互生成查询类接口所需xml格式数据
     *
     * @param bean 数据对象
     * @return 结果
     * @throws Exception
     */
    public static String selectXmlDoc(Object bean) throws Exception {
        Map p = BeanUtils.describe(bean);
        Set s = p.keySet();
        Iterator i = s.iterator();
        StringBuffer buff = new StringBuffer();
        buff.append("<QueryCondition>\n");
        while (i.hasNext()) {
            Object key = i.next();
            if (key.equals("class")) {
            } else {
                Object value = p.get(key);
                buff.append("\n <");
                buff.append(key);
                buff.append(">");
                if (value == null || value.toString().equals("")) {
                    buff.append("");
                } else {
                    value = Utf8Utils.encodeUTF8(value.toString());
                    buff.append(value);
                }
                buff.append("</");
                buff.append(key);
                buff.append(">");
            }
        }
        buff.append("\n</QueryCondition>\n");

        return buff.toString();
    }
}
