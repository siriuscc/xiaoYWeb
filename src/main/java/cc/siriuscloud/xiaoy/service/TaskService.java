package cc.siriuscloud.xiaoy.service;

import cc.siriuscloud.xiaoy.domain.Task;

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
}
