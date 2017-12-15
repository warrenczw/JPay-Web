package com.czw.common.tools;

import javax.servlet.http.HttpServletRequest;

public class Tools extends JavaTools{

	public static String logHead(HttpServletRequest request)
	{
		long stime=Tools.toLong(request.getAttribute("_req_start_time"),System.currentTimeMillis());
		return Tools.logString(System.currentTimeMillis() - stime
				, request.getMethod()
				, request.getServerName()
				, getRemoteAddr(request)
		        , Tools.removeAll(request.getRequestURI(), "|")
		        , Tools.removeAll(request.getQueryString(), "|")
		         , Tools.objectToJsonString(request.getParameterMap())
		        );
	}
	
	/**
	 * 获取域名ip
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		if(Tools.isIpString(request.getHeader("X-Forwarded-For")))
			return request.getHeader("X-Forwarded-For").trim();
		return request.getRemoteAddr();
	}
}
