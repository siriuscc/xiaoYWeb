package cc.siriuscloud.xiaoy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {



    public HomeController() {

    }


    @RequestMapping("/home")
    @ResponseBody
    public String home() {

        return "home";
    }


}
