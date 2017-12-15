package com.czw.common.handler;

import java.io.EOFException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.czw.common.tools.Tools;

/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月13日
 * www.cuizhiwei.com
 */
public class CallBackHandler implements HandlerInterceptor{
	
	private static Logger logger = Logger.getLogger(CallBackHandler.class);

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex)
			throws Exception {
		if(ex!=null && ex.getClass()!=null){
			infoLog(req, "error",ex.getClass().getName());
		}else{
			infoLog(req, "ok");
		}
			
		if(ex!=null){
			if(!(ex instanceof EOFException))
				errorLog(req, ex, "error");
		} 
		
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView mv)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		req.setAttribute("_req_start_time",System.currentTimeMillis());
		String remoteAddr = Tools.getRemoteAddr(req);
		//判断ip是否是可信任ip
		/*if(!remoteAddr.startsWith("10.") && !remoteAddr.equals("127.0.0.1")){
			return false;				
		}*/
		return true;
	}
	
	public void infoLog(HttpServletRequest request, Object... msg)
	{
		logger.info(Tools.logString(Tools.logHead(request), msg));
	}

	public void debugLog(HttpServletRequest request, Object... msg)
	{
		logger.debug(Tools.logString(Tools.logHead(request), msg));
	}

	public void errorLog(HttpServletRequest request, Throwable e, Object... msg)
	{
		logger.error(Tools.logString(Tools.logHead(request), msg), e);
	}
	
}
