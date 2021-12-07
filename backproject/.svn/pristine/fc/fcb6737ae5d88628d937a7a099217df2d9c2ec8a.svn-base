package com.egintra.common.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

/**
 * 记账、解锁日志表
 *
 * @author liushihao
 * @date 2021/8/12
 */
@TableName("fee_operlog")
public class FeeOperlog extends Model<FeeOperlog> {

    private static final long serialVersionUID = 3566405396352273120L;
    /**
     * 订单号
     */
    @TableField(value = "orderid")
    private String orderid;

    /**
     * 1：操作记账标记   2：操作解锁标记
     */
    @TableField(value = "czlb")
    private String czlb;

    /**
     * 1：成功  0：未成功
     */
    @TableField(value = "zt")
    private String zt;

    /**
     * 记录信息
     */
    @TableField(value = "msg")
    private String msg;

    /**
     * 系统时间
     */
    @TableField(value = "")
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
        return "FeeOperlog{" +
                "orderid='" + orderid + '\'' +
                ", czlb='" + czlb + '\'' +
                ", zt='" + zt + '\'' +
                ", msg='" + msg + '\'' +
                ", dates=" + dates +
                '}';
    }
}
