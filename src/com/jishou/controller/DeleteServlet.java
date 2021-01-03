package com.jishou.controller;

import com.jishou.service.MoneyService;
import com.jishou.service.impl.MoneyServiceImpl;
import com.sun.org.apache.bcel.internal.generic.IXOR;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private MoneyService MoneyService = new MoneyServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method){
            case "deleteIncome":
                String incomeIdStr = req.getParameter("incomeId");
                Integer incomeId = Integer.parseInt(incomeIdStr);
                MoneyService.deleteIncome(incomeId);
                break;
            case "deleteExpense":
                String expenseIdStr = req.getParameter("expenseId");
                Integer expenseId = Integer.parseInt(expenseIdStr);
                MoneyService.deleteExpense(expenseId);
                break;
            case "deleteUser":
                String userIdStr = req.getParameter("userId");
                Integer userId = Integer.parseInt(userIdStr);
                MoneyService.deleteUser(userId);
                break;
        }
    }

}
