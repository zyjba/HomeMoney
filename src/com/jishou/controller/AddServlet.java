package com.jishou.controller;

import com.jishou.entity.User;
import com.jishou.service.MoneyService;
import com.jishou.service.impl.MoneyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/AddServlet")
public class AddServlet extends HttpServlet {
    private MoneyService MoneyService = new MoneyServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //获取参数
        String IncomeOrExpense = req.getParameter("IncomeOrExpense");
        String number = req.getParameter("number");
        String member = req.getParameter("member");
        String type = req.getParameter("type");
        String remark = req.getParameter("remark");
        String userIdStr = req.getParameter("userId");
        Integer userId = Integer.parseInt(userIdStr);
        switch (IncomeOrExpense){
            case "income":
                MoneyService.addIncome(number,member,type,remark,userId);
                break;
            case "expense":
                MoneyService.addExpense(number,member,type,remark,userId);
                break;
        }
    }
}
