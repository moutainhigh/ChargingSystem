package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.pay.FeePayinfoRespDTO;
import com.egintra.common.dto.pay.PayinfoReqDTO;
import com.egintra.common.repository.entity.FeePayinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayInfoMapper extends BaseMapper<FeePayinfo> {

    /**
     * 添加支付请求信息
     */
    void addPayInfo(PayinfoReqDTO payinfoReqDTO);

    /**
     * 修改支付状态
     *
     * @param payinfoReqDTO
     */
    void updateStatus(PayinfoReqDTO payinfoReqDTO);

    /**
     * 根据支付标记、记账标记、解锁标记查询支付信息表
     *
     * @param zfbj 支付标记
     * @param jzbj 记账标记
     * @param jsbj 解锁标记
     * @return 查询结果
     */

    List<FeePayinfoRespDTO> queryUnpaidInfos(@Param("zfbj") String zfbj, @Param("jzbj") String jzbj,
                                             @Param("jsbj") String jsbj);

    /**
     * 根据订单号查询当天未支付成功的
     *
     * @return
     */
    List<PayinfoReqDTO> getPayInfoList();

    /**
     * 根据order批量更新记账标记
     *
     * @param orderIds 参数
     * @return 结果
     */
    int batchUpdateBookingMark(@Param("list") List<String> orderIds, @Param("jzbj") String jzbj, @Param("jsbj") String jsbj);
}
