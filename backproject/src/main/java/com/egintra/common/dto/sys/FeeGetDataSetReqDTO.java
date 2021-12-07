package com.egintra.common.dto.sys;

import com.egintra.common.dto.CommonPage;

import javax.validation.constraints.NotNull;

/**
 * 数据访问参数设置模块请求参数对象
 *
 * @author liushihao
 * @date 2021/6/8
 */
public class FeeGetDataSetReqDTO extends CommonPage {

    /**
     * 项目模块id
     */
    @NotNull(message = "项目模块不能为空")
    private String modelid;

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    @Override
    public String toString() {
        return "FeeGetDataSetReqDTO{" +
                "modelid='" + modelid + '\'' +
                '}';
    }
}
