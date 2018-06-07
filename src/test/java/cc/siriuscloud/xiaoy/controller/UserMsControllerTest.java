package cc.siriuscloud.xiaoy.controller;



import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.domain.vo.PageBean;
import cc.siriuscloud.xiaoy.service.TaskService;
import cc.siriuscloud.xiaoy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-ssm.xml")
@Controller
public class UserMsControllerTest {


    @Resource
    UserService userService;

    @Resource
    TaskService taskService;

    @Test
    public void userlist(){


        Integer pageNum=null;

        //构造分页容器
        PageBean<User> page = new PageBean<User>();
        if(null!=pageNum){
            page.setPageNum(pageNum);
        }
        page.setPageSize(5);


        //获取分页数据
        page=userService.findUsersPerPage(page);


        System.out.println(page);


        for(User user:page.getData()){
            System.out.println(user);

        }

    }





    @Test
    public void tasklist() {

        Integer pageNum=null;
        Integer userId=2;

        //构造分页容器
        PageBean<Task> page = new PageBean<>();


        if(null!=pageNum){
            page.setPageNum(pageNum);
        }
        page.setPageSize(5);


        User user=taskService.findTaskPerPage(page,userId);


    }



}
