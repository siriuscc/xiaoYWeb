package cc.siriuscloud.xiaoy.controller;

import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.utils.Message;
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
    public Message<User> login(User user, HttpSession session) {

        User loginUser = userService.login(user);
        Message<User> msg = new Message<>();

        if (null != loginUser) {

            session.setAttribute("user", loginUser);
            msg.setMsg("login");

            msg.setItem(loginUser);

        } else {
            msg.setStatus(Message.STATUS_ERROR);   //失败
            msg.setMsg("nologin");
        }

        return msg;
    }


    @RequestMapping("register")
    @ResponseBody
    public Message<User> register(User user) {

        int register = userService.register(user);
        Message<User> msg = new Message<>();

        if (0 < register) {

            msg.setMsg("register success");

        } else {
            msg.setStatus(Message.STATUS_ERROR);   //失败
            msg.setMsg("register error");
        }

        return msg;
    }


}
