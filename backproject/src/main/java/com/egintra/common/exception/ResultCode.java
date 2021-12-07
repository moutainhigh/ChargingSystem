package com.egintra.common.exception;

public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS("000000", "成功"),

    /**
     * 其他错误
     */
    OTHER_ERROR("111111", "其他错误"),

    /**
     * 基础提示(10000-19999)
     */
    /**
     * 请求参数为空
     */
    REQUEST_PARAMS_IS_EMPTY("10001", "请求参数为空"),
    /**
     * 当前用户无权限
     */
    CURRENT_USER_HAS_NO_PERMISSION("10002", "当前用户无权限"),

    /**
     * 更新成功
     */
    UPDATE_SUCCESS("10003", "更新成功"),

    /**
     * 更新失败
     */
    UPDATE_FAILED("10004", "更新失败"),

    /**
     * 删除成功
     */
    DELETE_SUCCESS("10005", "删除成功"),

    /**
     * 删除失败
     */
    DELETE_FAILED("10006", "删除失败"),

    /**
     * 登录、用户信息、菜单管理模块(20000-29999)
     */
    /**
     * 账号不存在或密码错误
     */
    USER_LOGIN_ERROR("20001", "账号不存在或密码错误"),

    /**
     * 请输入您的用户名和密码
     */
    USER_INFO_IS_EMPTY("20002", "请输入您的用户名和密码"),

    /**
     * 更新用户密码失败，请检查您的输入信息
     */
    UPDATE_USER_PASSWORD_FAILED("20003", "更新密码失败,请检查您的输入信息"),

    /**
     * 当前账号已存在，无法保存
     */
    ADD_USER_NO_FAILED("20004", "当前账号已存在，无法保存"),

    /**
     * 当前分类菜单存在下级功能菜单,继续删除会删除下级菜单,是否继续?
     */
    CUR_FUNCCATE_MENU_HAVE_SUBMENUS("20005", "当前分类菜单存在下级功能菜单，继续删除将删除下级菜单,是否继续?"),

    /**
     * 当前编码分类存在已创建编码,继续删除会删除已创建编码,是否继续?
     */
    CUR_CODE_SORT_HAVE_CODES("20006", "当前分类菜单存在下级功能菜单，继续删除会删除已创建编码,是否继续?"),

    /**
     * 当前角色已存在，无法保存
     */
    ADD_ROLE_FAILED("20007", "当前角色已存在，无法保存"),

    /**
     * 当前编码分类已存在，无法保存
     */
    CUR_PUBSIMPLECATE_ALREADY_SAVED("20008", "当前编码分类已存在，无法保存"),

    /**
     * 当前编码已存在，无法保存
     */
    CUR_PUBSIMPLENUMBER_ALREADY_SAVED("20009", "当前编码已存在，无法保存"),

    /**
     * 存在角色重复，无法保存
     */
    UPDATE_ROLE_FAILED("20010", "存在角色重复，无法保存"),

    /**
     * 存在用户重复，无法保存
     */
    UPDATE_USER_FAILED("20011", "存在用户重复，无法保存"),

    /**
     * 存在编码分类重复，无法保存
     */
    UPDATE_SIMPLECATE_FAILED("20012", "存在编码分类重复，无法保存"),

    /**
     * 存在项目模块重复，无法保存
     */
    UPDATE_DATASET_FAILED("20012", "存在项目模块重复，无法保存"),

    /**
     * 财务设置模块（30000-39999）
     */
    /**
     * 已经存在当前收费信息设置，无法新增
     */
    ADD_FEE_ERROR("30000", "已经存在当前收费信息设置，无法新增"),

    /**
     * 已存在收费项目***，请重新选择
     */
    ERROR_FOR_MORE_FEE_PROJECTS("30001", "已存在收费项目%s，请重新选择"),

    /**
     * 业务操作（40000-49999）
     */
    /**
     * 请输入流水号或身份证号码
     */
    PLEASE_ENTER_LSH_OR_SFZMHM("40000", "请输入流水号或身份证号码"),

    /**
     * 查询管理部门收费信息为空
     */
    QUERY_GLBM_PAYPARA_IS_EMPTY("40001", "查询管理部门收费信息为空"),

    /**
     * 收费项目业务不存在，请先添加收费项目业务
     */
    BUSINESS_DOES_NOT_EXIST("40002", "收费项目业务不存在，请先添加收费项目业务"),

    /**
     * 未查询到考试费收费项目
     */
    QUERY_EXAM_PAYINFO_IS_EMPTY("40003", "未查询到考试费收费项目"),

    /**
     * 请输入流水号或身份证号码
     */
    PLEASE_INPUT_LSH_OR_SFZMHM("40004", "请输入流水号或身份证号码"),

    /**
     * 当前无欠费记录，未查询到缴费项目
     */
    CUR_HAVE_NO_ARREARS_RECORD("40005", "请输入流水号或身份证号码"),

    /**
     * 当前暂不支持POS支付
     */
    PAY_NOT_POS("40006", "当前暂不支持POS支付");

    private String code;
    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
