package com.czw;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
/**
 * 创建者:崔志伟
 * emai：cuizhiwei4930@sina.com
 * 日期：2017年11月27日
 */
//springBoot应用标识
@SpringBootApplication
public class Application {
	
	@Bean    
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){    
	    return hemf.getSessionFactory();    
	}    
	
	//程序启动入口
	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		InputStream in = Application.class.getResourceAsStream("/application.properties");
		properties.load(in);
		SpringApplication app = new SpringApplication(Application.class);
		app.setDefaultProperties(properties);
		app.run(args);
    }
}
