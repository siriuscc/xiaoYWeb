package cc.siriuscloud.xiaoy.dao;

import cc.siriuscloud.xiaoy.domain.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByEmailPasswd(User user);

    List<User> selectAllUser();

    Long selectCountAll();
}