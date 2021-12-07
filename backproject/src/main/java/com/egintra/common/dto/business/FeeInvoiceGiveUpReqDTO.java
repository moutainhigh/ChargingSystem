package com.egintra.common.dto.business;

import javax.validation.constraints.NotNull;

/**
 * 发票作废请求DTO
 *
 * @author liushihao
 * @date 2021/9/8
 */
public class FeeInvoiceGiveUpReqDTO {

    /**
     * 单位代码
     */
    @NotNull(message = "发表主表ID不可为空")
    private String id;

    /**
     * 作废原因
     */
    @NotNull(message = "作废原因不可为空")
    private String cause;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "FeeInvoiceGiveUpReqDTO{" +
                "id='" + id + '\'' +
                ", cause='" + cause + '\'' +
                '}';
    }
}
