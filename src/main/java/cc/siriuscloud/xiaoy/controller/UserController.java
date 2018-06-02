package cc.siriuscloud.xiaoy.controller;

import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.utils.Msg;
import cc.siriuscloud.xiaoy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @RequestMapping("login")
    @ResponseBody
    public Object login(User user, HttpSession session) {

        User loginUser = userService.login(user);
        Msg msg = new Msg();

        if (null != loginUser) {

            session.setAttribute("user", loginUser);
            msg.setMsg("login");

            msg.setData(loginUser);

        } else {
            msg.setStatus(Msg.STATUS_ERROR);   //失败
            msg.setMsg("nologin");
        }

        return msg;
    }


}
