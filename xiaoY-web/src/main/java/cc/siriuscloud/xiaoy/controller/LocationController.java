package cc.siriuscloud.xiaoy.controller;


import cc.siriuscloud.xiaoy.domain.UserLocation;
import cc.siriuscloud.xiaoy.service.UserLocationService;
import cc.siriuscloud.xiaoy.utils.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller

@RequestMapping("/location")
public class LocationController {


    @Resource
    UserLocationService userLocationService;

    @ResponseBody
    @RequestMapping("commitLocation")
    public Message commitLocation(UserLocation userLocation){

        int add=userLocationService.addLocation(userLocation);

        if(add!=0){

            return new Message<>(Message.STATUS_SUCCESS, Message.MSG_SUCCESS);
        }

        return new Message<>(Message.STATUS_ERROR, Message.MSG_ERROR);
    }


}
