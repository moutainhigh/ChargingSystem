package com.egintra.feeService.pay.service.impl;

import com.egintra.common.dto.pay.FeePayparaReqDTO;
import com.egintra.common.dto.pay.FeePayparaRespDTO;
import com.egintra.common.exception.ResultCode;
import com.egintra.common.repository.mapper.FeePayparaMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.pay.service.FeePayparaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FeePayparaServiceImpl implements FeePayparaService {

    @Resource
    private FeePayparaMapper feePayparaMapper;

    /**
     * 获取该管理部门下对应的线上缴费账套号、线上支付银行参数信息
     *
     * @param feePayparaReqDTO 参数
     * @return 结果
     */
    @Override
    public DataReuslt queryPayInfo(FeePayparaReqDTO feePayparaReqDTO) {

        // 查询
        FeePayparaRespDTO feePaypara = feePayparaMapper.queryFeePaypara(feePayparaReqDTO);
        if (feePaypara == null) {
            return DataReuslt.fail(ResultCode.QUERY_GLBM_PAYPARA_IS_EMPTY.code(),
                    ResultCode.QUERY_GLBM_PAYPARA_IS_EMPTY.message());
        }

        return DataReuslt.success(feePaypara);
    }
}
