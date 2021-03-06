package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.InvoiceDetailReqDTO;
import com.egintra.common.dto.InvoiceReqDTO;
import com.egintra.common.dto.business.FeeInvoiceGiveUpQueryRespDTO;
import com.egintra.common.dto.business.FeeInvoiceGiveUpQueryReqDTO;
import com.egintra.common.dto.business.FeeInvoiceGiveUpReqDTO;
import com.egintra.common.repository.entity.FeeInvoice;
import com.egintra.common.repository.entity.FeeInvoiceDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 发票主表Mapper
 *
 * @author liushihao
 * @date 2021/9/8
 */
public interface InvoiceMapper extends BaseMapper<FeeInvoice> {

    /**
     * 车驾管业务收费核查接口
     *
     * @param lsh
     * @return
     */
    InvoiceReqDTO isPaymentNot(@Param("lsh") String lsh);

    /**
     * 批量插入发票主表数据
     *
     * @param feeInvoices 参数
     * @return 结果
     */
    int batchInsertInvoices(@Param("list") List<FeeInvoice> feeInvoices);

    /**
     * 发票作废时查询发票信息
     *
     * @param reqDTO 参数
     * @return 查询结果
     */
    List<FeeInvoiceGiveUpQueryRespDTO> queryInvoiceList(@Param("req") FeeInvoiceGiveUpQueryReqDTO reqDTO);

    /**
     * 根据ID查询发票信息
     *
     * @param reqDTO 参数
     * @return 查询结果
     */
    FeeInvoiceGiveUpQueryRespDTO queryInvoiceById(@Param("req") FeeInvoiceGiveUpReqDTO reqDTO);

    /**
     * 根据ID更新发票状态
     *
     * @param id 发票id
     * @param status 状态
     * @return 结果
     */
    int updateStatusById(@Param("id") String id,@Param("status") String status);

    /**
     * 根据条件获取发票信息
     * @param invoiceReqDTO
     * @return
     */
    List<FeeInvoice> getInvoiceList(InvoiceReqDTO invoiceReqDTO);

    /**
     * 查看发票详情
     * @param feeInvoiceId
     * @return
     */
    List<FeeInvoiceDetail> getInvoiceDetailListById(String feeInvoiceId);



    /**
     * 查看发票详情列表
     * @param invoiceDetailReqDTO
     * @return
     */
    List<InvoiceDetailReqDTO> getFeeInvoiceDetailList(InvoiceDetailReqDTO invoiceDetailReqDTO);
}
