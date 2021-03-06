package com.egintra.feeService.pay.service;

import com.egintra.common.dto.DrvPreasignReqDTO;
import com.egintra.common.utils.DataReuslt;

/**
 *
 */
public interface DrvPreasignService {

    /**
     * 考试费收费核查接口
     *
     * @param drvPreasign
     * @return
     */
    DataReuslt<Integer> checkExamPay(DrvPreasignReqDTO drvPreasign);

    /**
     * 查询试预约数据(根据流水号或身份证号)
     *
     * @param drvPreasign 请求参数
     * @return 结果
     */
    public DataReuslt queryPreasigns(DrvPreasignReqDTO drvPreasign);
}
