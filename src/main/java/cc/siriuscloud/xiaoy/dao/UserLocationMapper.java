package cc.siriuscloud.xiaoy.dao;

import cc.siriuscloud.xiaoy.domain.UserLocation;

import java.util.List;

public interface UserLocationMapper {
    int deleteByPrimaryKey(Integer locId);

    int insert(UserLocation record);

    int insertSelective(UserLocation record);

    UserLocation selectByPrimaryKey(Integer locId);

    int updateByPrimaryKeySelective(UserLocation record);

    int updateByPrimaryKey(UserLocation record);

    /**
     *
     * 查找最后的登录地点
     * @return
     */
    List<UserLocation> selectAllLast();
}