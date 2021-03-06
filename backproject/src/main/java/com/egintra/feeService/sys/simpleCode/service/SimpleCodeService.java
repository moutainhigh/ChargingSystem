package com.egintra.feeService.sys.simpleCode.service;

import com.egintra.common.dto.PubSimplecateReqDTO;
import com.egintra.common.dto.PubSimplecateRespDTO;
import com.egintra.common.dto.PubSimplenumberReqDTO;
import com.egintra.common.dto.PubSimplenumberRespDTO;
import com.egintra.common.utils.DataReuslt;

import java.util.List;

public interface SimpleCodeService {

    /**
     * 初始化编码分类
     *
     * @return 结果
     */
    public DataReuslt initCodeSorts();

    /**
     * 根据编码分类名称查询编码分类
     *
     * @param pubSimplecateReqDTO 参数
     * @return 结果
     */
    public List<PubSimplecateRespDTO> queryCodeSortByNm(PubSimplecateReqDTO pubSimplecateReqDTO);

    /**
     * 初始化编码分类
     *
     * @param pubSimplecateReqDTO 请求参数对象
     * @return 结果
     */
    public DataReuslt initCodeSortsList(PubSimplecateReqDTO pubSimplecateReqDTO);

    /**
     * 新增编码分类
     *
     * @param pubSimplecateReqDTO 请求参数
     * @return 结果
     */
    public DataReuslt addCodeSorts(PubSimplecateReqDTO pubSimplecateReqDTO);

    /**
     * 删除编码分类
     *
     * @param pubSimplecateReqDTO 请求参数
     * @return 结果
     */
    public DataReuslt deleteCodeSorts(PubSimplecateReqDTO pubSimplecateReqDTO);

    /**
     * 更新编码分类
     *
     * @param pubSimplecateReqDTO 请求参数
     * @return 结果
     */
    public DataReuslt updateCodeSorts(PubSimplecateReqDTO pubSimplecateReqDTO);

    /**
     * 初始化编码（根据编码分类查询编码）
     *
     * @param pubSimplenumberReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt initCode(PubSimplenumberReqDTO pubSimplenumberReqDTO);

    /**
     * 新增编码
     *
     * @param pubSimplenumberReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt addCode(PubSimplenumberReqDTO pubSimplenumberReqDTO);

    /**
     * 删除编码
     *
     * @param pubSimplenumberReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt deleteCode(PubSimplenumberReqDTO pubSimplenumberReqDTO);

    /**
     * 更新编码
     *
     * @param pubSimplenumberReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt updateCode(PubSimplenumberReqDTO pubSimplenumberReqDTO);

    /**
     * 根据分类编码查询具体编码内容
     *
     * @param code 分类编码
     * @return 结果
     */
    public List<PubSimplenumberRespDTO> queryNumbersByCateCd(String code);

    /**
     * 获取机动车收费业务信息的值
     *
     * @return 结果
     */
    public DataReuslt getListByCode();

    /**
     * 根据code获取对应的值
     *
     * @param pubSimplenumberReqDTO
     * @return
     */
    public DataReuslt getCodeSortsListByType(PubSimplenumberReqDTO pubSimplenumberReqDTO);
}
