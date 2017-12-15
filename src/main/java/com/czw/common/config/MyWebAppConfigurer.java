package com.czw.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月7日
 * www.cuizhiwei.com
 */
@Configuration
@PropertySource(value = {"classpath:/common.properties"},encoding="utf-8") 
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter{
	
	@Value("${com.czw.upload.file}")  
	private String uploadpath;  //自定义上传目录
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations(uploadpath);
		super.addResourceHandlers(registry);
	}
	
}
