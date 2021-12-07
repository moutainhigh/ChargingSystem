package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.finance.FeeDrvjkMeterialRespDTO;
import com.egintra.common.dto.finance.FeeDrvjkReqDTO;
import com.egintra.common.dto.finance.FeeDrvjkRespDTO;
import com.egintra.common.repository.entity.FeeDrvjk;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeeDrvjkMapper extends BaseMapper<FeeDrvjk> {

    /**
     * 初始化查询驾驶证业务收费信息
     *
     * @return
     */
    List<FeeDrvjkRespDTO> feeDrvjksInit();

    /**
     * 根据条件查询驾驶证业务收费信息
     *
     * @param feeDrvjkReqDTO 参数
     * @return 结果
     */
    List<FeeDrvjkRespDTO> queryFeeDrvjks(@Param("req") FeeDrvjkReqDTO feeDrvjkReqDTO);

    /**
     * 根据条件分组查询驾驶证业务收费信息
     *
     * @param feeDrvjkReqDTO 参数
     * @return 结果
     */
    List<FeeDrvjkRespDTO> groupByFeeDrvjks(@Param("req") FeeDrvjkReqDTO feeDrvjkReqDTO);

    /**
     * 根据条件批量查询驾驶证业务收费信息
     *
     * @param feeDrvjks 参数
     * @return 结果
     */
    List<FeeDrvjkRespDTO> batchQueryFeeDrvjks(@Param("list") List<FeeDrvjkReqDTO> feeDrvjks);

    /**
     * 新增驾驶证业务收费信息
     *
     * @param feeDrvjkReqDTO
     * @return
     */
    int addFeeDrvjk(@Param("req") FeeDrvjkReqDTO feeDrvjkReqDTO);

    /**
     * 批量新增驾驶证业务收费信息
     *
     * @param feeDrvjks 新增数据集合
     * @return 新增结果
     */
    int batchAddFeeDrvjk(@Param("list") List<FeeDrvjkReqDTO> feeDrvjks);

    /**
     * 删除驾驶证业务收费信息
     *
     * @param feeDrvjkReqDTO
     */
    void deleteFeeDrvjk(@Param("req") FeeDrvjkReqDTO feeDrvjkReqDTO);

    /**
     * 批量更新驾驶证业务收费信息
     *
     * @param feeDrvjks 参数
     * @return 结果
     */
    int batchUpdateFeeDrvjks(@Param("list") List<FeeDrvjkReqDTO> feeDrvjks);

    /**
     * 获取驾驶证业务缴费信息服务接口
     *
     * @param feeDrvjkReqDTO 请求参数
     * @return 查询结果
     */
    List<FeeDrvjkMeterialRespDTO> queryDriverLicensePaymentInfo(@Param("req") FeeDrvjkReqDTO feeDrvjkReqDTO);
}
