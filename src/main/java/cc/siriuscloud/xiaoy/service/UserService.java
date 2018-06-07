package cc.siriuscloud.xiaoy.service;

import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.domain.vo.PageBean;

import java.util.List;

public interface UserService {



    User login(User user);


    int register(User user);

    List<User> findAllUser();

    PageBean<User> findUsersPerPage(PageBean<User> page);

    int removeUserById(int userId);
}
