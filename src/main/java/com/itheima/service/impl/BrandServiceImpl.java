package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    //    创建sqlsessionfactory对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Brand> selectAll() {
        //获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        List<Brand> brands = mapper.selectAll();
        //释放资源
        sqlSession.close();

        return brands;
    }

    @Override
    public void add(Brand brand) {
        //获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.add(brand);
        sqlSession.commit();
        sqlSession.close();

    }

    @Override
    public void deleteById(int id) {
        //获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.deleteById(id);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.deleteByIds(ids);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //计算
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        List<Brand> rowBrands = mapper.selectByPage(begin, size);

        //查询总记录数
        int totalCount = mapper.selectTotalCount();

        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rowBrands);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();
        return pageBean;


    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //计算
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        //处理条件，模糊表达式
        String brandName = brand.getBrandName();
        if (brandName!=null&&brandName.length()>0){
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName = brand.getCompanyName();
        if (companyName!=null&&companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }
        List<Brand> rowBrands = mapper.selectByPageAndCondition(begin, size,brand);

        //传入条件brand,查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);

        //封装pageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rowBrands);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();
        return pageBean;
    }

    @Override
    public void update(Brand brand) {
        //获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.update(brand);
        sqlSession.commit();
        sqlSession.close();
    }


}
