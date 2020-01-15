package com.wayn.ssoserver.service.impl;

import com.wayn.ssocore.entity.User;
import com.wayn.ssocore.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        return user;
    }

    @Override
    public User getUserByUserName(String username) {
        User user = new User();
        user.setUserName(username);
        user.setPassword("123456");
        return user;
    }
}
