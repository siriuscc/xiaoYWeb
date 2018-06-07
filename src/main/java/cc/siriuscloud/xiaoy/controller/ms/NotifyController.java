package cc.siriuscloud.xiaoy.controller.ms;


import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("ms/notify")
public class NotifyController {


    @ResponseBody
    @RequestMapping("notify")
    public String notify(HttpServletRequest request) {


        Logger logger=Logger.getLogger(this.getClass());


        String basePath = request.getServletContext().getRealPath("/");

        File file=new File(basePath);


        File parentFile = file.getParentFile().getParentFile().getParentFile();


        logger.debug(parentFile.getName());

        File errorFile=null;
        for(File f:parentFile.listFiles()){

            logger.debug("\t"+f.getName());
            if(f.getName().equals("error.html")){
                logger.debug("找到了");
                errorFile=f;
            }
        }

        if(errorFile!=null){
            logger.debug("错误文件："+errorFile.getName());
        }else{
            logger.debug("错误文件：找不到");

        }


        return basePath;
    }


}
