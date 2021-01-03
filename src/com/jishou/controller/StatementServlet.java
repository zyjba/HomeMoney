package com.jishou.controller;

import com.jishou.entity.Number;
import com.jishou.service.MoneyService;
import com.jishou.service.impl.MoneyServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/StatementServlet")
public class StatementServlet extends HttpServlet {
    private MoneyService MoneyService = new MoneyServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置格式
        resp.setCharacterEncoding("UTF-8");
        //获取时间参数time
        String dateStr = req.getParameter("time");
        String type = req.getParameter("type");
        String userIdStr = req.getParameter("userId");
        Integer userId = Integer.parseInt(userIdStr);
        //  Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        switch (type){
            case "income":
                //获取text
                Number income=MoneyService.findAllIncomeByMonth(dateStr,userId);
                //需要将java对象转为Json格式
                JSONObject jsonIncome = JSONObject.fromObject(income);
                resp.getWriter().write(jsonIncome.toString());
                break;
            case "expense":
                //获取text
                Number expense=MoneyService.findAllExpenseByMonth(dateStr,userId);
                //需要将java对象转为Json格式
                JSONObject jsonExpense = JSONObject.fromObject(expense);
                resp.getWriter().write(jsonExpense.toString());
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
