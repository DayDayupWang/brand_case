package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;
//Dao层
public interface BrandMapper {
    /*
     * 查询所有
     * 返回列表数据*/
    @Select("select * from tb_brand;")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /*
     *插入单条数据 */
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status});")
    void add(Brand brand);

    /*
     * 单个删除*/
    void deleteById(@Param("id") int id);

    /*
     * 批量删除*/
    void deleteByIds(@Param("ids") int[] ids);

    /*
     * 分页查询*/
    @Select("select * from tb_brand limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin, @Param(("size")) int size);

    /*返回总的数据量*/
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    /*
     * 分页条件查询*/

    List<Brand> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size,@Param("brand") Brand brand);

    /*返回符合条件的总数据量*/
    int selectTotalCountByCondition(Brand brand);

    /**
     * 修改项目
     *
     * @param brand
     */
    @Update("update tb_brand set brand_name = #{brandName},company_name = #{companyName},ordered = #{ordered},description = #{description},status = #{status} where id = #{id}")
    @ResultMap("brandResultMap")
    void update(Brand brand);
}
