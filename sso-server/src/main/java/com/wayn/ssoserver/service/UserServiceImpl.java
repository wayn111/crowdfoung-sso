package com.wayn.ssoserver.service;

import com.wayn.ssocore.entity.User;
import com.wayn.ssocore.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        return user;
    }
}
