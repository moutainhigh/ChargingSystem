package com.egintra.feeService.sys.simpleCode.service.impl;

import com.egintra.common.dto.PubSimplecateReqDTO;
import com.egintra.common.dto.PubSimplecateRespDTO;
import com.egintra.common.dto.PubSimplenumberReqDTO;
import com.egintra.common.dto.PubSimplenumberRespDTO;
import com.egintra.common.dto.sys.VehDrvParamsRespDTO;
import com.egintra.common.enums.PubsimplecateEnum;
import com.egintra.common.enums.RecordStatusEnum;
import com.egintra.common.exception.ResultCode;
import com.egintra.common.repository.entity.PubSimplecate;
import com.egintra.common.repository.entity.PubSimplenumber;
import com.egintra.common.repository.mapper.PubSimplecateMapper;
import com.egintra.common.repository.mapper.PubSimplenumberMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.DateUtils;
import com.egintra.common.utils.PageHelperUtils;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.sys.simpleCode.service.SimpleCodeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SimpleCodeServiceImpl implements SimpleCodeService {

    @Resource
    private PubSimplecateMapper pubSimplecateMapper;
    @Resource
    private PubSimplenumberMapper pubSimplenumberMapper;

    /**
     * 初始化编码分类
     *
     * @return 结果
     */
    @Override
    public DataReuslt initCodeSorts() {

        // 初始化查询
        List<PubSimplecateRespDTO> initCodeSorts = pubSimplecateMapper.init();

        // 返回查询结果
        return DataReuslt.success(initCodeSorts);
    }

    /**
     * 初始化编码分类
     *
     * @param pubSimplecateReqDTO 参数
     * @return 结果
     */
    @Override
    public List<PubSimplecateRespDTO> queryCodeSortByNm(PubSimplecateReqDTO pubSimplecateReqDTO) {

        // 初始化查询
        List<PubSimplecateRespDTO> codeSort = pubSimplecateMapper.queryCodeSortByNm(pubSimplecateReqDTO);

        // 返回查询结果
        return codeSort;
    }

    /**
     * 初始化编码分类
     *
     * @param pubSimplecateReqDTO 请求参数对象
     * @return 结果
     */
    @Override
    public DataReuslt initCodeSortsList(PubSimplecateReqDTO pubSimplecateReqDTO) {

        // 设置分页
        Page page = PageHelper.startPage(pubSimplecateReqDTO.getPage(), pubSimplecateReqDTO.getSize());
        // 初始化查询
        List<PubSimplecateRespDTO> initCodeSorts = pubSimplecateMapper.initParams(pubSimplecateReqDTO);
        // 分页
        PageResult<PubSimplecateRespDTO> pagingData = PageHelperUtils.getPagingData(initCodeSorts, page);

        // 返回查询结果
        return DataReuslt.success(pagingData);
    }

    /**
     * 新增编码分类
     *
     * @param pubSimplecateReqDTO 请求参数
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt addCodeSorts(PubSimplecateReqDTO pubSimplecateReqDTO) {

        // 查验是否已经存在当前分类名称，已存在不可重复添加
        List<PubSimplecateRespDTO> pubSimplecates = pubSimplecateMapper.queryCodeSortByCd(pubSimplecateReqDTO);
        if (!CollectionUtils.isEmpty(pubSimplecates)) {
            return DataReuslt.fail(ResultCode.CUR_PUBSIMPLECATE_ALREADY_SAVED.code(),
                    ResultCode.CUR_PUBSIMPLECATE_ALREADY_SAVED.message());
        }

        // 对象数据拷贝到entity类
        PubSimplecate pubSimplecate = new PubSimplecate();
        BeanUtils.copyProperties(pubSimplecateReqDTO, pubSimplecate);
        // 设置其他必填字段
        pubSimplecate.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
        pubSimplecate.setRecordStatus(RecordStatusEnum.NO.getCode());
        Date currentDate = DateUtils.getCurrentDate();
        pubSimplecate.setRecordCreateTm(currentDate);
        pubSimplecate.setRecordModifyTm(currentDate);
        // 新增数据
        pubSimplecateMapper.insertPubSimplecate(pubSimplecate);

        // 返回结果
        return DataReuslt.success("保存成功");
    }

    /**
     * 删除编码分类
     *
     * @param pubSimplecateReqDTO 请求参数
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt deleteCodeSorts(PubSimplecateReqDTO pubSimplecateReqDTO) {

        // 查询当前编码分类下是否存在编码，如果存在，提示是否继续删除，继续删除也将删除当前编码分类下的所有编码
        // 根据参数查询当前编码分类
        PubSimplenumberReqDTO queryDto = new PubSimplenumberReqDTO();
        queryDto.setCateId(pubSimplecateReqDTO.getId());
        List<PubSimplenumberRespDTO> codeList = pubSimplenumberMapper.queryCodes(queryDto);
        // 如果当前编码分类下存在编码并且是第一次请求，返回是否继续删除的提示
        if (!CollectionUtils.isEmpty(codeList)
                && "0".equals(pubSimplecateReqDTO.getIsContinue())) {
            return DataReuslt.fail(ResultCode.CUR_CODE_SORT_HAVE_CODES.code(),
                    ResultCode.CUR_CODE_SORT_HAVE_CODES.message());
        }

        // 先根据ID删除分类编码：更新数据状态为'1'
        pubSimplecateMapper.deleteCodeSortById(pubSimplecateReqDTO);
        // 再判断如果当前分类编码下存在已创建的编码并且在继续删除中=>删除所有编码
        if (!CollectionUtils.isEmpty(codeList)
                && "1".equals(pubSimplecateReqDTO.getIsContinue())) {
            List<String> codes = codeList.stream()
                    .map(PubSimplenumberRespDTO::getId).collect(Collectors.toList());
            pubSimplenumberMapper.batchUpdateCodeById(codes);
        }

        return DataReuslt.success("删除成功");
    }

    /**
     * 更新编码分类
     *
     * @param pubSimplecateReqDTO 请求参数
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt updateCodeSorts(PubSimplecateReqDTO pubSimplecateReqDTO) {

        // 1.数据校验，判断是否修改时重复保存
        // 1）查询判断：id code 结果存在，保存；不存在=》 code 如果存在提示，不存在保存
        // 根据id code查
        PubSimplecateRespDTO pubSimplecateResp = pubSimplecateMapper.queryCodeSorts(pubSimplecateReqDTO);
        if (null == pubSimplecateResp) {
            // 只根据code查
            List<PubSimplecateRespDTO> pubSimplecateResps = pubSimplecateMapper.queryCodeSortByCd(pubSimplecateReqDTO);
            if (!CollectionUtils.isEmpty(pubSimplecateResps)) {
                return DataReuslt.fail(ResultCode.UPDATE_SIMPLECATE_FAILED.UPDATE_SIMPLECATE_FAILED.code(),
                        ResultCode.UPDATE_SIMPLECATE_FAILED.message());
            }
        }

        // 2.更新
        // 1）对象数据拷贝到entity类
        PubSimplecate pubSimplecate = new PubSimplecate();
        BeanUtils.copyProperties(pubSimplecateReqDTO, pubSimplecate);
        // 2）设置其他必填字段
        pubSimplecate.setRecordModifyTm(DateUtils.getCurrentDate());
        int i = pubSimplecateMapper.updateCodeSortById(pubSimplecate);

        // 返回结果
        return DataReuslt.success("保存成功");
    }

    /**
     * 初始化编码（根据编码分类查询编码）
     *
     * @param pubSimplenumberReqDTO 请求对象
     * @return 结果
     */
    @Override
    public DataReuslt initCode(PubSimplenumberReqDTO pubSimplenumberReqDTO) {

        // 设置分页
        Page page = PageHelper.startPage(pubSimplenumberReqDTO.getPage(), pubSimplenumberReqDTO.getSize());
        // 查询
        List<PubSimplenumberRespDTO> init = pubSimplenumberMapper.init(pubSimplenumberReqDTO);
        // 分页
        PageResult<PubSimplenumberRespDTO> pagingData = PageHelperUtils.getPagingData(init, page);

        // 返回查询结果
        return DataReuslt.success(pagingData);
    }

    /**
     * 新增编码
     *
     * @param pubSimplenumberReqDTO 请求对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt addCode(PubSimplenumberReqDTO pubSimplenumberReqDTO) {

        // 查验是否已经存在当前编码名称，已存在不可重复添加
        List<PubSimplenumberRespDTO> pubSimplenumbers = pubSimplenumberMapper.queryCodesByCd(pubSimplenumberReqDTO);
        if (!CollectionUtils.isEmpty(pubSimplenumbers)) {
            return DataReuslt.fail(ResultCode.CUR_PUBSIMPLENUMBER_ALREADY_SAVED.code(),
                    ResultCode.CUR_PUBSIMPLENUMBER_ALREADY_SAVED.message());
        }

        // 对象数据拷贝到entity类
        PubSimplenumber pubSimplenumber = new PubSimplenumber();
        BeanUtils.copyProperties(pubSimplenumberReqDTO, pubSimplenumber);
        // 设置其他必填字段
        pubSimplenumber.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
        pubSimplenumber.setRecordStatus(RecordStatusEnum.NO.getCode());
        Date currentDate = DateUtils.getCurrentDate();
        pubSimplenumber.setRecordCreateTm(currentDate);
        pubSimplenumber.setRecordModifyTm(currentDate);
        // 新增数据
        pubSimplenumberMapper.insertPubSimplenumber(pubSimplenumber);

        // 返回结果
        return DataReuslt.success("保存成功");
    }

    /**
     * 删除编码
     *
     * @param pubSimplenumberReqDTO 请求对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt deleteCode(PubSimplenumberReqDTO pubSimplenumberReqDTO) {

        // 根据ID更新数据状态为'1'
        pubSimplenumberMapper.updateCodeById(pubSimplenumberReqDTO);

        return DataReuslt.success("删除成功");
    }

    /**
     * 更新编码
     *
     * @param pubSimplenumberReqDTO 请求对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt updateCode(PubSimplenumberReqDTO pubSimplenumberReqDTO) {

        // 1.数据校验，判断是否修改时重复保存
        // 1）查询判断：id code 结果存在，保存；不存在=》 code 如果存在提示，不存在保存
        // 根据id code查
        PubSimplenumberRespDTO simplenumberResp = pubSimplenumberMapper.queryCodesByIdAndCd(pubSimplenumberReqDTO);
        if (null == simplenumberResp) {
            // 只根据code查
            List<PubSimplenumberRespDTO> simplenumberResps = pubSimplenumberMapper.queryCodesByCd(pubSimplenumberReqDTO);
            if (!CollectionUtils.isEmpty(simplenumberResps)) {
                return DataReuslt.fail(ResultCode.UPDATE_SIMPLECATE_FAILED.UPDATE_SIMPLECATE_FAILED.code(),
                        ResultCode.UPDATE_SIMPLECATE_FAILED.message());
            }
        }

        // 2.更新
        // 1）对象数据拷贝到entity类
        PubSimplenumber pubSimplenumber = new PubSimplenumber();
        BeanUtils.copyProperties(pubSimplenumberReqDTO, pubSimplenumber);
        // 2）设置其他必填字段
        pubSimplenumber.setRecordModifyTm(DateUtils.getCurrentDate());
        // 3）更新数据
        pubSimplenumberMapper.updateCodeInfoById(pubSimplenumber);

        // 返回结果
        return DataReuslt.success("保存成功");
    }

    /**
     * 根据分类编码查询具体编码内容
     *
     * @param code 分类编码
     * @return 结果
     */
    @Override
    public List<PubSimplenumberRespDTO> queryNumbersByCateCd(String code) {
        List<PubSimplenumberRespDTO> simplenumbes = pubSimplenumberMapper.queryNumbersByCateCd(code);

        return simplenumbes;
    }

    /**
     * 获取机动车收费业务信息的值
     *
     * @return 结果
     */
    @Override
    public DataReuslt getListByCode() {

        String hpzlCode = PubsimplecateEnum.JDCHPZL.getCode();
        String ywlxCode = PubsimplecateEnum.JDCYWLX.getCode();
        List<PubSimplenumberRespDTO> hpzlList = pubSimplenumberMapper.queryNumbersByCateCd(hpzlCode);
        List<PubSimplenumberRespDTO> ywlxList = pubSimplenumberMapper.queryNumbersByCateCd(ywlxCode);

        VehDrvParamsRespDTO vehDrvParamsRespDTO = new VehDrvParamsRespDTO();
        vehDrvParamsRespDTO.setHpzl(hpzlList);
        vehDrvParamsRespDTO.setYwlx(ywlxList);

        return DataReuslt.success(vehDrvParamsRespDTO);
    }

    /**
     * 根据code获取对应的数据
     *
     * @param pubSimplenumberReqDTO
     * @return
     */
    @Override
    public DataReuslt getCodeSortsListByType(PubSimplenumberReqDTO pubSimplenumberReqDTO) {
        // 账套状态
        String ztzt = PubsimplecateEnum.ZTZT.getCode();
        // 单位级别
        String dwjb = PubsimplecateEnum.DWJB.getCode();
        // 是否可选
        String sfkx = PubsimplecateEnum.SFKX.getCode();
        // 分类业务
        String flyw = PubsimplecateEnum.FLYW.getCode();
        // 收费属性
        String sfsx = PubsimplecateEnum.SFSX.getCode();
        // 用户级别
        String yhjb = PubsimplecateEnum.YHJB.getCode();

        // 根据type获取编码列表
        List<PubSimplenumberRespDTO> codeList = new ArrayList<>();
        if (ztzt.equals(pubSimplenumberReqDTO.getCode())) {
            codeList = pubSimplenumberMapper.queryNumbersByCateCd(ztzt);
        } else if (dwjb.equals(pubSimplenumberReqDTO.getCode())) {
            codeList = pubSimplenumberMapper.queryNumbersByCateCd(dwjb);
        } else if (sfkx.equals(pubSimplenumberReqDTO.getCode())) {
            codeList = pubSimplenumberMapper.queryNumbersByCateCd(sfkx);
        } else if (flyw.equals(pubSimplenumberReqDTO.getCode())) {
            codeList = pubSimplenumberMapper.queryNumbersByCateCd(flyw);
        } else if (sfsx.equals(pubSimplenumberReqDTO.getCode())) {
            codeList = pubSimplenumberMapper.queryNumbersByCateCd(sfsx);
        } else if (yhjb.equals(pubSimplenumberReqDTO.getCode())) {
            codeList = pubSimplenumberMapper.queryNumbersByCateCd(yhjb);
        }

        // 返回结果
        return DataReuslt.success(codeList);
    }
}
