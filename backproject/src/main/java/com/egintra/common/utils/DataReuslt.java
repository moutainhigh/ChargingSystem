package com.egintra.common.utils;

import com.egintra.common.exception.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DataReuslt<T> implements Serializable {

    private static final long serialVersionUID = 4022360849042537754L;
    // 返回码
    private String code;
    // 返回消息
    private String message;
    // 返回数据
    private T data;

    private DataReuslt() {

    }

    public DataReuslt(ResultCode resultCode, T data) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    private void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    private void setResultCode(String message) {
        this.code = ResultCode.SUCCESS.code();
        this.message = message;
    }

    // 返回成功
    public static DataReuslt success() {
        DataReuslt result = new DataReuslt();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    // 返回成功
    public static DataReuslt success(String message) {
        DataReuslt result = new DataReuslt();
        result.setResultCode(message);
        return result;
    }

    // 返回成功
    public static DataReuslt success(Object data) {
        DataReuslt result = new DataReuslt();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    // 返回成功
    public static DataReuslt success(String code, String message) {
        DataReuslt result = new DataReuslt();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    // 返回失败
    public static DataReuslt fail(String code, String message) {
        DataReuslt result = new DataReuslt();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    // 返回失败
    public static DataReuslt fail(ResultCode resultCode) {
        DataReuslt result = new DataReuslt();
        result.setResultCode(resultCode);
        return result;
    }

    // 返回成功
    public static DataReuslt fail(String message) {
        DataReuslt result = new DataReuslt();
        result.setResultCode(ResultCode.OTHER_ERROR);
        result.setData(message);
        return result;
    }
}
