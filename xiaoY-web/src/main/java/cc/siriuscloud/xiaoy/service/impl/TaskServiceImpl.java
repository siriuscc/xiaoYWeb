package cc.siriuscloud.xiaoy.service.impl;

import cc.siriuscloud.xiaoy.dao.TaskMapper;
import cc.siriuscloud.xiaoy.dao.UserMapper;
import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.domain.vo.PageBean;
import cc.siriuscloud.xiaoy.service.TaskService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService{


    @Resource
    TaskMapper taskMapper;

    @Resource
    UserMapper userMapper;



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

        return taskMapper.selectAllTaskByUserId(userId);
    }

    @Override
    public List<Task> findTodayTasks(int userId) {


        return taskMapper.selectTodayTaskByUserId(userId);
    }



    @Override
    public User findTaskPerPage(PageBean<Task> page, Integer userId) {


        User user = userMapper.selectByPrimaryKey(userId);


        if(user==null){
            return null;
        }

        //先查条目数
        Long totalRecord = taskMapper.selectCountByUserId(userId);
        page.setTotalRecord(totalRecord);
        page.buildParams();

        PageHelper.startPage(page.getPageNum(), page.getPageSize());//1.添加插件api方法

        List<Task> tasks = taskMapper.selectAllTaskByUserId(userId);


        page.setData(tasks);

        return user;
    }
}
