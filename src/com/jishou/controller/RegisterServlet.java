package com.jishou.controller;

import com.jishou.service.RegisterService;
import com.jishou.service.impl.RegisterServiceImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理用户注册的业务逻辑
 */
@WebServlet(urlPatterns ="/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private RegisterService registerService = new RegisterServiceImpl();//多态
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //获取请求参数
        String account = req.getParameter("username");
        String password = req.getParameter("password");
        //判断账号是否被注册
        boolean count=registerService.isExistAccount(account);
        if(count){
            //判断是否注册成功
            boolean flag=registerService.register(account,password);
            if(flag){
                //跳转到登录界面
                resp.sendRedirect("login.jsp");
            }else{
                //返回注册页面
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }
        }else {
            //数据库中已经有相同的用户
            //通过response对象给客户端一个错误提示
            PrintWriter writer=resp.getWriter();
            writer.write("<script>");
            writer.write("alert('申请注册的账号已被占用！');");
            writer.write("window.location.href='index.jsp'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }
    }
}
