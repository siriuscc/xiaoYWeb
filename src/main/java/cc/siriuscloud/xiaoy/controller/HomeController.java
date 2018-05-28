package cc.siriuscloud.xiaoy.controller;



import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {


    @Autowired
    private UserService userService;


    public HomeController() {

    }


    @RequestMapping("/home")
    @ResponseBody
    public String home(){

        return "home";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(User user, HttpSession session){


        User loginUser=userService.login(user);

        if(null!=loginUser){

            session.setAttribute("user",loginUser);
            return "login";


        }

        return "loginerror";
    }

}
