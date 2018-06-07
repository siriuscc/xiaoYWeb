package cc.siriuscloud.xiaoy.controller.ms;


import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.domain.vo.PageBean;
import cc.siriuscloud.xiaoy.service.TaskService;
import cc.siriuscloud.xiaoy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/ms/user")
public class UserMsController {

    @Resource
    UserService userService;

    @Resource
    TaskService taskService;


    @RequestMapping("userlist")
    public String userlist(ModelMap model,Integer pageNum){


        //构造分页容器
        PageBean<User> page = new PageBean<User>();
        if(null!=pageNum){
            page.setPageNum(pageNum);
        }
        page.setPageSize(5);


        //获取分页数据
        page=userService.findUsersPerPage(page);

        //填入数据
        model.put("page",page);
//
//        List<User> users=userService.findAllUser();
//
//        model.addAttribute("users",users);

        return "ms/user/userlist";
    }



    @ResponseBody
    @RequestMapping("removeUser")
    public String removeUser(int userId){

        int i = userService.removeUserById(userId);

        return i+"";
    }


    @RequestMapping("tasklist")
    public String taskList(ModelMap map,Integer userId,Integer pageNum){

        //构造分页容器
        PageBean<Task> page = new PageBean<>();


        if(null!=pageNum){
            page.setPageNum(pageNum);
        }
        page.setPageSize(5);

        //TODO 没有测试呢
        //获取分页数据
        User user=taskService.findTaskPerPage(page,userId);

        map.put("user",user);
        map.put("page",page);


        return "ms/user/tasklist";
    }


    @ResponseBody
    @RequestMapping("removeTask")
    public String removeTask(int taskId){

        int i = taskService.removeTaskById(taskId);

        return i+"";
    }



}
