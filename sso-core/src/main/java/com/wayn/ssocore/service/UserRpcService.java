
package com.wayn.ssocore.service;

import com.wayn.ssocore.entity.User;

public interface UserRpcService {

    User getUserByUserName(String admin);

    User getUser(User user);
}
