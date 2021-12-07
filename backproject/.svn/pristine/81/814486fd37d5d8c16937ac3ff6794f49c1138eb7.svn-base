package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.InvoiceTmpDTO;
import com.egintra.common.dto.pay.FeeInvoiceTmpRespDTO;
import com.egintra.common.repository.entity.FeeInvoiceTmp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 临时记账发票主表mapper接口
 *
 * @author liushihao
 * @date 2021/8/11
 */
public interface InvoiceTmpMapper extends BaseMapper<FeeInvoiceTmp> {

    /**
     * 添加临时记账
     *
     * @param invoiceTmp
     * @return 结果
     */
    int addInvoiceTmp(InvoiceTmpDTO invoiceTmp);

    /**
     * 通过orderId查询临时记账发票主表
     *
     * @param orderId 参数
     * @return 查询结果
     */
    List<FeeInvoiceTmpRespDTO> queryFeeInvoiceTmps(@Param("orderId") String orderId);
}
