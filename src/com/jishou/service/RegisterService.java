package com.jishou.service;

public interface RegisterService {
    public boolean register(String account, String password);
    public boolean isExistAccount(String account);
}
