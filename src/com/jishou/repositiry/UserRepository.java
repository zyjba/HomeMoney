package com.jishou.repositiry;

import com.jishou.entity.Income;
import com.jishou.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    public List<User> findAll();
    public User login(String account, String password);
    public boolean register(Object... args);
    public boolean isExistAccount(String account);
    public boolean modify(String password,String account);
    public void deleteUser(Integer userId);
}
