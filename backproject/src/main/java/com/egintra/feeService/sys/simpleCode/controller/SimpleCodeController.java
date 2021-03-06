package com.egintra.feeService.sys.simpleCode.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.PubSimplecateReqDTO;
import com.egintra.common.dto.PubSimplenumberReqDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.sys.simpleCode.service.SimpleCodeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统码表相关接口
 *
 * @author liushihao
 * @date 2021/06/29
 */
@RestController
@RequestMapping(value = "/simpleCode")
@ResponseResult
public class SimpleCodeController {

    @Resource
    private SimpleCodeService simpleCodeService;

    /**
     * 初始化编码分类
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping("/initCodeSorts")
    public DataReuslt initCodeSorts() {

        return simpleCodeService.initCodeSorts();
    }

    /**
     * 初始化编码分类
     *
     * @param pubSimplecateReqDTO 请求参数对象
     * @return 结果
     */
    @RequestMapping("/initCodeSortsList")
    public DataReuslt initCodeSortsList(@RequestBody PubSimplecateReqDTO pubSimplecateReqDTO) {

        return simpleCodeService.initCodeSortsList(pubSimplecateReqDTO);
    }

    /**
     * 新增编码分类
     *
     * @param pubSimplecateReqDTO 请求参数
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/addCodeSorts")
    public DataReuslt addCodeSorts(@RequestBody PubSimplecateReqDTO pubSimplecateReqDTO) {

        return simpleCodeService.addCodeSorts(pubSimplecateReqDTO);
    }

    /**
     * 删除编码分类
     *
     * @param pubSimplecateReqDTO 请求参数
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/deleteCodeSorts")
    public DataReuslt deleteCodeSorts(@RequestBody PubSimplecateReqDTO pubSimplecateReqDTO) {

        return simpleCodeService.deleteCodeSorts(pubSimplecateReqDTO);
    }

    /**
     * 更新编码分类
     *
     * @param pubSimplecateReqDTO 请求参数
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/updateCodeSorts")
    public DataReuslt updateCodeSorts(@RequestBody PubSimplecateReqDTO pubSimplecateReqDTO) {

        return simpleCodeService.updateCodeSorts(pubSimplecateReqDTO);
    }

    /**
     * 初始化编码（根据编码分类查询编码）
     *
     * @param pubSimplenumberReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping("/initCode")
    public DataReuslt initCode(@RequestBody PubSimplenumberReqDTO pubSimplenumberReqDTO) {

        return simpleCodeService.initCode(pubSimplenumberReqDTO);
    }

    /**
     * 新增编码
     *
     * @param pubSimplenumberReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/addCode")
    public DataReuslt addCode(@RequestBody PubSimplenumberReqDTO pubSimplenumberReqDTO) {

        return simpleCodeService.addCode(pubSimplenumberReqDTO);
    }

    /**
     * 删除编码
     *
     * @param pubSimplenumberReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/deleteCode")
    public DataReuslt deleteCode(@RequestBody PubSimplenumberReqDTO pubSimplenumberReqDTO) {

        return simpleCodeService.deleteCode(pubSimplenumberReqDTO);
    }

    /**
     * 更新编码
     *
     * @param pubSimplenumberReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/updateCode")
    public DataReuslt updateCode(@RequestBody PubSimplenumberReqDTO pubSimplenumberReqDTO) {

        return simpleCodeService.updateCode(pubSimplenumberReqDTO);
    }

    /**
     * 获取机动车收费业务信息的值
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getListByCode")
    public DataReuslt getListByCode() {
        return simpleCodeService.getListByCode();
    }

    /**
     * 根据code获取对应的列表信息
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getCodeSortsListByType")
    public DataReuslt getCodeSortsListByType(@RequestBody PubSimplenumberReqDTO pubSimplenumberReqDTO) {
        return simpleCodeService.getCodeSortsListByType(pubSimplenumberReqDTO);
    }
}
