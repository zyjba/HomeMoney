package com.jishou.controller;

import com.jishou.service.ModifyService;
import com.jishou.service.impl.ModifyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/ModifyServlet")
public class ModifyServlet extends HttpServlet {
    private ModifyService modifyService= new ModifyServiceImpl();

    /**
     * 修改用户密码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //获取请求参数
        String account = req.getParameter("userName");
        String password = req.getParameter("userPw1");
        //判断是否修改成功
        boolean flag=modifyService.modify(password,account);
        if(flag){
            PrintWriter writer=resp.getWriter();
            writer.write("<script>");
            writer.write("alert('密码修改成功！');");
            writer.write("window.location.href='userPw.jsp'");
            writer.write("<script>");
        }else{
            PrintWriter writer=resp.getWriter();
            writer.write("<script>");
            writer.write("alert('密码修改失败！');");
            writer.write("window.location.href='userPw.jsp'");
            writer.write("<script>");
        }
    }
}
