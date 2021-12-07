package com.egintra.common.exception;

/**
 * 自定义全局异常
 *
 * @author liushihao
 * @date 2021/7/06
 */
public class PlatformsException extends RuntimeException {

    private String code;
    private String msg;

    public PlatformsException(String code, String msg) {
        //调用父构建函数添加错误信息
        super(String.format("异常类型：%s\t异常描述：%s", code, msg));
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
