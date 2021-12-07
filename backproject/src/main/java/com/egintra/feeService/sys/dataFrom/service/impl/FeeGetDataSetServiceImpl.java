package com.egintra.feeService.sys.dataFrom.service.impl;

import com.egintra.common.dto.sys.FeeGetDataSetReqDTO;
import com.egintra.common.dto.sys.FeeGetDataSetRespDTO;
import com.egintra.common.dto.sys.FeeGetDataSetSaveReqDTO;
import com.egintra.common.exception.ResultCode;
import com.egintra.common.repository.mapper.FeeGetDataSetMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.PageHelperUtils;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.sys.dataFrom.service.FeeGetDataSetService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FeeGetDataSetServiceImpl implements FeeGetDataSetService {

    @Resource
    private FeeGetDataSetMapper feeGetDataSetMapper;

    /**
     * 初始化查询设置数据
     *
     * @param feeGetDataSetReqDTO 请求参数
     * @return 查询结果
     */
    @Override
    public DataReuslt queryDataSets(FeeGetDataSetReqDTO feeGetDataSetReqDTO) {

        // 设置分页
        Page page = PageHelper.startPage(feeGetDataSetReqDTO.getPage(), feeGetDataSetReqDTO.getSize());
        // 查询
        List<FeeGetDataSetRespDTO> list = feeGetDataSetMapper.queryDataSet(feeGetDataSetReqDTO);
        // 分页
        PageResult<FeeGetDataSetRespDTO> pagingData = PageHelperUtils.getPagingData(list, page);

        return DataReuslt.success(pagingData);
    }

    /**
     * 新增设置
     *
     * @param feeGetDataSetSaveReqDTO 请求参数
     * @return 结果
     */
    @Override
    @Transactional
    public DataReuslt saveDataSet(FeeGetDataSetSaveReqDTO feeGetDataSetSaveReqDTO) {

        // 判断是否已存在
        FeeGetDataSetRespDTO oneByModelId = feeGetDataSetMapper.getOneByModelId(feeGetDataSetSaveReqDTO.getModelid());
        if (null != oneByModelId) {
            // 提示“存在项目模块重复，无法保存"
            return DataReuslt.fail(ResultCode.UPDATE_DATASET_FAILED.code(), ResultCode.UPDATE_DATASET_FAILED.message());
        }

        // 不存在则新增
        feeGetDataSetMapper.insertDataSet(feeGetDataSetSaveReqDTO);

        // 返回结果
        return DataReuslt.success();
    }

    /**
     * 修改设置
     *
     * @param updates 请求参数
     * @return 结果
     */
    @Override
    @Transactional
    public DataReuslt updateDataSet(List<FeeGetDataSetSaveReqDTO> updates) {

        // 批量更新
        int inserts = feeGetDataSetMapper.batchUpdateDataSets(updates);

        // 返回结果
        return DataReuslt.success();
    }

    /**
     * 删除设置
     *
     * @param feeGetDataSetReqDTO 请求参数
     * @return 结果
     */
    @Override
    @Transactional
    public DataReuslt deleteDataSet(FeeGetDataSetReqDTO feeGetDataSetReqDTO) {

        // 根据主键删除
        feeGetDataSetMapper.deleteDataSet(feeGetDataSetReqDTO);

        // 返回结果
        return DataReuslt.success("删除成功");
    }
}
