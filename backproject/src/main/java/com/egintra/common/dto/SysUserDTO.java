package com.egintra.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户请求DTO
 *
 * @author liushihao
 * @date 2021/06/24
 */
public class SysUserDTO extends CommonPage {

    /**
     * ID
     */
    private String id;

    /**
     * 账号
     */
    @NotNull(message = "账号不能为空")
    @Length(min = 1, max = 7, message = "用户名长度只能在1和7之间")
    private String no;

    /**
     * 用户名
     */
    @Length(min = 1, max = 6, message = "用户名长度只能在1和6之间")
    private String name;

    /**
     * 用户级别
     */
    private String userLevel;

    /**
     * 起始ip
     */
    private String startIp;

    /**
     * 终止ip
     */
    private String endIp;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 创建人编码
     */
    private String recordCreateCd;

    /**
     * 创建人名称
     */
    private String recordCreateNm;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordCreateTm;

    /**
     * 修改人编码
     */
    private String recordModifyCd;

    /**
     * 修改人名称
     */
    private String recordModifyNm;

    /**
     * 修改日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordModifyTm;

    /**
     * 状态
     */
    private String recordStatus;

    /**
     * 部门ID
     */
    private String departId;

    /**
     * 单位ID
     */
    private String departmentId;

    public SysUserDTO(String userId, String userName, String userCode) {
        this.id = userId;
        this.name = userName;
        this.no = userCode;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "SysUserDTO{" +
                "id='" + id + '\'' +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", startIp='" + startIp + '\'' +
                ", endIp='" + endIp + '\'' +
                ", password='" + password + '\'' +
                ", recordCreateCd='" + recordCreateCd + '\'' +
                ", recordCreateNm='" + recordCreateNm + '\'' +
                ", recordCreateTm=" + recordCreateTm +
                ", recordModifyCd='" + recordModifyCd + '\'' +
                ", recordModifyNm='" + recordModifyNm + '\'' +
                ", recordModifyTm=" + recordModifyTm +
                ", recordStatus='" + recordStatus + '\'' +
                ", departId='" + departId + '\'' +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }
}
