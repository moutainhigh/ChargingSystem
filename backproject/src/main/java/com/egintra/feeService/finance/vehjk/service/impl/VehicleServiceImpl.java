package com.egintra.feeService.finance.vehjk.service.impl;

import com.egintra.common.dto.VehicleReqDTO;
import com.egintra.common.repository.mapper.VehicleMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.finance.vehjk.service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Resource
    private VehicleMapper vehicleMapper;

    /**
     * 查看列表(分页)
     *
     * @param vehicleReqDTO
     * @return
     */
    @Override
    public DataReuslt getVehicleList(VehicleReqDTO vehicleReqDTO) {
        List<VehicleReqDTO> materialReqDTOS = vehicleMapper.getVehicleList(vehicleReqDTO);
        List<VehicleReqDTO> newList = new ArrayList<>();
        try {
            Map<String, List<VehicleReqDTO>> map = groupBillingDataByExcpBatchCode(materialReqDTOS);
            for (VehicleReqDTO vehicleReqDTO1 : materialReqDTOS) {
                String key = vehicleReqDTO1.getHpzl() + vehicleReqDTO1.getYwyy() + vehicleReqDTO1.getYwlx();
                List<VehicleReqDTO> vehicleReqDTOList = map.get(key);
                StringBuffer nameBuffer = new StringBuffer();
                StringBuffer projectIdBuffer = new StringBuffer();
                vehicleReqDTOList.forEach(vehicle -> {
                    nameBuffer.append(vehicle.getProjectName() + "，");
                    projectIdBuffer.append(vehicle.getProjectId() + ",");
                });
                String pName = nameBuffer.deleteCharAt(nameBuffer.length() - 1).toString();
                String projectId = projectIdBuffer.deleteCharAt(projectIdBuffer.length() - 1).toString();

                VehicleReqDTO vehicleReqDTO2 = vehicleReqDTOList.get(0);
                VehicleReqDTO veh = new VehicleReqDTO();
                veh.setProjectName(pName);
                veh.setHpzl(vehicleReqDTO2.getHpzl());
                veh.setYwlx(vehicleReqDTO2.getYwlx());
                veh.setYwyy(vehicleReqDTO2.getYwyy());
                veh.setQuantity(vehicleReqDTO2.getQuantity());
                veh.setOptions(vehicleReqDTO2.getOptions());
                veh.setYwmc(vehicleReqDTO2.getYwmc());
                veh.setpId(projectId);
                veh.setHpzlName(vehicleReqDTO2.getHpzlName());
                veh.setYwlxName(vehicleReqDTO2.getYwlxName());
                newList.add(veh);
            }
            List<VehicleReqDTO> vehicleReqDTOList = new ArrayList<>();
            if (newList.size() != 0) {
                newList = newList.stream()
                        .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(f -> f.getYwyy() + f.getHpzl() + f.getYwyy()))), ArrayList::new));
                vehicleReqDTOList = startPage(newList, vehicleReqDTO.getPage(), vehicleReqDTO.getSize());
            }
            PageResult<VehicleReqDTO> result = new PageResult<>();
            result.setRows(vehicleReqDTOList);
            long total = newList.size();
            result.setTotal(total);
            result.setPages(vehicleReqDTO.getPage());
            result.setCurrentPage(0);
            return DataReuslt.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 分页 每页展示10条数据
     *
     * @param list
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static List startPage(List<VehicleReqDTO> list, Integer pageNum,
                                 Integer pageSize) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }
        // 记录总数
        Integer count = list.size();
        // 页数
        Integer pageCount = 0;
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }
        // 开始索引
        int fromIndex = 0;
        // 结束索引
        int toIndex = 0;

        if (pageNum != pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        List pageList = list.subList(fromIndex, toIndex);

        return pageList;
    }

    /**
     * 查看列表(不分页)
     *
     * @param vehicleReqDTO
     * @return
     */
    @Override
    public DataReuslt getVehicleLists(VehicleReqDTO vehicleReqDTO) {
        List<VehicleReqDTO> departments = vehicleMapper.getVehicleList(vehicleReqDTO);

        return DataReuslt.success(departments);
    }

    /**
     * 新增
     *
     * @param vehjkReqDTOS
     * @return
     */
    @Override
    @Transactional
    public DataReuslt addVehicle(List<VehicleReqDTO> vehjkReqDTOS) {

        boolean result = false;
        for (VehicleReqDTO vehicleReqDTO : vehjkReqDTOS) {
            VehicleReqDTO material = vehicleMapper.getOne(vehicleReqDTO);
            if (material != null) {
                result = true;
            }
        }
        if (result == true) {
            return DataReuslt.fail("选择的收费项目在系统中已经存在");
        }
        vehicleMapper.addVehicle(vehjkReqDTOS);

        return DataReuslt.success(vehjkReqDTOS);
    }

    /**
     * 编辑
     *
     * @param vehicleReqDTOList
     * @return
     */
    @Override
    @Transactional
    public DataReuslt editVehicle(List<VehicleReqDTO> vehicleReqDTOList) {
        VehicleReqDTO vehicle = vehicleReqDTOList.get(0);
        // 刪除之前的数据
        vehicleMapper.deleteById(vehicle);
        // 从新添加数据
        vehicleMapper.addVehicle(vehicleReqDTOList);
        return DataReuslt.success(vehicleReqDTOList);
    }

    /**
     * 删除
     *
     * @param vehjkReqDTO
     * @return
     */
    @Override
    public DataReuslt deleteById(VehicleReqDTO vehjkReqDTO) {
        vehicleMapper.deleteById(vehjkReqDTO);

        return DataReuslt.success("删除成功");
    }

    /**
     * 将根据好牌种类,业务类型,业务原因分组
     *
     * @param billingList
     * @return
     * @throws Exception
     */
    private static Map<String, List<VehicleReqDTO>> groupBillingDataByExcpBatchCode(List<VehicleReqDTO> billingList) throws Exception {
        Map<String, List<VehicleReqDTO>> resultMap = new HashMap<>();

        try {
            for (VehicleReqDTO tmExcpNew : billingList) {
                String key = tmExcpNew.getHpzl() + tmExcpNew.getYwyy() + tmExcpNew.getYwlx();
                // map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                if (resultMap.containsKey(key)) {
                    resultMap.get(key).add(tmExcpNew);
                } else {
                    // map中不存在，新建key，用来存放数据
                    List<VehicleReqDTO> tmpList = new ArrayList<VehicleReqDTO>();
                    tmpList.add(tmExcpNew);
                    resultMap.put(key, tmpList);
                }
            }
        } catch (Exception e) {
            throw new Exception("按照异常批次号对已开单数据进行分组时出现异常", e);
        }

        return resultMap;
    }

    /**
     * 查看信息详情
     *
     * @param vehjkReqDTO
     * @return
     */
    @Override
    public DataReuslt getOne(VehicleReqDTO vehjkReqDTO) {
        return DataReuslt.success(vehicleMapper.getOne(vehjkReqDTO));
    }
}
