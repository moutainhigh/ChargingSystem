package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.pay.PayReqDTO;

import java.util.List;

public interface PayMapper extends BaseMapper<PayReqDTO> {

    /**
     * 添加线上支付请求信息
     */
    void addPay(PayReqDTO payReqDTO);


    /**
     * 修改支付状态
     *
     * @param payReqDTO
     */
    void updateStatus(PayReqDTO payReqDTO);


    /**
     * 根据订单号查询当天未支付成功的
     * @param PayReqDTO
     * @return
     */
    List<PayReqDTO> getPayList(PayReqDTO PayReqDTO);
}
