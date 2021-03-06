package com.egintra.feeService.sys.menu.service.impl;

import com.egintra.common.dto.FunctionReqDTO;
import com.egintra.common.dto.FunctionRespDTO;
import com.egintra.common.dto.RightFunccateReqDTO;
import com.egintra.common.dto.RightUserDTO;
import com.egintra.common.enums.RecordStatusEnum;
import com.egintra.common.exception.ResultCode;
import com.egintra.common.repository.entity.RightFunccate;
import com.egintra.common.repository.entity.RightFunction;
import com.egintra.common.repository.mapper.RightFunccateMapper;
import com.egintra.common.repository.mapper.RightFunctionMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.DateUtils;
import com.egintra.common.utils.PageHelperUtils;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.sys.menu.service.MenuService;
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
public class MenuServiceImpl implements MenuService {

    @Resource
    private RightFunctionMapper rightFunctionMapper;
    @Resource
    private RightFunccateMapper rightFunccateMapper;

    /**
     * 根据权限获取对应菜单树
     *
     * @param rightUserDTO 用户信息
     * @return
     */
    @Override
    public DataReuslt getCurRoleMenus(RightUserDTO rightUserDTO) {
        // 根据用户名获取当前用户所属角色对应功能菜单信息
        List<FunctionRespDTO> functionList = rightFunctionMapper.querycurUserFunctions(rightUserDTO.getId());
        // 构建菜单
        List<FunctionRespDTO> menus = new ArrayList<>();
        // 如果未分配功能权限直接返回空结果
        if (CollectionUtils.isEmpty(functionList)) {
            return DataReuslt.success(menus);
        }
        // map<功能分类ID，功能分类所属功能集合>
        Map<String, List<FunctionRespDTO>> functionMap
                = functionList.stream().collect(Collectors.groupingBy(FunctionRespDTO::getFunccateId));
        // 根据查询结果获取的功能分类ID集合（去重）
        List<String> funccateIds = functionList.stream().map(FunctionRespDTO::getFunccateId)
                .distinct().collect(Collectors.toList());
        // 获取分类菜单信息:先获取所有菜单分类结果集，再获取当前用户所有功能对应的功能分类集合
        List<FunctionRespDTO> funccateList = rightFunccateMapper.queryFunccateInfo(new RightFunccateReqDTO());
        List<FunctionRespDTO> curUserFunccates = funccateList.stream().filter(p -> funccateIds.contains(p.getId()))
                .collect(Collectors.toList());
        menus.addAll(curUserFunccates);
        // 循环分类菜单列表，设置下级功能菜单信息
        for (FunctionRespDTO menu : menus) {
            if (functionMap.containsKey(menu.getId())) {
                menu.setChildList(functionMap.get(menu.getId()));
            }
        }

        // 返回构建完的菜单目录
        return DataReuslt.success(menus);
    }

    /**
     * 获取菜单
     *
     * @return 菜单信息
     */
    @Override
    public DataReuslt getMenus() {
        // 获取分类菜单信息
        List<FunctionRespDTO> funccateList = rightFunccateMapper.queryFunccateInfo(new RightFunccateReqDTO());
        // 获取功能菜单信息
        List<FunctionRespDTO> functionList = rightFunctionMapper.queryFunctionInfo(new FunctionReqDTO());
        Map<String, List<FunctionRespDTO>> functionMap
                = functionList.stream().collect(Collectors.groupingBy(FunctionRespDTO::getFunccateId));

        // 构建菜单
        List<FunctionRespDTO> menus = new ArrayList<>();
        menus.addAll(funccateList);
        // 循环分类菜单列表，设置下级功能菜单信息
        for (FunctionRespDTO menu : menus) {
            if (functionMap.containsKey(menu.getId())) {
                menu.setChildList(functionMap.get(menu.getId()));
            }
        }

        // 返回构建完的菜单目录
        return DataReuslt.success(menus);
    }

    /**
     * 查询分类菜单列表
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果
     */
    @Override
    public DataReuslt getFunccate(RightFunccateReqDTO rightFunccateReqDTO) {

        // 获取分类菜单信息
        List<FunctionRespDTO> funccateList = rightFunccateMapper.queryFunccateInfo(rightFunccateReqDTO);

        return DataReuslt.success(funccateList);
    }

