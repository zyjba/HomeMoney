package com.jishou.controller;

import com.jishou.entity.Admin;
import com.jishou.entity.User;
import com.jishou.service.LoginService;
import com.jishou.service.impl.LoginServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * 处理登陆的业务逻辑
 */
@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();//多态
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //获取请求参数
        String account = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        String choose = req.getParameter("choose");
        //账号密码不能为空
            switch (choose){
                case "登录":
                    //通过参数查找人员赋给object
                    Object object=loginService.login(account,password,type);
                    if(object!=null){
                        //登陆成功
                        HttpSession session = req.getSession();
                        switch (type){
                            case "user":
                                User user = (User) object;
                                session.setAttribute("user",user);
                                //跳转到用户首页
                                resp.sendRedirect("/HomeMoney/auser/index.jsp");
                                break;
                            case "admin":
                                Admin admin = (Admin) object;
                                session.setAttribute("admin",admin);
                                //跳转到管理员首页
                                resp.sendRedirect("/HomeMoney/admin/index.jsp");
                                break;
                        }
                    }else {
                        //登陆失败
                        PrintWriter writer=resp.getWriter();
                        writer.write("<script>");
                        writer.write("alert('用户名或密码错误！');");
                        writer.write("window.location.href='login.jsp'");
                        writer.write("</script>");
                        writer.flush();
                        writer.close();
                    }
                    break;
                case "注册":
                    resp.sendRedirect("index.jsp");
                    break;
            }
    }
}
