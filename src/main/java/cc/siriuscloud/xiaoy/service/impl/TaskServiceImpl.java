package cc.siriuscloud.xiaoy.service.impl;

import cc.siriuscloud.xiaoy.dao.TaskMapper;
import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService{


    @Resource
    TaskMapper taskMapper;



    @Override
    public int addTask(Task task) {

        return taskMapper.insert(task);

    }


    /**
     *
     * 删除task
     * @param taskId
     * @return
     */
    @Override
    public int removeTaskById(int taskId) {

        return taskMapper.deleteByPrimaryKey(taskId);
    }


    /**
     * findTaskId
     * @param taskId
     * @return
     */
    @Override
    public Task findTaskById(int taskId) {

        return taskMapper.selectByPrimaryKey(taskId);
    }


    @Override
    public int updataTask(Task task) {

        return taskMapper.updateByPrimaryKey(task);

    }

    @Override
    public List<Task> findAllTasks(int userId) {

        return taskMapper.findAllTaskByUserId(userId);
    }

    @Override
    public List<Task> findTodayTasks(int userId) {


        return taskMapper.findTodayTaskByUserId(userId);
    }
}
