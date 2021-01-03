package com.jishou.repositiry;

import com.jishou.entity.Expense;
import com.jishou.entity.Income;

import java.util.List;

public interface ExpenseRepository {
    public List<Expense> findAll();
    public List<Expense> findAllByMonth(String time, Integer userId);
    public void deleteExpense(Integer expenseId);
    public void addExpense(Object... args);
}
