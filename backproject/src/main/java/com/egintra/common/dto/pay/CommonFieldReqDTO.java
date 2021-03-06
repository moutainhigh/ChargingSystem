package com.egintra.common.dto.pay;

import java.math.BigDecimal;

/**
 * 相同字段 返回实体
 *
 * @author zzyt
 * @date 2021/08/06
 */
public class CommonFieldReqDTO {

    /**
     * 准驾车型或者号牌种类
     */
    private String cxhp;

    /**
     * 业务类别
     */
    private String ywlb;

    /**
     * 订单号
     */
    private String orderid;

    /**
     * 流水号
     */
    private String lsh;

    /**
     *管理部门
     */
    private String glbm;

    /**
     * 准驾车型
     */
    private String zjcx;

    /**
     * 名称
     */
    private String xm;

    /**
     * 号牌种类
     */
    private String hpzl;

    /**
     * 业务类型
     */
    private String ywlx;

    /**
     * 业务原因
     */
    private String ywyy;

    /**
     * 项目明细编号
     */
    private String projectId;

    /**
     * 数量
     */
    private String quantity;

    /**
     * 项目明细名称
     */
    private String projectName;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 项目所属财政大类
     */
    private String classId;

    /**
     * 上缴省财政比率
     */
    private String provinceRate;

    /**
     * 上缴市财政比率
     */
    private String cityRate;

    /**
     * 价格
     */
    private BigDecimal materialCost;

    /**
     * 总价格
     */
    private BigDecimal totalSum;

    /**
     * 存利润
     */
    private BigDecimal profitPrice;

    /**
     * 账套号
     */
    private String accountId;

    /**
     * 财政部门执行号
     */
    private String financeCode;

    /**
     * 号牌号码
     */
    private String hphm;

    public String getCxhp() {
        return cxhp;
    }

    public void setCxhp(String cxhp) {
        this.cxhp = cxhp;
    }

    public String getYwlb() {
        return ywlb;
    }

    public void setYwlb(String ywlb) {
        this.ywlb = ywlb;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getGlbm() {
        return glbm;
    }

    public void setGlbm(String glbm) {
        this.glbm = glbm;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getZjcx() {
        return zjcx;
    }

    public void setZjcx(String zjcx) {
        this.zjcx = zjcx;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public BigDecimal getProfitPrice() {
        return profitPrice;
    }

    public void setProfitPrice(BigDecimal profitPrice) {
        this.profitPrice = profitPrice;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getYwyy() {
        return ywyy;
    }

    public void setYwyy(String ywyy) {
        this.ywyy = ywyy;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getProvinceRate() {
        return provinceRate;
    }

    public void setProvinceRate(String provinceRate) {
        this.provinceRate = provinceRate;
    }

    public String getCityRate() {
        return cityRate;
    }

    public void setCityRate(String cityRate) {
        this.cityRate = cityRate;
    }

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    @Override
    public String toString() {
        return "CommonFieldReqDTO{" +
                "cxhp='" + cxhp + '\'' +
                ", ywlb='" + ywlb + '\'' +
                ", orderid='" + orderid + '\'' +
                ", lsh='" + lsh + '\'' +
                ", glbm='" + glbm + '\'' +
                ", zjcx='" + zjcx + '\'' +
                ", xm='" + xm + '\'' +
                ", hpzl='" + hpzl + '\'' +
                ", ywlx='" + ywlx + '\'' +
                ", ywyy='" + ywyy + '\'' +
                ", projectId='" + projectId + '\'' +
                ", quantity=" + quantity +
                ", projectName='" + projectName + '\'' +
                ", unitPrice=" + unitPrice +
                ", classId='" + classId + '\'' +
                ", provinceRate='" + provinceRate + '\'' +
                ", cityRate='" + cityRate + '\'' +
                ", materialCost=" + materialCost +
                ", totalSum=" + totalSum +
                ", profitPrice=" + profitPrice +
                ", accountId='" + accountId + '\'' +
                ", financeCode='" + financeCode + '\'' +
                ", hphm='" + hphm + '\'' +
                '}';
    }
}
