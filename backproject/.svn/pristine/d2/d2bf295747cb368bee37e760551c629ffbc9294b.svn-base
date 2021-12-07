package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.pay.FeeInvoiceDetailRespDTO;
import com.egintra.common.dto.queryStatistics.QueryStatisticsReqDTO;
import com.egintra.common.repository.entity.FeeInvoiceDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 发票明细表Mapper接口
 *
 * @author liushihao
 * @date 2021/8/11
 */
public interface FeeInvoiceDetailMapper extends BaseMapper<FeeInvoiceDetail> {

    /**
     * 批量插入发票明细表
     *
     * @param details 参数
     * @return 结果
     */
    int batchInsertDetails(@Param("list") List<FeeInvoiceDetail> details);

    /**
     * 根据ID更新发票明细表状态
     *
     * @param id     发票id
     * @param status 状态
     * @return 结果
     */
    int updateStatusById(@Param("id") String id, @Param("status") String status);

    /**
     * 收费业务统计查询
     *
     * @param queryStatisticsReqDTO 查询对象
     * @return 结果
     */
    List<FeeInvoiceDetailRespDTO> queryChargeStatistics(@Param("req") QueryStatisticsReqDTO queryStatisticsReqDTO);
}
