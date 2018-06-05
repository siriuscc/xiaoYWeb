package cc.siriuscloud.xiaoy.controller.ms;


import cc.siriuscloud.xiaoy.domain.Staff;
import cc.siriuscloud.xiaoy.service.StaffService;
import cc.siriuscloud.xiaoy.utils.JsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/ms")
public class IndexController {


    @Resource
    private StaffService staffService;

    @RequestMapping("loginUI")
    public String loginUI(){


        return "ms/login";
    }


    @ResponseBody
    @RequestMapping("login")
    public String login(Staff staff,HttpSession session){

        Staff loginStaff=staffService.login(staff);


        if(loginStaff!=null){

            session.setAttribute("staff",loginStaff);

            return JsUtil.redirectJs("登录成功","/ms/staff/myself.do");
        }else{

            return JsUtil.redirectJs("登录失败","/ms/loginUI.do");
        }

    }






}
