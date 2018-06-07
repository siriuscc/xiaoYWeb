package cc.siriuscloud.xiaoy.dao;

import cc.siriuscloud.xiaoy.domain.UserLocation;

public interface UserLocationMapper {
    int deleteByPrimaryKey(Integer locId);

    int insert(UserLocation record);

    int insertSelective(UserLocation record);

    UserLocation selectByPrimaryKey(Integer locId);

    int updateByPrimaryKeySelective(UserLocation record);

    int updateByPrimaryKey(UserLocation record);
}