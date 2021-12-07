package com.egintra.feeService.pay.service.impl;

import com.egintra.common.dto.finance.FeeDrvjkMeterialRespDTO;
import com.egintra.common.dto.finance.FeeDrvjkReqDTO;
import com.egintra.common.repository.mapper.FeeDrvjkMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.pay.service.FeeInterfaceTwoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FeeInterfaceTwoServiceImpl implements FeeInterfaceTwoService {

    @Resource
    private FeeDrvjkMapper feeDrvjkMapper;

    /**
     * 获取驾驶证业务缴费信息服务接口
     *
     * @param feeDrvjkReqDTO 请求参数
     * @return 结果
     */
    public DataReuslt driverLicensePaymentInfo(FeeDrvjkReqDTO feeDrvjkReqDTO) {
        // 查询
        List<FeeDrvjkMeterialRespDTO> resp = feeDrvjkMapper.queryDriverLicensePaymentInfo(feeDrvjkReqDTO);

        return DataReuslt.success(resp);
    }
}
