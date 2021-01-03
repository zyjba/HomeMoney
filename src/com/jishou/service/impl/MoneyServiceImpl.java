package com.jishou.service.impl;

import com.jishou.entity.Expense;
import com.jishou.entity.Income;
import com.jishou.entity.User;
import com.jishou.entity.Number;
import com.jishou.repositiry.ExpenseRepository;
import com.jishou.repositiry.IncomeRepository;
import com.jishou.repositiry.UserRepository;
import com.jishou.repositiry.impl.ExpenseRepositoryImpl;
import com.jishou.repositiry.impl.IncomeRepositoryImpl;
import com.jishou.repositiry.impl.UserRepositoryImpl;
import com.jishou.service.MoneyService;

import java.util.Date;
import java.util.List;

public class MoneyServiceImpl implements MoneyService {
    private IncomeRepository incomeRepository = new IncomeRepositoryImpl();
    private ExpenseRepository expenseRepository = new ExpenseRepositoryImpl();
    private UserRepository userRepository = new UserRepositoryImpl();
    private final int LIMIT =8;
    @Override
    public List<Income> findAllIncome(int page) {
        int index = (page-1)*LIMIT;
        return incomeRepository.findAll(index,LIMIT);
    }

    @Override
    public int getPages() {
        int count =incomeRepository.count();
        int page =0;
        if(count%LIMIT==0){
            page=count%LIMIT;
        }else {
            page=count%LIMIT+1;
        }
        return page;
    }

    @Override
    public Number findAllIncomeByMonth(String time, Integer userId) {
        //根据参数time和当前用户ID寻找income表 where income.time=time&userId=income.userId
        List<Income> listIncome =incomeRepository.findAllByMonth(time,userId);
        Double myMoney=0.00;
        Double mateMoney=0.00;
        Double faMoney=0.00;
        Double maMoney=0.00;
        Double childMoney=0.00;
        //遍历List集合
        if (listIncome != null && listIncome.size() > 0) {
            for (int i = 0; i < listIncome.size(); i++) {
                Income income= listIncome.get(i);
                if(income.getMember().equals("本人")){
                    myMoney += income.getNumber();
                }else if(income.getMember().equals("配偶")){
                    mateMoney += income.getNumber();
                }else if(income.getMember().equals("爸爸")){
                    faMoney += income.getNumber();
                }else if(income.getMember().equals("妈妈")){
                    maMoney += income.getNumber();
                }else if(income.getMember().equals("子女")){
                    childMoney += income.getNumber();
                }
            }
        }
        Number text = new Number(myMoney,mateMoney,faMoney,maMoney,childMoney);
        return text;
    }

    @Override
    public Number findAllExpenseByMonth(String time, Integer userId) {
        //根据参数time和当前用户ID寻找income表 where income.time=time&userId=income.userId
        List<Expense> listExpense =expenseRepository.findAllByMonth(time,userId);
        Double myMoney=0.00;
        Double mateMoney=0.00;
        Double faMoney=0.00;
        Double maMoney=0.00;
        Double childMoney=0.00;
        //遍历List集合
        if (listExpense != null && listExpense.size() > 0) {
            for (int i = 0; i < listExpense.size(); i++) {
                Expense expense= listExpense.get(i);
                if(expense.getMember().equals("本人")){
                    myMoney += expense.getNumber();
                }else if(expense.getMember().equals("配偶")){
                    mateMoney += expense.getNumber();
                }else if(expense.getMember().equals("爸爸")){
                    faMoney += expense.getNumber();
                }else if(expense.getMember().equals("妈妈")){
                    maMoney += expense.getNumber();
                }else if(expense.getMember().equals("子女")){
                    childMoney += expense.getNumber();
                }
            }
        }
        Number text = new Number(myMoney,mateMoney,faMoney,maMoney,childMoney);
        return text;
    }

    @Override
    public List<Expense> findAllExpense() {
        return expenseRepository.findAll();
    }

    @Override
    public List<User> findAllUser() {
        return  userRepository.findAll();
    }

    @Override
    public void deleteIncome(Integer incomeId) {
        incomeRepository.deleteIncome(incomeId);
    }
    @Override
    public void deleteExpense(Integer expenseId) {
        expenseRepository.deleteExpense(expenseId);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteUser(userId);
    }

    @Override
    public void addIncome(String number, String member, String type, String remark, Integer userId) {
        incomeRepository.addIncome(new Date(),number, member, type, remark, userId);
    }
    @Override
    public void addExpense(String number, String member, String type, String remark, Integer userId) {
        expenseRepository.addExpense(new Date(),number, member, type, remark, userId);
    }
}
