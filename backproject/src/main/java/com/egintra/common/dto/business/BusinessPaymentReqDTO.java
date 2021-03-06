package com.egintra.common.dto.business;

import com.egintra.common.dto.finance.FeeDrvjkMeterialRespDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 业务缴费请求DTO
 *
 * @author zhangyunting
 * @date 2021/9/8
 */
public class BusinessPaymentReqDTO {

    /**
     * 流水号或者身份号码
     */
    private String params;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 总数
     */
    private BigDecimal totalMoney;

    /**
     * 收款人
     */
    private String payUnit;

    /**
     * 当前用户id
     */
    private String userId;

    private List<Map<String,Object>> drvs;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Map<String, Object>> getDrvs() {
        return drvs;
    }

    public void setDrvs(List<Map<String, Object>> drvs) {
        this.drvs = drvs;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getPayUnit() {
        return payUnit;
    }

    public void setPayUnit(String payUnit) {
        this.payUnit = payUnit;
    }

    @Override
    public String toString() {
        return "BusinessPaymentDTO{" +
                "params='" + params + '\'' +
                ", payType='" + payType + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                ", payUnit='" + payUnit + '\'' +
                '}';
    }
}
