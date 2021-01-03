package com.jishou.service;

import com.jishou.entity.Expense;
import com.jishou.entity.Income;
import com.jishou.entity.User;
import com.jishou.entity.Number;

import java.util.List;

public interface MoneyService {
    public List<Income> findAllIncome(int page);
    public int getPages();
    public Number findAllIncomeByMonth(String time, Integer userId);
    public Number findAllExpenseByMonth(String time, Integer userId);
    public List<Expense> findAllExpense();
    public List<User> findAllUser();
    public void deleteIncome(Integer incomeId);
    public void deleteExpense(Integer expenseId);
    public void deleteUser(Integer userId);
    public void addIncome(String number,String member,String type,String remark,Integer userId);
    public void addExpense(String number,String member,String type,String remark,Integer userId);
}

