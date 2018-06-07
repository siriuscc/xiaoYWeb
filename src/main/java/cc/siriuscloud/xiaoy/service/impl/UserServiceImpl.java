package cc.siriuscloud.xiaoy.service.impl;

import cc.siriuscloud.xiaoy.dao.UserMapper;
import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.domain.vo.PageBean;
import cc.siriuscloud.xiaoy.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


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

    @Override
    public List<User> findAllUser() {

        return userMapper.selectAllUser();
    }

    @Override
    public PageBean<User> findUsersPerPage(PageBean<User> page) {


        //先查条目数
        Long totalRecord = userMapper.selectCountAll();
        page.setTotalRecord(totalRecord);
        page.buildParams();

        PageHelper.startPage(page.getPageNum(), page.getPageSize());//1.添加插件api方法

        List<User> users = userMapper.selectAllUser();
        page.setData(users);

        return page;
    }

    @Override
    public int removeUserById(int userId) {

        return userMapper.deleteByPrimaryKey(userId);
    }
}
