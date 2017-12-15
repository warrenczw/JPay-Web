package com.czw.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.czw.common.handler.ApiHandler;

/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月13日
 * www.cuizhiwei.com
 */
@Configuration
public class PayWebConfigurer extends WebMvcConfigurerAdapter{

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//拦截器适配
		registry.addInterceptor(new ApiHandler()).addPathPatterns("/api/**");
		registry.addInterceptor(new ApiHandler()).addPathPatterns("/cb/**");
		super.addInterceptors(registry);
	}
}
