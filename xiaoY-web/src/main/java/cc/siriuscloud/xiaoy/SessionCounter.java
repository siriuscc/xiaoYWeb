package cc.siriuscloud.xiaoy;


import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;


public class SessionCounter implements HttpSessionListener {



    private static AtomicInteger sessionCount=new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent se) {


        synchronized (SessionCounter.class){
            sessionCount.incrementAndGet();
        }

        System.out.println("session create ...............");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        synchronized (SessionCounter.class){
            sessionCount.decrementAndGet();
        }

        System.out.println("session destroy ...............");

    }

    public static int getSessionCount(){
        return sessionCount.get();
    }

    /**
     * 通过WebApplicationContextUtils 得到Spring容器的实例。根据bean的名称返回bean的实例。
     * @param servletContext  ：ServletContext上下文。
     * @param beanName  :要取得的Spring容器中Bean的名称。
     * @return 返回Bean的实例。
     */
    private Object getObjectFromApplication(ServletContext servletContext, String beanName){
        //通过WebApplicationContextUtils 得到Spring容器的实例。
        ApplicationContext application=WebApplicationContextUtils.getWebApplicationContext(servletContext);
        //返回Bean的实例。
        return application.getBean(beanName);
    }


}
