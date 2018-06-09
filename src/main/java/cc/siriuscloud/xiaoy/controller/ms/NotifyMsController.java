package cc.siriuscloud.xiaoy.controller.ms;


import cc.siriuscloud.xiaoy.MyHandlerExceptionResolver;
import cc.siriuscloud.xiaoy.domain.ExceptionDomain;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.LinkedList;

@Controller
@RequestMapping("ms/notify")
public class NotifyMsController {


    private static final int SIZE_KB =  1024;



    @RequestMapping("notifications")
    public String notifications(ModelMap map){


        LinkedList<ExceptionDomain> exceptionList =
                MyHandlerExceptionResolver.getExceptionList();

        map.addAttribute("exceptionList",exceptionList);

        return "ms/notify/notifications";
    }


    @ResponseBody
    @RequestMapping("getNotify")
    public void getNotify(HttpServletRequest request,HttpServletResponse response) throws IOException {

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

        FileInputStream inputStream=new FileInputStream(errorFile);

        PrintWriter writer = response.getWriter();
        readToString(errorFile,writer);

        writer.flush();
        writer.close();

    }

    @RequestMapping("exception")
    public String exception() throws NullPointerException {

        throw new NullPointerException();
    }

    private String readToString(File file, PrintWriter writer ) {
        String encoding = "UTF-8";

        Long filelength = file.length();
        byte[] cache = new byte[SIZE_KB];

        int len;

        try {
            FileInputStream in = new FileInputStream(file);

            while((len=in.read(cache))>0){

                //文件太大，无法载入到内存,换个方法，每次收到异常就记录到全局数据


                writer.print(new String(cache));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(cache, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

}
