package cc.siriuscloud.xiaoy.dao;

import cc.siriuscloud.xiaoy.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    User selectByEmailPasswd(User user);
}