    /**
     * 查询分类菜单列表
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果/
     */
    @Override
    public DataReuslt getFunccateList(RightFunccateReqDTO rightFunccateReqDTO) {
        // 查询所有角色信息
        Page page = PageHelper.startPage(rightFunccateReqDTO.getPage(), rightFunccateReqDTO.getSize());
        List<FunctionRespDTO> funccateList = rightFunccateMapper.queryFunccateInfo(rightFunccateReqDTO);
        // 分页
        PageResult<FunctionRespDTO> pagingData = PageHelperUtils.getPagingData(funccateList, page);

        // 返回结果
        return DataReuslt.success(pagingData);
    }

    /**
     * 查询当前分类菜单下功能菜单列表
     *
     * @param functionReqDTO 请求对象
     * @return 结果
     */
    @Override
    public DataReuslt getFuncMenus(FunctionReqDTO functionReqDTO) {

        // 获取所有功能菜单信息
        Page page = PageHelper.startPage(functionReqDTO.getPage(), functionReqDTO.getSize());
        List<FunctionRespDTO> functionList = rightFunctionMapper.queryFunctionInfo(functionReqDTO);
        // 移除非当前分类菜单下其他功能菜单
        functionList.removeIf(p -> !functionReqDTO.getFunccateId().equals(p.getFunccateId()));
        // 分页
        PageResult<FunctionRespDTO> pagingData = PageHelperUtils.getPagingData(functionList, page);

        // 返回结果
        return DataReuslt.success(pagingData);
    }

    /**
     * 根据分类菜单获取当前角色下对应的功能菜单列表
     *
     * @param functionReqDTO 请求参数
     * @return 结果
     */
    @Override
    public DataReuslt getFunctionMenus(FunctionReqDTO functionReqDTO) {

        // 获取所有功能菜单信息
        List<FunctionRespDTO> functionList = rightFunctionMapper.queryFunctionInfo(new FunctionReqDTO());
        // 获取当前分类菜单下的功能菜单
        if (!CollectionUtils.isEmpty(functionList)) {
            functionList.removeIf(p -> !functionReqDTO.getId().equals(p.getFunccateId()));
        }

        // 返回结果
        return DataReuslt.success(functionList);
    }

