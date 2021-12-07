package com.egintra.common.enums;

/**
 * 用户状态枚举
 *
 * @author liushihao
 * @date 2021/6/21
 */
public enum DatafromEnum {
    /**
     * webservice
     */
    WEB("1", "webservice"),

    /**
     * 下发库
     */
    LESSUE("2", "下发库");

    private String code;
    private String desc;

    DatafromEnum(String code, String desc) {
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
