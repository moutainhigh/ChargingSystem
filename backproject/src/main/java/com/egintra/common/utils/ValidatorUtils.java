package com.egintra.common.utils;


import com.baomidou.mybatisplus.extension.exceptions.ApiException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws ApiException 校验不通过，则报ApiException异常
     */
    public static void validateEntity(Object object, Class<?>... groups) throws Exception {

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        Map<String, String> map = new HashMap<>();
        if (!constraintViolations.isEmpty()) {
            StringBuffer stringBuffer = new StringBuffer();
            constraintViolations.forEach(o -> {
                map.put("message", o.getMessage());
                return;
            });
            if(map.size()!=0){
                throw new Exception(map.get("message"));
            }
        }
    }
}
