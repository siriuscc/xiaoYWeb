package cc.siriuscloud.xiaoy.controller;


import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.utils.Message;
import cc.siriuscloud.xiaoy.utils.Msg;
import cc.siriuscloud.xiaoy.service.TaskService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/task")
public class TaskController {

    public TaskController() {


    }

    Logger logger = Logger.getLogger(TaskController.class);


    @Resource
    private TaskService taskService;


    @ResponseBody
    @RequestMapping("/addTask")
    public Message<Task> addTask(Task task, String startTime) {

        //这里多了八个小时
        logger.debug("。。。。。。。。。。。task:"+task);
        logger.debug("。。。。。。。。。。。。。startTime:"+startTime);


        //插入task
        Message<Task> msg = new Message<>();

        int count = taskService.addTask(task);

        if (count < 1) {

            msg.setStatus(Message.STATUS_ERROR);
        }


        return msg;
    }

    @ResponseBody
    @RequestMapping("/removeTask")
    public Message removeTask(int taskId) {

        int removeTaskById = taskService.removeTaskById(taskId);

        if(removeTaskById>0){
            return new Message(0);
        }

        return new Message(removeTaskById);
    }


    @ResponseBody
    @RequestMapping("/findTask")
    public Message<Task> findTask(int taskId) {

        Task task = taskService.findTaskById(taskId);

        return (null == task) ?
                new Message<Task>(Message.STATUS_ERROR, Message.MSG_ERROR) :
                new Message<Task>(Message.STATUS_SUCCESS, Message.MSG_SUCCESS, task);
    }


    @ResponseBody
    @RequestMapping("/updateTask")
    public Message<Task> updateTask(Task task) {

        int count = taskService.updataTask(task);


        return (count < 1) ?
                new Message<Task>(Message.STATUS_ERROR) :
                new Message<Task>(Message.STATUS_SUCCESS);
    }


    @ResponseBody
    @RequestMapping("/findAllTasks")
    public Message<Task> findAllTasks(int userId){

        List<Task> tasks = taskService.findAllTasks(userId);

        Message<Task> msg = new Message<>();

        if(null == tasks){
            msg.setStatus(Message.STATUS_ERROR);
            msg.setMsg(Message.MSG_ERROR);
        }

        msg.setData(tasks);



        return msg;
    }

    @ResponseBody
    @RequestMapping("/findTodayTasks")
    public Message<Task> findTodayTasks(int userId){

        List<Task> tasks = taskService.findTodayTasks(userId);

//        logger.debug(".........."+tasks);

        Message<Task> msg = new Message<>();

        if(null == tasks){
            msg.setStatus(Message.STATUS_ERROR);
            msg.setMsg(Message.MSG_ERROR);
        }

        msg.setData(tasks);

        //数据库的时间是正确的，那么回来后会导致数据是错的？打印一下看看
        logger.debug("...............tasks:"+tasks);

        return msg;
    }



}
