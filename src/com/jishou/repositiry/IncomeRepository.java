package com.jishou.repositiry;

import com.jishou.entity.Income;

import java.util.List;

public interface IncomeRepository {
    public List<Income> findAll(int index,int limit);
    public int count();
    public List<Income> findAllByMonth(String time,Integer userId);
    public void deleteIncome(Integer incomeId);
    public void addIncome(Object... args);
}
