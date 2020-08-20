package com.changgou.system.service;

import com.changgou.system.pojo.Admin;

public interface AdminService {
    public void add(Admin admin);
    boolean login(Admin admin);
}
