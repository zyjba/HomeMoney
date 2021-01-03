package com.jishou.service.impl;

import com.jishou.repositiry.AdminRepository;
import com.jishou.repositiry.UserRepository;
import com.jishou.repositiry.impl.AdminRepositoryImpl;
import com.jishou.repositiry.impl.UserRepositoryImpl;
import com.jishou.service.RegisterService;

import java.util.Date;

public class RegisterServiceImpl implements RegisterService {
    private UserRepository userRepository=new UserRepositoryImpl();

    /**
     * 通过type判断是否为用户注册，管理员唯一，无法注册。
     * @param account
     * @param password
     * @return
     */
    @Override
    public boolean register(String account, String password) {
        boolean count =false;
        count=userRepository.register(account,password,new Date());
        return count;
    }
    //判断账号存在性
    @Override
    public boolean isExistAccount(String account) {
        boolean count =false;
        count=userRepository.isExistAccount(account);
        return count;
    }
}
