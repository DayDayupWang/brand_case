package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BrandService {
    /*查询所有*/
    List<Brand> selectAll();

    void add(Brand brand);

    /*
     * 单个删除*/
    void deleteById(int id);

    /*
     * 批量删除*/
    void deleteByIds(int[] ids);

    /*
    * currentPage:当前页码
    * pageSize:当前条数
    * */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize,Brand brand);

    /**
     * 修改
     *
     * @param brand
     */
    @Update("update tb_brand set brand_name = #{brandName},company_name = #{companyName},ordered = #{ordered},description = #{description},status = #{status} where id = #{id}")
    void update(Brand brand);
}
