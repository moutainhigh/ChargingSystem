package com.egintra.feeService.finance.driverLicense.service.impl;

import com.egintra.common.dto.ProjectDetailReqDTO;
import com.egintra.common.dto.PubSimplenumberRespDTO;
import com.egintra.common.dto.finance.FeeDrvjkChangeReqDTO;
import com.egintra.common.dto.finance.FeeDrvjkReqDTO;
import com.egintra.common.dto.finance.FeeDrvjkRespDTO;
import com.egintra.common.dto.sys.VehDrvParamsRespDTO;
import com.egintra.common.enums.PubsimplecateEnum;
import com.egintra.common.exception.ResultCode;
import com.egintra.common.repository.mapper.FeeDrvjkMapper;
import com.egintra.common.repository.mapper.ProjectDetailMapper;
import com.egintra.common.repository.mapper.PubSimplenumberMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.PageHelperUtils;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.finance.driverLicense.service.FeeDriverLicenseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FeeDriverLicenseServiceImpl implements FeeDriverLicenseService {

    @Resource
    private FeeDrvjkMapper feeDrvjkMapper;
    @Resource
    private PubSimplenumberMapper pubSimplenumberMapper;
    @Resource
    private ProjectDetailMapper projectDetailMapper;

    /**
     * 初始化
     *
     * @param feeDrvjkReqDTO 参数
     * @return 初始化结果
     */
    @Override
    public DataReuslt driverLicenseInit(FeeDrvjkReqDTO feeDrvjkReqDTO) {

        // 初始化查询所有机动车业务收费信息数据
        List<FeeDrvjkRespDTO> feeDrvjks = feeDrvjkMapper.queryFeeDrvjks(feeDrvjkReqDTO);
        // stream分组所有数据:map<zjcx_ywlx_ywyy,List<T>>
        Map<String, List<FeeDrvjkRespDTO>> feeDrvjksMap = feeDrvjks.stream().collect(
                Collectors.groupingBy(x -> x.getZjcx() + "_" + x.getYwlx() + "_" + x.getYwyy()));
        // 如果查询结果为空，直接返回
        if (CollectionUtils.isEmpty(feeDrvjks)) {
            return DataReuslt.success();
        }

        // 根据zjcx、ywlx、ywyy分组分页查询数据
        Page page = PageHelper.startPage(feeDrvjkReqDTO.getPage(), feeDrvjkReqDTO.getSize());
        List<FeeDrvjkRespDTO> groupFeeDrvjks = feeDrvjkMapper.groupByFeeDrvjks(feeDrvjkReqDTO);

        // 循环list,构建String和mapkey匹配，统一汇总其他字段后返回list
        List<FeeDrvjkRespDTO> resultFeeDrvjks = new ArrayList<>();
        if (!CollectionUtils.isEmpty(groupFeeDrvjks)) {
            FeeDrvjkRespDTO feeDrvjkRespDTO;
            for (FeeDrvjkRespDTO group : groupFeeDrvjks) {
                // 拼接String,zjcx_ywlx_ywyy格式，匹配一开始stream分组的mapkey
                String mapKey = String.format("%s_%s_%s", group.getZjcx(), group.getYwlx(), group.getYwyy());
                // 当拼接String匹配后汇总收费项目名称返回
                if (feeDrvjksMap.containsKey(mapKey)) {
                    List<FeeDrvjkRespDTO> feeDrvjkList = feeDrvjksMap.get(mapKey);
                    List<String> projectNames = feeDrvjkList.stream().map(FeeDrvjkRespDTO::getProjectName)
                            .distinct().collect(Collectors.toList());
                    String allProjects = projectNames.stream().collect(Collectors.joining("，"));
                    feeDrvjkRespDTO = new FeeDrvjkRespDTO();
                    BeanUtils.copyProperties(group, feeDrvjkRespDTO);
                    feeDrvjkRespDTO.setProjectName(allProjects);
                    feeDrvjkRespDTO.setYwmc(feeDrvjkList.get(0).getYwmc());
                    resultFeeDrvjks.add(feeDrvjkRespDTO);
                }
            }

            // 查询结果集中部分字段英文替换中文
            String zjcx = PubsimplecateEnum.ZJCX.getCode();
            String ywlx = PubsimplecateEnum.JSZYWLX.getCode();
            // 根据编码分类代码分别查询准驾车型和 驾驶证业务类型具体编码参数
            List<PubSimplenumberRespDTO> zjcxs = pubSimplenumberMapper.queryNumbersByCateCd(zjcx);
            List<PubSimplenumberRespDTO> ywlxs = pubSimplenumberMapper.queryNumbersByCateCd(ywlx);

            // 准驾车型、业务类型map<code,name>
            Map<String, String> zjcxsMap = zjcxs.stream().collect(
                    Collectors.toMap(PubSimplenumberRespDTO::getCode, PubSimplenumberRespDTO::getName));
            Map<String, String> ywlxsMap = ywlxs.stream().collect(
                    Collectors.toMap(PubSimplenumberRespDTO::getCode, PubSimplenumberRespDTO::getName));
            // 循环查询结果替换英文为中文
            for (FeeDrvjkRespDTO resultFeeDrvjk : resultFeeDrvjks) {
                resultFeeDrvjk.setZjcx(zjcxsMap.get(resultFeeDrvjk.getZjcx()));
                resultFeeDrvjk.setYwlx(ywlxsMap.get(resultFeeDrvjk.getYwlx()));
            }
        }
        // 分页
        PageResult<FeeDrvjkRespDTO> pagingData = PageHelperUtils.getPagingData(resultFeeDrvjks, page);

        // 返回结果
        return DataReuslt.success(pagingData);
    }

    /**
     * 新增
     *
     * @param reqDTO 请求对象
     * @return 返回结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt addDriverLicense(FeeDrvjkChangeReqDTO reqDTO) {
        // 整理页面批量选择的收费项目，构建新集合
        List<FeeDrvjkReqDTO> feeDrvjks = new ArrayList<>();
        FeeDrvjkReqDTO feeDrvjk;
        for (ProjectDetailReqDTO dto : reqDTO.getPayProject()) {
            feeDrvjk = new FeeDrvjkReqDTO();
            BeanUtils.copyProperties(reqDTO, feeDrvjk);
            feeDrvjk.setQuantity(dto.getQuantity());
            feeDrvjk.setProjectId(dto.getProjectId());
            feeDrvjk.setOptions(dto.getOptions());
            feeDrvjks.add(feeDrvjk);
        }

        // 根据新集合批量查询是否已经存在当前收费信息设置，存在则报错提示
        List<FeeDrvjkRespDTO> feeDrvjkList = feeDrvjkMapper.batchQueryFeeDrvjks(feeDrvjks);
        if (!CollectionUtils.isEmpty(feeDrvjkList)) {
            // 报错提示：已存在收费项目***，请重新选择
            // 先汇总所有已存在的收费项目，并把所有收费项目名称以"，"隔开
            List<String> projectNames = feeDrvjkList.stream().map(FeeDrvjkRespDTO::getProjectName)
                    .distinct().collect(Collectors.toList());
            String errorProjects = projectNames.stream().collect(Collectors.joining("，"));
            // 返回错误提示:已存在收费项目***，请重新选择
            return DataReuslt.fail(ResultCode.ERROR_FOR_MORE_FEE_PROJECTS.code(),
                    String.format(ResultCode.ERROR_FOR_MORE_FEE_PROJECTS.message(), errorProjects));
        }

        // 批量插入驾驶证业务收费信息设置
        int inserts = feeDrvjkMapper.batchAddFeeDrvjk(feeDrvjks);

        return DataReuslt.success("保存成功");
    }

    /**
     * 修改
     *
     * @param reqDTO 请求对象
     * @return 返回结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt updateDriverLicense(FeeDrvjkChangeReqDTO reqDTO) {

        // 将请求参数中的中文转化为英文
        FeeDrvjkChangeReqDTO newReqDTO = this.getCode(reqDTO);

        // 先根据zjcx、ywlx、ywyy删除原数据
        // 根据zjcx、ywlx、ywyy查询数据，查询存在结果则删除原数据
        FeeDrvjkReqDTO feeDrvjkReqDTO = new FeeDrvjkReqDTO();
        BeanUtils.copyProperties(newReqDTO, feeDrvjkReqDTO);
        List<FeeDrvjkRespDTO> feeDrvjkS = feeDrvjkMapper.queryFeeDrvjks(feeDrvjkReqDTO);
        // 查询存在结果则删除原数据
        if (!CollectionUtils.isEmpty(feeDrvjkS)) {
            // 删除
            feeDrvjkMapper.deleteFeeDrvjk(feeDrvjkReqDTO);
        }
        // 再插入当前数据:循环“收费项目明细”构建批量保存数据集合
        List<FeeDrvjkReqDTO> feeDrvjks = new ArrayList<>();
        FeeDrvjkReqDTO feeDrvjk;
        for (ProjectDetailReqDTO dto : reqDTO.getPayProject()) {
            feeDrvjk = new FeeDrvjkReqDTO();
            BeanUtils.copyProperties(newReqDTO, feeDrvjk);
            feeDrvjk.setQuantity(dto.getQuantity());
            feeDrvjk.setProjectId(dto.getProjectId());
            feeDrvjk.setOptions(dto.getOptions());
            feeDrvjks.add(feeDrvjk);
        }

        // 更新数据：批量插入驾驶证业务收费信息设置
        if (!CollectionUtils.isEmpty(feeDrvjks)) {
            int inserts = feeDrvjkMapper.batchAddFeeDrvjk(feeDrvjks);
        }

        // 返回结果
        return DataReuslt.success("保存成功");
    }

    /**
     * 删除
     *
     * @param feeDrvjkReqDTO 请求对象
     * @return 返回结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt deleteDriverLicense(FeeDrvjkReqDTO feeDrvjkReqDTO) {
        // 将请求参数中的中文转化为英文
        FeeDrvjkChangeReqDTO feeDrvjkChangeReqDTO = new FeeDrvjkChangeReqDTO();
        BeanUtils.copyProperties(feeDrvjkReqDTO, feeDrvjkChangeReqDTO);
        FeeDrvjkChangeReqDTO newReqDTO = this.getCode(feeDrvjkChangeReqDTO);

        // 转换删除数据时的参数对象
        FeeDrvjkReqDTO newReqDto = new FeeDrvjkReqDTO();
        BeanUtils.copyProperties(newReqDTO, newReqDto);
        // 删除
        feeDrvjkMapper.deleteFeeDrvjk(newReqDto);

        // 返回结果
        return DataReuslt.success("删除成功");
    }

    /**
     * 查询
     *
     * @param feeDrvjkReqDTO 请求对象
     * @return 返回结果
     */
    @Override
    public DataReuslt queryDriverLicense(FeeDrvjkReqDTO feeDrvjkReqDTO) {

        // 在执行SQL时获取分页参数
        Page page = PageHelper.startPage(feeDrvjkReqDTO.getPage(), feeDrvjkReqDTO.getSize());
        // 查询驾驶证业务收费信息
        List<FeeDrvjkRespDTO> feeDrvjks = feeDrvjkMapper.queryFeeDrvjks(feeDrvjkReqDTO);
        // 分页
        PageResult<FeeDrvjkRespDTO> pagingData = PageHelperUtils.getPagingData(feeDrvjks, page);

        // 返回结果
        return DataReuslt.success(pagingData);
    }

    /**
     * 驾驶证业务收费新增页面初始化获取下拉框数据
     *
     * @return 返回结果
     */
    @Override
    public DataReuslt getSelectedData() {
        // 获取准驾车型、驾驶证业务类型编码分类代码
        String zjcx = PubsimplecateEnum.ZJCX.getCode();
        String ywlx = PubsimplecateEnum.JSZYWLX.getCode();

        // 根据编码分类代码分别查询准驾车型和 驾驶证业务类型具体编码参数
        List<PubSimplenumberRespDTO> zjcxs = pubSimplenumberMapper.queryNumbersByCateCd(zjcx);
        List<PubSimplenumberRespDTO> ywlxs = pubSimplenumberMapper.queryNumbersByCateCd(ywlx);
        // 查询获取收费项目明细
        ProjectDetailReqDTO dto = new ProjectDetailReqDTO();
        List<ProjectDetailReqDTO> payProjects = projectDetailMapper.getProjectDetailList(dto);

        // 封装返回对象
        VehDrvParamsRespDTO respDto = new VehDrvParamsRespDTO();
        respDto.setZjcx(zjcxs);
        respDto.setYwlx(ywlxs);
        respDto.setPayProject(payProjects);

        // 返回结果
        return DataReuslt.success(respDto);
    }

    /**
     * 驾驶证业务收费编辑页面初始化已设置收费项目明细
     *
     * @param reqDTO 请求参数
     * @return 返回结果
     */
    @Override
    public DataReuslt chargeableProjects(FeeDrvjkChangeReqDTO reqDTO) {
        // 将请求参数中的中文转化为英文
        FeeDrvjkChangeReqDTO newReqDTO = this.getCode(reqDTO);

        // 查询获取收费项目明细
        ProjectDetailReqDTO dto = new ProjectDetailReqDTO();
        List<ProjectDetailReqDTO> payProjects = projectDetailMapper.getProjectDetailList(dto);
        // 根据zjcx、ywlx、ywyy查询数据，查询已经设置的收费项目明细
        FeeDrvjkReqDTO feeDrvjkReqDTO = new FeeDrvjkReqDTO();
        BeanUtils.copyProperties(newReqDTO, feeDrvjkReqDTO);
        List<FeeDrvjkRespDTO> feeDrvjks = feeDrvjkMapper.queryFeeDrvjks(feeDrvjkReqDTO);

        // 已经设置的收费项目明细生成map<projectId,dto>
        List<String> projectIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(feeDrvjks)) {
            Map<String, FeeDrvjkRespDTO> feeDrvjksMap = feeDrvjks.stream().collect(
                    Collectors.toMap(v -> v.getProjectId(), v -> v));
            // 循环所有收费项目明细，对已经设置的收费项目添加“(可选项目)”或“(必选项目)”
            for (ProjectDetailReqDTO payProject : payProjects) {
                if (feeDrvjksMap.containsKey(payProject.getProjectId())
                        && "0".equals(feeDrvjksMap.get(payProject.getProjectId()).getOptions())) {
                    String optionalProject = String.format("%s(可选项目)", payProject.getProjectName());
                    payProject.setProjectName(optionalProject);
                }
                if (feeDrvjksMap.containsKey(payProject.getProjectId())
                        && "1".equals(feeDrvjksMap.get(payProject.getProjectId()).getOptions())) {
                    String requiredProject = String.format("%s(必选项目)", payProject.getProjectName());
                    payProject.setProjectName(requiredProject);
                }
            }
            // 获取已经设置的收费项目明细ID集合
            projectIds = feeDrvjks.stream().map(FeeDrvjkRespDTO::getProjectId).collect(Collectors.toList());

            // 已设置收费项目集合生成Map
            Map<String, FeeDrvjkRespDTO> projectMap = feeDrvjks.stream().collect(
                    Collectors.toMap(FeeDrvjkRespDTO::getProjectId, v -> v));
            // 对已设置收费项目设置对应的”数量“及“必选可选”
            for (ProjectDetailReqDTO payProject : payProjects) {
                if (projectMap.containsKey(payProject.getProjectId())) {
                    payProject.setQuantity(projectMap.get(payProject.getProjectId()).getQuantity());
                    payProject.setOptions(projectMap.get(payProject.getProjectId()).getOptions());
                }
            }
        }

        // 封装返回对象：所有收费项目集合（payProjects)、已设置收费项目ID集合(projectIds)
        VehDrvParamsRespDTO respDto = new VehDrvParamsRespDTO();
        respDto.setPayProject(payProjects);
        respDto.setPayProjectIds(projectIds);

        // 返回结果
        return DataReuslt.success(respDto);
    }

    /**
     * 驾驶证业务收费请求参数中文转英文
     *
     * @param reqDTO 请求参数
     * @return 转化结果
     */
    public FeeDrvjkChangeReqDTO getCode(FeeDrvjkChangeReqDTO reqDTO) {
        // zjcx、ywyy转换为中文
        // 获取准驾车型、驾驶证业务类型编码分类代码
        String zjcx = PubsimplecateEnum.ZJCX.getCode();
        String ywlx = PubsimplecateEnum.JSZYWLX.getCode();
        // 根据编码分类代码分别查询准驾车型和 驾驶证业务类型具体编码参数
        List<PubSimplenumberRespDTO> zjcxs = pubSimplenumberMapper.queryNumbersByCateCd(zjcx);
        List<PubSimplenumberRespDTO> ywlxs = pubSimplenumberMapper.queryNumbersByCateCd(ywlx);
        Map<String, String> zjcxsMap = zjcxs.stream().collect(
                Collectors.toMap(PubSimplenumberRespDTO::getName, PubSimplenumberRespDTO::getCode));
        Map<String, String> ywlxsMap = ywlxs.stream().collect(
                Collectors.toMap(PubSimplenumberRespDTO::getName, PubSimplenumberRespDTO::getCode));

        reqDTO.setZjcx(zjcxsMap.get(reqDTO.getZjcx()));
        reqDTO.setYwlx(ywlxsMap.get(reqDTO.getYwlx()));

        return reqDTO;
    }
}
