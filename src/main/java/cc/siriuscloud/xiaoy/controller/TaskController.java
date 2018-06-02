package cc.siriuscloud.xiaoy.controller;


import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.utils.Msg;
import cc.siriuscloud.xiaoy.service.TaskService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping("/task")
public class TaskController {

    public TaskController() {

        TimeZone.setDefault(TimeZone.getTimeZone("GMT-8"));

    }

    Logger logger = Logger.getLogger(TaskController.class);


    @Resource
    private TaskService taskService;


    @ResponseBody
    @RequestMapping("/addTask")
    public Msg addTask(Task task,String startTime) {

        logger.debug("startTime............" + startTime);

        logger.debug("............" + task);
        logger.debug("............" + task.getStartTime());


        //插入task
        Msg msg = new Msg();

        int count = taskService.addTask(task);

        if (count < 1) {

            msg.setStatus(Msg.STATUS_ERROR);
        }


        return msg;
    }

    @ResponseBody
    @RequestMapping("/removeTask")
    public Msg removeTask(int taskId) {


        int removeTaskById = taskService.removeTaskById(taskId);

        return new Msg(removeTaskById);
    }


    @ResponseBody
    @RequestMapping("/findTask")
    public Msg findTask(int taskId) {

        Task task = taskService.findTaskById(taskId);


        return (null == task) ?
                new Msg(Msg.MSG_ERROR, Msg.MSG_ERROR) :
                new Msg(Msg.STATUS_SUCCESS, Msg.MSG_SUCCESS, task);
    }


    @ResponseBody
    @RequestMapping("/updateTask")
    public Msg updateTask(Task task) {


        int count = taskService.updataTask(task);


        return (count < 1) ?
                new Msg(Msg.STATUS_ERROR) :
                new Msg(Msg.STATUS_SUCCESS);
    }


    @ResponseBody
    @RequestMapping("/findAllTasks")
    public Msg findAllTasks(int userId){

        List<Task> tasks = taskService.findAllTasks(userId);

        Msg msg = new Msg();

        if(null == tasks){
            msg.setStatus(Msg.STATUS_ERROR);
            msg.setMsg(Msg.MSG_ERROR);
        }

        msg.setData(tasks);
        return msg;
    }

    @ResponseBody
    @RequestMapping("/findTodayTasks")
    public Msg findTodayTasks(int userId){

        List<Task> tasks = taskService.findTodayTasks(userId);

        logger.debug(".........."+tasks);

        Msg msg = new Msg();

        if(null == tasks){
            msg.setStatus(Msg.STATUS_ERROR);
            msg.setMsg(Msg.MSG_ERROR);
        }

        msg.setData(tasks);
        return msg;
    }



}
