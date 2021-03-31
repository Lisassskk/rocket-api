package com.github.alenfive.rocketapi.extend;

import com.github.alenfive.rocketapi.entity.ApiInfo;
import com.github.alenfive.rocketapi.entity.ApiParams;

import java.util.List;

/**
 * 分页构造器接口
 */
public interface IApiPager {

    /**
     *
     * @param totalRecords 查询得到的总记录数
     * @param data 分页列表数据
     * @param apiInfo 该API信息
     * @param apiParams 参数信息
     */
    Object buildPager(Long totalRecords, List data, ApiInfo apiInfo, ApiParams apiParams);

    /**
     * 每页大小参数名
     */
    String getPageSizeVarName();

    /**
     * 第几页参数名
     */
    String getPageNoVarName();

    /**
     * skip变量名，由页码和每页大小计算获得,
     */
    String getIndexVarName();

    /**
     * 计算skip变量的值
     * @param pageSize
     * @param pageNo
     */
    Integer getIndexVarValue(Integer pageSize, Integer pageNo);

    /**
     * 默认每页大小
     */
    Integer getPageSizeDefaultValue();

    /**
     * 默认页码
     */
    Integer getPageNoDefaultValue();
}
