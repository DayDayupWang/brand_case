package com.itheima.web.servlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    public void selectAll() {
        System.out.println("user selectAll...");
    }
    public void add() {
        System.out.println("user add...");
    }
}
