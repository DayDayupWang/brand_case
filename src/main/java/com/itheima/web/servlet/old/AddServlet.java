package com.itheima.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收来自网页的数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        brandService.add(brand);
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
