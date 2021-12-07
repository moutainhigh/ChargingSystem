package com.egintra.common.enums;

/**
 * 角色状态枚举
 *
 * @author liushihao
 * @date 2021/6/21
 */
public enum RoleStatusEnum {
    /**
     * 正常
     */
    NO("0", "正常"),

    /**
     * 删除
     */
    YES("1", "删除");

    private String code;
    private String desc;

    RoleStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
