package com.wayn.ssoserver.service.impl;

import com.wayn.ssocore.entity.User;
import com.wayn.ssocore.service.UserRpcService;
import com.wayn.ssocore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRpcServiceImpl implements UserRpcService {

    @Autowired
    private UserService userService;

    public User getUserByUserName(String username) {
        return userService.getUserByUserName(username);
    }

    public User getUser(User user) {
        return userService.getUser(user);
    }

}
