package cc.siriuscloud.xiaoy.service;

import cc.siriuscloud.xiaoy.domain.User;

public interface UserService {



    User login(User user);


    int register(User user);
}
