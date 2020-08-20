package com.changgou.system.service.impl;

import com.changgou.system.dao.AdminMapper;
import com.changgou.system.pojo.Admin;
import com.changgou.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public void add(Admin admin) {
        String password = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
        admin.setPassword(password);
        adminMapper.insert(admin);
    }

    @Override
    public boolean login(Admin admin) {
        Admin admin1=new Admin();
        admin1.setLoginName(admin.getLoginName());
        admin1.setStatus("1");
        Admin admin2 = adminMapper.selectOne(admin1);//数据库查询出的对象
        if(admin2==null){
            return false;
        }else{
            //验证密码, Bcrypt为spring的包, 第一个参数为明文密码, 第二个参数 为密文密码
            return BCrypt.checkpw(admin.getPassword(),admin2.getPassword());
        }
    }
}
