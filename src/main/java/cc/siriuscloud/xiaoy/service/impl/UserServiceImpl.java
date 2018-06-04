package cc.siriuscloud.xiaoy.service.impl;

import cc.siriuscloud.xiaoy.dao.UserMapper;
import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;


    @Override
    public User login(User user) {

        return userMapper.selectByEmailPasswd(user);
    }

    @Override
    public int register(User user) {

        return userMapper.insert(user);
    }
}
