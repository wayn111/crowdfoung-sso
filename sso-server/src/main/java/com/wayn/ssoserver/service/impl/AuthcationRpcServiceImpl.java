package com.wayn.ssoserver.service.impl;

import com.wayn.ssocore.entity.User;
import com.wayn.ssocore.service.AuthcationRpcService;
import com.wayn.ssoserver.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthcationRpcServiceImpl implements AuthcationRpcService {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean validate(String token) {
        return tokenManager.validateToken(token);
    }

    @Override
    public User findUserByToken(String token) {
        return tokenManager.getUserByToken(token);
    }

}
