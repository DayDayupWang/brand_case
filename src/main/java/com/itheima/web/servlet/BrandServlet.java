package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> brands = brandService.selectAll();

        //转JSON
        String jsonString = JSON.toJSONString(brands);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收来自网页的数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        brandService.add(brand);
        response.getWriter().write("success");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理POST请求的乱码问题
        request.setCharacterEncoding("utf-8");
        //接收来自网页的数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        System.out.println(brand);

        //1. 接收表单提交的数据，封装为一个Brand对象
        Integer id = brand.getId();
        String brandName = brand.getBrandName();
        String companyName = brand.getCompanyName();
        Integer ordered = brand.getOrdered();
        String description = brand.getDescription();
        Integer status = brand.getStatus();

        //封装为一个Brand对象
        Brand b = new Brand();
        b.setId(id);
        b.setBrandName(brandName);
        b.setCompanyName(companyName);
        b.setOrdered(ordered);
        b.setDescription(description);
        b.setStatus(status);


        brandService.update(b);
        //在响应中写入成功
        response.getWriter().write("success");
    }


    /*
     * 单个删除*/
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收来自网页的数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转Brand对象
        int id = JSON.parseObject(params, int.class);
        brandService.deleteById(id);
        response.getWriter().write("success");
    }

    /*
     * 批量删除*/
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收来自网页的数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转Brand对象
        int[] ids = JSON.parseObject(params, int[].class);
        brandService.deleteByIds(ids);
        response.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收当前页码和每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //转JSON
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收当前页码和每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //接收来自网页的数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);


        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);

        //转JSON
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
