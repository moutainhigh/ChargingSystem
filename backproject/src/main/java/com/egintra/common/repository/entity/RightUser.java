package com.egintra.common.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@TableName("sys_user")
public class RightUser extends Model<RightUser> {

    private static final long serialVersionUID = 4068324007516059538L;
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 用户编码
     */
    @TableId(value = "no")
    private String no;

    /**
     * 用户名
     */
    @TableId(value = "name")
    private String name;

    /**
     * 用户级别
     */
    @TableId(value = "user_level")
    private String userLevel;

    /**
     * 起始ip
     */
    @TableId(value = "start_ip")
    private String startIp;

    /**
     * 终止ip
     */
    @TableId(value = "end_ip")
    private String endIp;

    /**
     * 性别
     */
    @TableId(value = "sex")
    private String sex;

    /**
     * 电话
     */
    @TableId(value = "phone")
    private String phone;

    /**
     * 密码
     */
    @TableId(value = "password")
    private String password;

    /**
     * 部门ID
     */
    @TableId(value = "depart_id")
    private String departId;

    /**
     * 账套号
     */
    @TableId(value = "account_id")
    private String accountId;

    /**
     * 创建人编码
     */
    @TableId(value = "record_create_cd")
    private String recordCreateCd;

    /**
     * 创建人名称
     */
    @TableId(value = "record_create_nm")
    private String recordCreateNm;

    /**
     * 创建时间
     */
    @TableId(value = "record_create_tm")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordCreateTm;

    /**
     * 修改人编码
     */
    @TableId(value = "record_modify_cd")
    private String recordModifyCd;

    /**
     * 修改人名称
     */
    @TableId(value = "record_modify_nm")
    private String recordModifyNm;

    /**
     * 修改日期
     */
    @TableId(value = "record_modify_tm")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordModifyTm;

    /**
     * 状态
     */
    @TableId(value = "record_status")
    private String recordStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getStartIp() {
        return startIp;
    }

    public void setStartIp(String startIp) {
        this.startIp = startIp;
    }

    public String getEndIp() {
        return endIp;
    }

    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRecordCreateCd() {
        return recordCreateCd;
    }

    public void setRecordCreateCd(String recordCreateCd) {
        this.recordCreateCd = recordCreateCd;
    }

    public String getRecordCreateNm() {
        return recordCreateNm;
    }

    public void setRecordCreateNm(String recordCreateNm) {
        this.recordCreateNm = recordCreateNm;
    }

    public Date getRecordCreateTm() {
        return recordCreateTm;
    }

    public void setRecordCreateTm(Date recordCreateTm) {
        this.recordCreateTm = recordCreateTm;
    }

    public String getRecordModifyCd() {
        return recordModifyCd;
    }

    public void setRecordModifyCd(String recordModifyCd) {
        this.recordModifyCd = recordModifyCd;
    }

    public String getRecordModifyNm() {
        return recordModifyNm;
    }

    public void setRecordModifyNm(String recordModifyNm) {
        this.recordModifyNm = recordModifyNm;
    }

    public Date getRecordModifyTm() {
        return recordModifyTm;
    }

    public void setRecordModifyTm(Date recordModifyTm) {
        this.recordModifyTm = recordModifyTm;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    @Override
    public String toString() {
        return "RightUser{" +
                "id='" + id + '\'' +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", startIp='" + startIp + '\'' +
                ", endIp='" + endIp + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", departId='" + departId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", recordCreateCd='" + recordCreateCd + '\'' +
                ", recordCreateNm='" + recordCreateNm + '\'' +
                ", recordCreateTm=" + recordCreateTm +
                ", recordModifyCd='" + recordModifyCd + '\'' +
                ", recordModifyNm='" + recordModifyNm + '\'' +
                ", recordModifyTm=" + recordModifyTm +
                ", recordStatus='" + recordStatus + '\'' +
                '}';
    }
}
