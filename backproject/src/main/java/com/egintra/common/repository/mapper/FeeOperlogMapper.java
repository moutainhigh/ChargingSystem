package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.pay.FeeOperlogSaveReqDTO;
import com.egintra.common.repository.entity.FeeOperlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 记账、解锁日志表Mapper
 *
 * @author liushihao
 * @date 2021/8/12
 */
public interface FeeOperlogMapper extends BaseMapper<FeeOperlog> {

    /**
     * 批量插入日志表数据
     *
     * @param inserts 数据集合
     * @return 结果
     */
    int batchInsertFeeOperlogs(@Param("list") List<FeeOperlogSaveReqDTO> inserts);
}
