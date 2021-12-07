package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.DrvPreasignReqDTO;
import org.apache.ibatis.annotations.Param;

public interface DrvPreasignMapper extends BaseMapper<DrvPreasignReqDTO> {

    /**
     * 查看是否欠费
     *
     * @return 结果
     */
    int checkExamPay(DrvPreasignReqDTO drvPreasignReqDTO);

    /**
     * 根据流水号，身份证号查询信息
     *
     * @param drvPreasignReqDTO 参数
     * @return 结果
     */
    DrvPreasignReqDTO getDrvPreasignParam(@Param("req") DrvPreasignReqDTO drvPreasignReqDTO);
}
