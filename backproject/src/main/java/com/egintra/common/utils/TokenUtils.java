package com.egintra.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token生成验证工具类
 */
public class TokenUtils {

    //设置过期时间
    private static final long EXPIRE_DATE = 30 * 60 * 100000;
    //token秘钥
    private static final String TOKEN_SECRET = "ZCfasfhuaUUHufguGuwu2020BQWE";

    /**
     * 生成token
     *
     * @param userId   用户Id
     * @param password 密码
     * @return 结果
     */
    public static String getToken(String userId, String password) {
        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("userId", userId)
                    .withClaim("password", password).withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return token;
    }

    /**
     * 解密token
     *
     * @param token token
     * @return 解密结果
     */
    public static Map<String, Claim> verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("token已过期");
        }

        return jwt.getClaims();
    }

    /**
     * 根据token获取userId
     *
     * @return userId
     */
    public static String getTokenUserId() {
        // 从http请求头中取出token
        String token = getRequest().getHeader("token");
        Map<String, Claim> claimMap = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token).getClaims();
        // 返回userId
        return claimMap.get("userId").asString();
    }

    /**
     * 获取request请求
     *
     * @return request请求
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    public static void main(String[] args) {
        String username = "111";
        String password = "222";
        String token = getToken(username, password);
        System.out.println("token是" + token);
    }
}
