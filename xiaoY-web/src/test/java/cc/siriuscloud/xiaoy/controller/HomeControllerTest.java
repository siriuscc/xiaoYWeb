package cc.siriuscloud.xiaoy.controller;


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


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:spring-ssm.xml")
//@Controller
public class HomeControllerTest {


    @Resource
    private HomeController controller=new HomeController();



    public void testLogin() throws Exception{



        MockMvc mockMvc=standaloneSetup(controller).build();

        //发送get请求，期待home 视图
        mockMvc.perform(get("/login")).andExpect(status().isOk()).andReturn().equals("login");

    }




}
