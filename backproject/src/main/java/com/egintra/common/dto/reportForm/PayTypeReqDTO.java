package com.egintra.common.dto.reportForm;

/**
 * 支付方式
 *
 * @author zhangyunting
 * @date 2021/11/10
 */
public class PayTypeReqDTO {

    /**
     * 编码
     */
    private String code;

    /**
     * 支付方式
     */
    private String name;

    /**
     * 支付总金额
     */
    private String nvl;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNvl() {
        return nvl;
    }

    public void setNvl(String nvl) {
        this.nvl = nvl;
    }
}
