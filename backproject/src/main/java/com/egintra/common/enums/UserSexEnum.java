package com.egintra.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 性别枚举类
 *
 * @author liushihao
 * @date 2021/6/25
 */
public enum UserSexEnum {
    /**
     * 男
     */
    NO("0", "男"),

    /**
     * 女
     */
    YES("1", "女");

    private String code;
    private String desc;

    UserSexEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static Map<String, String> userSexMap = new HashMap<>();

    static {
        UserSexEnum[] types = UserSexEnum.values();
        for (UserSexEnum type : types) {
            userSexMap.put(type.code, type.desc);
        }
    }
}
