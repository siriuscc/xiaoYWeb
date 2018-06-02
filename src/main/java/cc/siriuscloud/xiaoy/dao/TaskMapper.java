package cc.siriuscloud.xiaoy.dao;

import cc.siriuscloud.xiaoy.domain.Task;

import java.util.List;

public interface TaskMapper {
    int deleteByPrimaryKey(Integer taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKeyWithBLOBs(Task record);

    int updateByPrimaryKey(Task record);

    List<Task> findAllTaskByUserId(int userId);

    List<Task> findTodayTaskByUserId(int userId);

}