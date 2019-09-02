package cn.Interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SystemInterceptor extends HandlerInterceptorAdapter {
	
	private List<String> excludedUrls;  
	
	public void setExcludedUrls(List<String> excludedUrls) {  
	    this.excludedUrls = excludedUrls;  
	}  	
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();    
        
        for (String url : excludedUrls) {  
        	if (requestUri.endsWith(url)) {  
        		return true;  
        	}  
        }          
        		
        /*判断是否需要过滤的页面*/
        HttpSession session = request.getSession();  
        
        if(session.getAttribute("devUser") == null){
        	/*跳转到登录页*/
        	request.getRequestDispatcher("/WEB-INF/jsp/403.jsp").forward(request, response);
        	return false;
        }
        return true;
	}
 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
	}
 
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}		
}
