package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*替换httpServlet，根据请求的最后一段路径进行方法分发*/
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取请求路径
        String uri = req.getRequestURI();//uri是短的路径
//        获取最后一段路径，方法名
        int index=uri.lastIndexOf('/');
        String methodName = uri.substring(index+1);//此时得到selectAll
//        System.out.println(uri);
//        System.out.println(methodName);

//        执行方法
//        获取BrandServlet /UserServlet字节码对象 class
        Class<? extends BaseServlet> cls = this.getClass();
//        获取方法对象
        try {
            Method method = cls.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
