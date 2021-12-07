package com.egintra.common.utils;

import com.egintra.feeService.config.PageResult;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 数据分页工具类
 *
 * @author liushihao
 * @date 2021/7/29
 */
public class PageHelperUtils {

    /**
     * 获取分页数据
     *
     * @param dataList 数据集合
     * @param page     分页数据
     * @param <T>      泛型对象
     * @return 分页结果
     */
    public static <T> PageResult<T> getPagingData(List<T> dataList, Page page) {
        // 分页返回对象
        PageResult<T> result = new PageResult<>();
        // 分页
        result.setRows(dataList);
        result.setTotal(page.getTotal());
        result.setPages(page.getPages());
        result.setCurrentPage(0);

        // 返回结果
        return result;
    }
}
