package cc.siriuscloud.xiaoy.dao;

import cc.siriuscloud.xiaoy.domain.Staff;

public interface StaffMapper {
    int deleteByPrimaryKey(Integer staffId);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(Integer staffId);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);

    Staff selectByNamePasswd(Staff staff);
}