    /**
     * 新增分类菜单
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt addFunccateMenu(RightFunccateReqDTO rightFunccateReqDTO) {

        // 设置保存参数
        RightFunccate rightFunccate = new RightFunccate();
        BeanUtils.copyProperties(rightFunccateReqDTO, rightFunccate);
        rightFunccate.setRecordStatus(RecordStatusEnum.NO.getCode());
        rightFunccate.setRecordCreateTm(DateUtils.getCurrentDate());

        // 保存数据
        Integer insert = rightFunccateMapper.insert(rightFunccate);

        // 返回结果
        return DataReuslt.success("保存成功");
    }

    /**
     * 新增功能菜单
     *
     * @param functionReqDTO 请求对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt addFunctionMenu(FunctionReqDTO functionReqDTO) {

        // 设置保存参数
        RightFunction rightFunction = new RightFunction();
        BeanUtils.copyProperties(functionReqDTO, rightFunction);
        rightFunction.setRecordStatus(RecordStatusEnum.NO.getCode());
        rightFunction.setRecordCreateTm(DateUtils.getCurrentDate());

        // 保存数据
        Integer insert = rightFunctionMapper.insert(rightFunction);

        // 返回结果
        return DataReuslt.success("保存成功");
    }

    /**
     * 修改分类菜单
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt updateFunccateMenu(RightFunccateReqDTO rightFunccateReqDTO) {

        // 根据ID修改分类菜单
        rightFunccateReqDTO.setRecordModifyTm(DateUtils.getCurrentDate());
        int update = rightFunccateMapper.updateFunccate(rightFunccateReqDTO);

        // 返回结果
        return DataReuslt.success("保存成功");
    }

    /**
     * 修改功能菜单
     *
     * @param functionReqDTO 请求对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt updateFunctionMenu(FunctionReqDTO functionReqDTO) {

        // 根据ID修改功能菜单
        functionReqDTO.setRecordModifyTm(DateUtils.getCurrentDate());
        int update = rightFunctionMapper.updateFunction(functionReqDTO);

        // 返回结果
        return DataReuslt.success("保存成功");
    }

    /**
     * 删除分类菜单（注意提示将会删除所有下级）
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt deleteFunccateMenu(RightFunccateReqDTO rightFunccateReqDTO) {

        // 1.根据分类菜单ID查询下级功能菜单。如果存在提示是否删除，如果坚持删除，则全部删除
        // 获取所有功能菜单信息并且获取当前分类菜单下的功能菜单
        List<FunctionRespDTO> functionList = rightFunctionMapper.queryFunctionInfo(new FunctionReqDTO());
        if (!CollectionUtils.isEmpty(functionList)) {
            functionList.removeIf(p -> !rightFunccateReqDTO.getId().equals(p.getFunccateId()));
        }
        if (!CollectionUtils.isEmpty(functionList)
                && "0".equals(rightFunccateReqDTO.getIsContinue())) {
            // 如果当前分类菜单ID存在下级功能菜单，提示是否删除
            return DataReuslt.success(ResultCode.CUR_FUNCCATE_MENU_HAVE_SUBMENUS.code(),
                    ResultCode.CUR_FUNCCATE_MENU_HAVE_SUBMENUS.message());
        }

        // 2.如果存在下级功能菜单并且坚持删除
        List<String> subMenus = null;
        if ("1".equals(rightFunccateReqDTO.getIsContinue())) {
            subMenus = functionList.stream().map(FunctionRespDTO::getId).collect(Collectors.toList());
            // 删除下级功能菜单（更新状态为1）
            rightFunctionMapper.batchDeleteFunctions(subMenus, RecordStatusEnum.YES.getCode());
        }

        // 3.删除分类菜单
        int delete = rightFunccateMapper.deleteFunccate(rightFunccateReqDTO);
        // 4.如果删除下级功能菜单成功但是删除分类菜单失败，回滚下级功能菜单数据状态为‘0’
        if (delete != 1 && !CollectionUtils.isEmpty(subMenus)) {
            // 删除下级功能菜单（更新状态为1）
            rightFunctionMapper.batchDeleteFunctions(subMenus, RecordStatusEnum.NO.getCode());
        }

        // 返回结果
        return DataReuslt.success("删除成功");
    }

    /**
     * 删除功能菜单
     *
     * @param functionReqDTO 请求对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt deleteFunctionMenu(FunctionReqDTO functionReqDTO) {

        // 根据ID直接删除功能菜单
        rightFunctionMapper.deleteFunction(functionReqDTO);

        // 返回结果
        return DataReuslt.success();
    }

    /**
     * 根据功能菜单获取按钮列表
     *
     * @param functionReqDTO 请求参数
     * @return 结果
     */
    @Override
    public DataReuslt getFuncActions(FunctionReqDTO functionReqDTO) {

//        // 根据功能菜单ID查询按钮
//        List<RightFuncActionRespDTO> curFuncActions = rightFuncActionMapper.getCurFuncActions(functionReqDTO);
//
//        // 循环判断，如果功能菜单已经配置了按钮，则设置已经配置了的按钮为YES('1')；如果功能菜单未配置按钮，默认全部按钮权限为YES('1')
//        if (!CollectionUtils.isEmpty(curFuncActions)) {
//            if (!StringUtil.isEmpty(curFuncActions.get(0).getActions())) {
//                List<String> actionNms = Arrays.asList(curFuncActions.get(0).getActions().split(","));
//                for (RightFuncActionRespDTO action : curFuncActions) {
//                    if (actionNms.contains(action.getName())) {
//                        action.setIsPower(MarkEnum.YES.getCode());
//                    }
//                }
//            } else {
//                curFuncActions.forEach(p -> p.setIsPower(MarkEnum.YES.getCode()));
//            }
//        }

        // 返回结果
        return DataReuslt.success();
    }
}
