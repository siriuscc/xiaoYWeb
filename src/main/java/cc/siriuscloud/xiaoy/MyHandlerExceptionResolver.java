package cc.siriuscloud.xiaoy;

import cc.siriuscloud.xiaoy.domain.ExceptionDomain;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;


@Component
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {



    private static int MAXSIZE_EXCEPTION=20;

    public static LinkedList<ExceptionDomain> getExceptionList() {
        return exceptionList;
    }

    private static LinkedList<ExceptionDomain> exceptionList=new LinkedList<>();



    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {


        String exMessage = ex.getMessage();

        String name = ex.getClass().getName();

        String causeMsg= null;

        if(ex.getCause()!=null){

            causeMsg=ex.getCause().getMessage();
        }

        Logger logger=Logger.getLogger(this.getClass());


        ExceptionDomain exceptionDomain = new ExceptionDomain(name, exMessage, causeMsg);

        while(exceptionList.size()>MAXSIZE_EXCEPTION){

            exceptionList.pop();
        }

        exceptionList.addLast(exceptionDomain);

        logger.debug("exMessage:.................."+exMessage);
        logger.debug("name:.................."+name);


        return new ModelAndView("/404");
    }





}
