package com.egintra.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static final String SPECIAL_CHAR = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    private StringUtil() {
        throw new IllegalAccessError("Utility class should't be instantiated.");
    }

    /**
     * 转义SQL中like操作符中的占位符
     *
     * @param src 源串
     * @return 转义后的字符串
     */
    public static String escapeLike(String src) {
        return null == src ? StringUtils.EMPTY : src.replace("%", "\\%").replace("_", "\\_");
    }

    /**
     * 格式化BigDecimal
     *
     * @param amt 金额
     * @return 字符串
     */
    public static String formatAmt(BigDecimal amt) {
        return null == amt ? DECIMAL_FORMAT.format(BigDecimal.ZERO) : DECIMAL_FORMAT.format(amt);
    }

    /**
     * 判断是否含有特殊字符
     *
     * @param str 被检查的字符串
     * @return true为包含，false为不包含
     */
    public static boolean isContainSpecialChar(String str) {
        Pattern p = Pattern.compile(SPECIAL_CHAR);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 过滤特殊字符
     *
     * @param str 被过滤的字符串
     * @return 过滤掉特殊字符串的字符串
     */
    public static String stringFilter(String str) {
        Pattern p = Pattern.compile(SPECIAL_CHAR);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 校验字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str) || !containsText(str);
    }

    /**
     * 有内容
     *
     * @param str 被校验字符串
     * @return 不为空true, 空false
     */
    public static boolean hasText(String str) {
        return str != null && !str.isEmpty() && containsText(str);
    }

    /**
     * 无用空格过滤
     */
    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断查询参数中是否以特殊字符开头
     *
     * @param value 被校验字符串
     * @return 如果以特殊字符开头则返回true，否则返回false
     */
    public static boolean specialSymbols(String value) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(value)) {
            return false;
        }
        Pattern pattern = Pattern.compile(SPECIAL_CHAR);
        Matcher matcher = pattern.matcher(value);

        char[] specialSymbols = SPECIAL_CHAR.toCharArray();
        // 是否以特殊字符开头
        boolean isStartWithSpecialSymbol = false;
        for (int i = 0; i < specialSymbols.length; i++) {
            char c = specialSymbols[i];
            if (value.indexOf(c) == 0) {
                isStartWithSpecialSymbol = true;
                break;
            }
        }

        return matcher.find() && isStartWithSpecialSymbol;
    }

    /**
     * 将原字符数组递归拼接，例原数组s1,s2,s3拼接后数组项s1; s1,s2; s1,s2,s3
     *
     * @param resource 原字符数组
     * @param locate   拼接开始位置
     * @return 拼接后的数组
     */
    public static String[] strListRecursionSplicing(String[] resource, int locate) {
        // 递归位置为数组最后一个元素+1时，开始回溯
        if (locate == resource.length) {
            return resource;
        }
        // 拼接递归的字符串
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i <= locate; i++) {
            buffer.append(resource[i]);
            if (i != locate) {
                buffer.append(",");
            }
        }
        // 递归调用
        resource = strListRecursionSplicing(resource, locate + 1);
        // 回溯时数组递归位置的值设置为新值
        resource[locate] = buffer.toString();
        // 返回递归结果
        return resource;
    }

    /**
     * 将string按逗号分隔成数组
     *
     * @param srcDocNo 待分隔的字符串
     * @return
     */
    public static String[] strToStrList(String srcDocNo) {
        String[] srcDocNoList = null;
        if (!StringUtil.isEmpty(srcDocNo)) {
            srcDocNoList = srcDocNo.split(",");
            for (int i = 0; i < srcDocNoList.length; i++) {
                srcDocNoList[i] = srcDocNoList[i].trim();
            }
        }
        return srcDocNoList;
    }

    /**
     * 将字符串按最大字符长度截取
     *
     * @param str           待截取的字符串
     * @param maxCharLength 截取最大长度
     * @return 截取后的字符串
     */
    public static String subStrMaxLength(String str, int maxCharLength) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
            return "无";
        } else {
            int charLength = 0;
            String chinese = "[\u0391-\uFFE5]";
            for (int i = 0; i < str.length(); i++) {
                //获取一个字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字符
                if (temp.matches(chinese)) {
                    // 中文字符长度为2
                    charLength += 2;
                } else {
                    // 其他字符长度为1
                    charLength += 1;
                }
                if (charLength > maxCharLength) {
                    str = str.substring(0, i);
                    break;
                }
            }
        }
        return str;
    }
}