package com.jishou.service.impl;

import com.jishou.repositiry.AdminRepository;
import com.jishou.repositiry.UserRepository;
import com.jishou.repositiry.impl.AdminRepositoryImpl;
import com.jishou.repositiry.impl.UserRepositoryImpl;
import com.jishou.service.LoginService;

public class LoginServiceImpl implements LoginService {

    private UserRepository userRepository=new UserRepositoryImpl();
    private AdminRepository adminRepository=new AdminRepositoryImpl();
    @Override
    public Object login(String account, String password , String type) {
        Object object =null;
        switch (type){
            case "user":
                   object=userRepository.login(account,password);
                break;
            case "admin":
                    object =adminRepository.login(account,password);
                break;
        }
        return object;
    }
}
