package com.jishou.repositiry;

import com.jishou.entity.Admin;
import com.jishou.entity.User;

public interface AdminRepository {
    public Admin login(String account, String password);
}
