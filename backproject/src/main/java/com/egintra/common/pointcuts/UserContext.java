package com.egintra.common.pointcuts;

import com.egintra.common.dto.SysUserDTO;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息处理
 *
 * @author liushihao
 * @date 2021/9/15
 */
public class UserContext {
    public static final ThreadLocal<Map<String, Object>> CONTEXT_DATA = new ThreadLocal<Map<String, Object>>() {
        protected Map<String, Object> initalValue() {
            return new HashMap<>(8);
        }
    };
    public static final String X_TOKEN = "X-TOKEN";
    public static final String X_USER_ID = "userId";
    public static final String X_NAME = "name";
    private static final String X_USER = "user";
    private static final String X_USER_CODE = "userCode";

    public UserContext() {

    }

    public static String getUserId() {
        return getStringValue(X_USER_ID);
    }

    public static void putUserId(Object value) {
        put(X_USER_ID, value);
    }

    public static String getUserName() {
        return getStringValue(X_NAME);
    }

    public static void putUserName(Object value) {
        put(X_NAME, value);
    }

    public static void putUserCode(Object value) {
        put(X_USER_CODE, value);
    }

    public static String getUserCode() {
        return getStringValue(X_USER_CODE);
    }

    public static String getToken() {
        return getStringValue(X_TOKEN);
    }

    public static void putToken(Object value) {
        put(X_TOKEN, value);
    }

    public static void put(String key, Object value) {
        // 限制传入的Key 和 value 都非空
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            throw new VdException("", "key或者value参数为空");
        }
        CONTEXT_DATA.get().put(key, value);
    }

    public static void init() {
        CONTEXT_DATA.set(new HashMap<>(8));
    }

    public static void remove() {
        CONTEXT_DATA.remove();
    }

    public static String getStringValue(String key) {
        if (CONTEXT_DATA.get() == null || !CONTEXT_DATA.get().containsKey(key)) {
            // 若用户信息项Map为空或者用户信息项Key为空，则返回null，具体处理逻辑由业务模块自行处理
            return null;
        } else {
            // 否则将用户信息项Value转换为字符串返回
            return String.valueOf(CONTEXT_DATA.get().get(key));
        }
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    public static SysUserDTO getUserInfo() {
        Object userInfo = (CONTEXT_DATA.get()).get(X_USER);
        if (userInfo == null) {
            throw new VdException("", "用户登录信息不存在");
        }
        return (SysUserDTO) userInfo;
    }

    /**
     * 把用户登录信息放到ThreadLocal中
     */
    public static void putUserInfo() {
        put(X_USER, new SysUserDTO(getUserId(), getUserName(), getUserCode()));
    }
}
