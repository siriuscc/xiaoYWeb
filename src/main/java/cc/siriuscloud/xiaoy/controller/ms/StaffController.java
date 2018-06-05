package cc.siriuscloud.xiaoy.controller.ms;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ms/staff")
public class StaffController {




    @RequestMapping("myself")
    public String myself(){




        return "ms/staff/myself";

    }






}
