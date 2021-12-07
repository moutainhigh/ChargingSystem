package com.egintra.common.enums;

/**
 * 收费项目可选必选
 *
 * @author liushihao
 * @date 2021/7/30
 */
public enum DriverLicenseOptionsEnum {
    /**
     * 可选
     */
    NO("0", "可选"),

    /**
     * 必选
     */
    YES("1", "必选");

    private String code;
    private String desc;

    DriverLicenseOptionsEnum(String code, String desc) {
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
