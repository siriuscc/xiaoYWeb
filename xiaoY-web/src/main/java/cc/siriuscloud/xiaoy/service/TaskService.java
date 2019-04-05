package cc.siriuscloud.xiaoy.service;

import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.domain.vo.PageBean;

import java.util.List;

public interface TaskService {


    int addTask(Task task);

    int removeTaskById(int taskId);

    Task findTaskById(int taskId);

    int updataTask(Task task);

    List<Task> findAllTasks(int userId);


    /**
     * 查找今天的任务列表
     * @param userId
     * @return
     */
    List<Task> findTodayTasks(int userId);


    /**
     * 分页查找任务
     * @param page
     * @param userId
     * @return
     */
    User findTaskPerPage(PageBean<Task> page, Integer userId);


}
