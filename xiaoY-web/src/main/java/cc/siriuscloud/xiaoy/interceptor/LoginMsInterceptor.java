package cc.siriuscloud.xiaoy.interceptor;

import cc.siriuscloud.xiaoy.domain.Staff;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class LoginMsInterceptor implements HandlerInterceptor {

    public static String TAG_MS_LOGIN="loginStaff";



	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {


        String servletPath = request.getServletPath();


        Logger logger=Logger.getLogger(this.getClass());

        logger.debug("..............servlet path:"+servletPath);


        //放行后台请求
        if(!servletPath.startsWith("/ms")){

            return true;
        };


		//验证session域
		Object loginTag = request.getSession().getAttribute(TAG_MS_LOGIN);
		if(loginTag!=null){
			
			return true;
		}


		response.sendRedirect("/ms/login.do");
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		
	}

	
	
	
	
}
