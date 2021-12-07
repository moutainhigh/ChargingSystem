package com.egintra.common.dto.pay;

/**
 * 线上支付信息参数设置请求DTO
 *
 * @author liushihao
 * @date 2021/8/18
 */
public class FeePayparaReqDTO {
    /**
     * 管理部门
     */
    private String glbm;

    public String getGlbm() {
        return glbm;
    }

    public void setGlbm(String glbm) {
        this.glbm = glbm;
    }

    @Override
    public String toString() {
        return "FeePayparaReqDTO{" +
                "glbm='" + glbm + '\'' +
                '}';
    }
}
