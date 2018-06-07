package cc.siriuscloud.xiaoy.controller.ms;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ms/upgrade")
public class UpgradeController {



    @RequestMapping("upgrade")
    public String upgrade(){

        return "ms/upgrade/upgrade";
    }


}
