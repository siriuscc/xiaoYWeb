package cc.siriuscloud.xiaoy.controller.ms;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 升级
 */
@Controller
@RequestMapping("/ms/upgrade")
public class UpgradeMsController {



    @RequestMapping("/up")
    public String upgrade(){

        return "ms/upgrade/upgrade";
    }


}
