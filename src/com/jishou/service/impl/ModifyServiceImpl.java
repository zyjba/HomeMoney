package com.jishou.service.impl;

import com.jishou.repositiry.AdminRepository;
import com.jishou.repositiry.UserRepository;
import com.jishou.repositiry.impl.AdminRepositoryImpl;
import com.jishou.repositiry.impl.UserRepositoryImpl;
import com.jishou.service.ModifyService;

import java.util.Date;

public class ModifyServiceImpl implements ModifyService {

    private UserRepository userRepository=new UserRepositoryImpl();
  //  private AdminRepository adminRepository=new AdminRepositoryImpl();
    @Override
    public boolean modify(String password,String account) {
        boolean count =false;
        count=userRepository.modify(password,account);
        return count;
    }
}
