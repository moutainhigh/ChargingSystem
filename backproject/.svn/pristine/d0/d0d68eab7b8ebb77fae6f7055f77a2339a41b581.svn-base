package com.egintra.common.dto.pay;

import java.util.Date;

/**
 * 记账、解锁日志表保存（新增）请求DTO
 *
 * @author liushihao
 * @date 2021/8/12
 */
public class FeeOperlogSaveReqDTO {

    /**
     * 订单号
     */
    private String orderid;

    /**
     * 1：操作记账标记   2：操作解锁标记
     */
    private String czlb;

    /**
     * 1：成功  0：未成功
     */
    private String zt;

    /**
     * 记录信息
     */
    private String msg;

    /**
     * 系统时间
     */
    private Date dates;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCzlb() {
        return czlb;
    }

    public void setCzlb(String czlb) {
        this.czlb = czlb;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "FeeOperlogSaveReqDTO{" +
                "orderid='" + orderid + '\'' +
                ", czlb='" + czlb + '\'' +
                ", zt='" + zt + '\'' +
                ", msg='" + msg + '\'' +
                ", dates=" + dates +
                '}';
    }
}
