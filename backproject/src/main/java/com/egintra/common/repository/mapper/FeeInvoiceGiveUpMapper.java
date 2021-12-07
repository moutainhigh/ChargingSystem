package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.repository.entity.FeeInvoiceGiveUp;
import org.apache.ibatis.annotations.Param;

/**
 * 发票作废Mapper
 *
 * @author liushihao
 * @date 2021/9/8
 */
public interface FeeInvoiceGiveUpMapper extends BaseMapper<FeeInvoiceGiveUp> {

    /**
     * 保存发票作废信息
     */
    void insertInvoiceGiveUpData(@Param("req") FeeInvoiceGiveUp feeInvoiceGiveUp);
}
