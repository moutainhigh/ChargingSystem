package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.pay.FeePayparaReqDTO;
import com.egintra.common.dto.pay.FeePayparaRespDTO;
import com.egintra.common.repository.entity.FeePaypara;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeePayparaMapper extends BaseMapper<FeePaypara> {

    /**
     * 获取该管理部门下对应的线上缴费账套号、线上支付银行参数信息等
     *
     * @param feePayparaReqDTO 参数
     * @return 结果
     */
    FeePayparaRespDTO queryFeePaypara(@Param("req") FeePayparaReqDTO feePayparaReqDTO);
}
