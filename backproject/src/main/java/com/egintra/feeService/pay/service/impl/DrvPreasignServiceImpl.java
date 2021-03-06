package com.egintra.feeService.pay.service.impl;

import com.egintra.common.dto.DrvPreasignReqDTO;
import com.egintra.common.exception.ResultCode;
import com.egintra.common.repository.mapper.DrvPreasignMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.pay.service.DrvPreasignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DrvPreasignServiceImpl implements DrvPreasignService {

    @Resource
    private DrvPreasignMapper dirPreasignMapper;

    /**
     * 考试费收费核查接口
     *
     * @param drvPreasign
     * @return
     */
    @Override
    public DataReuslt<Integer> checkExamPay(DrvPreasignReqDTO drvPreasign) {
        DrvPreasignReqDTO drvPreasignReqDTO = dirPreasignMapper.getDrvPreasignParam(drvPreasign);
        if (drvPreasignReqDTO != null) {
            drvPreasignReqDTO.setKsflb(drvPreasign.getKsflb());
            int count = dirPreasignMapper.checkExamPay(drvPreasignReqDTO);

            return DataReuslt.success(count);
        } else {
            return DataReuslt.fail(ResultCode.QUERY_EXAM_PAYINFO_IS_EMPTY.code(),
                    ResultCode.QUERY_EXAM_PAYINFO_IS_EMPTY.message());
        }
    }

    /**
     * 查询试预约数据(根据流水号或身份证号)
     *
     * @param drvPreasignReqDTO 请求参数
     * @return 结果
     */
    @Override
    public DataReuslt queryPreasigns(DrvPreasignReqDTO drvPreasignReqDTO) {

        DrvPreasignReqDTO drvPreasign = dirPreasignMapper.getDrvPreasignParam(drvPreasignReqDTO);

        return DataReuslt.success(drvPreasign);
    }
}
