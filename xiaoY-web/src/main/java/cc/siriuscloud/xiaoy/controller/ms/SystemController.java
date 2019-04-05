package cc.siriuscloud.xiaoy.controller.ms;


import cc.siriuscloud.xiaoy.MyHandlerExceptionResolver;
import cc.siriuscloud.xiaoy.SessionCounter;
import cc.siriuscloud.xiaoy.utils.SystemUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.lang.management.*;
import java.util.List;

@Controller
@RequestMapping("/ms/system")
public class SystemController {



    @RequestMapping("/dashboard")
    public String dashboard(ModelMap map, HttpSession session){

        List<GarbageCollectorMXBean> gcmBeanList = SystemUtil.getGCMBeanList();
        MemoryMXBean memoryMXBean = SystemUtil.getMemoryMXBean();
        OperatingSystemMXBean operatingSystemMXBean = SystemUtil.getOperatingSystemMXBean();
        RuntimeMXBean runtimeMXBean = SystemUtil.getRuntimeMXBean();
        SystemUtil.RuntimeBean runtimeBean = SystemUtil.getRuntimeBean();
        ThreadMXBean threadMXBean = SystemUtil.getThreadMXBean();

        List<MemoryPoolMXBean> memoryPoolMXBeans = SystemUtil.getMemoryPoolMXBeans();

        int sessionCount = SessionCounter.getSessionCount();


        map.addAttribute("sessionCount",sessionCount);

        map.addAttribute("gcmBeanList",gcmBeanList);
        map.addAttribute("memoryMXBean",memoryMXBean);
        map.addAttribute("operatingSystemMXBean",operatingSystemMXBean);
        map.addAttribute("runtimeMXBean",runtimeMXBean);
        map.addAttribute("runtimeBean",runtimeBean);
        map.addAttribute("threadMXBean",threadMXBean);
        map.addAttribute("memoryPoolMXBeans",memoryPoolMXBeans);

        session.setAttribute("exceptionList",MyHandlerExceptionResolver.getExceptionList());

        return "ms/sys/dashboard";
    }

}
