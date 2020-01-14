package com.wayn.ssocore.service;

import com.wayn.ssocore.entity.User;

public interface AuthcationRpcService {

    boolean validate(String token);

    User findUserByToken(String token);
}
