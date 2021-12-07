package com.egintra.common.pointcuts;

/**
 * 自定义异常
 *
 * @author liushihao
 * @date 2021/9/15
 */
public class VdException extends RuntimeException {
    private final String code;
    private final String msg;

    public VdException(String code, String msg) {
        //调用父构建函数添加错误信息
        super(String.format("异常类型：%s\t异常描述：%s", code, msg));
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
