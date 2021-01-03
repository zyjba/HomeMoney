package com.jishou.controller;

import com.jishou.entity.Expense;
import com.jishou.entity.Income;
import com.jishou.entity.User;
import com.jishou.service.MoneyService;
import com.jishou.service.impl.MoneyServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ShowServlet")
public class ShowServlet extends HttpServlet {
    private MoneyService MoneyService = new MoneyServiceImpl();
    /**
     * 显示收入或消费数据信息
     * //试图扩展分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //接收参数判断
        String type = req.getParameter("type");
        String pageStr = req.getParameter("page");
        Integer page = Integer.parseInt(pageStr);
        switch (type){
            case "income":
                //寻找收入数据
                List<Income> listIncome = MoneyService.findAllIncome(page);
                req.setAttribute("dataPrePage",8);
                req.setAttribute("currentPage",page);
                System.out.println(page);
                req.setAttribute("pages",MoneyService.getPages());
                req.setAttribute("listIncome",listIncome);
                req.getRequestDispatcher("/auser/Income/IncomeMana.jsp").forward(req,resp);
                break;
            case "expense":
                //寻找消费数据
                List<Expense> listExpense = MoneyService.findAllExpense();
                req.setAttribute("listExpense",listExpense);
                req.getRequestDispatcher("/auser/Expense/ExpenseMana.jsp").forward(req,resp);
                break;
            case "user":
                //寻找用户数据
                List<User> listUser = MoneyService.findAllUser();
                req.setAttribute("listUser",listUser);
                req.getRequestDispatcher("/admin/user/userMana.jsp").forward(req,resp);
                break;
        }
    }
}
