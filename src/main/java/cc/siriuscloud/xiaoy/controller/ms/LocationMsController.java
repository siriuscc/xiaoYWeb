package cc.siriuscloud.xiaoy.controller.ms;


import cc.siriuscloud.xiaoy.domain.UserLocation;
import cc.siriuscloud.xiaoy.domain.vo.UserLocationVo;
import cc.siriuscloud.xiaoy.service.UserLocationService;
import cc.siriuscloud.xiaoy.utils.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/ms/location")
public class LocationMsController {


    @Resource
    UserLocationService userLocationService;


    @RequestMapping("getLocation")
    @ResponseBody
    public List<UserLocation> getLocation(){

        //查询所有定位

        List<UserLocation> userLocations=userLocationService.findAllLast();


        return userLocations;
    }

    @RequestMapping("maps")
    public String maps(){


        return "ms/location/maps";
    }


    @RequestMapping("getLastAllLocation")
    @ResponseBody
    public List<UserLocationVo> getLastAllLocation(){

        //查询所有定位

        List<UserLocationVo> userLocations=userLocationService.findLastAllUserLocation();


        return userLocations;
    }



}
