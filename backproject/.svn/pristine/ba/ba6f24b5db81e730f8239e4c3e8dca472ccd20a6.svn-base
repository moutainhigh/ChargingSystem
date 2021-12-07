package com.egintra.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户级别枚举
 *
 * @author liushihao
 * @date 2021/9/3
 */
public enum UserLevelEnum {
    /**
     * 支队管理员
     */
    DETACHMENT("1", "支队管理员"),

    /**
     * 大队管理员
     */
    BRIGADE("2", "大队管理员"),

    /**
     * 操作员
     */
    OPERATOR("3", "操作员");

    private String code;
    private String desc;

    UserLevelEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static Map<String, String> userLevelMap = new HashMap<>();

    static {
        UserLevelEnum[] types = UserLevelEnum.values();
        for (UserLevelEnum type : types) {
            userLevelMap.put(type.code, type.desc);
        }
    }
}
