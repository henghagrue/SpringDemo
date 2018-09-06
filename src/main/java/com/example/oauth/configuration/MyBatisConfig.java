package com.example.oauth.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * 配置扫描mapper.xml文件
 */
@Configuration
@ComponentScan
public class MyBatisConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactory(ApplicationContext applicationContext) throws IOException{
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		//sessionFactory.setPlugins(new Interceptor[]{new PageInterceptor()});
		sessionFactory.setMapperLocations(applicationContext.getResources("classpath*:mapper/*.xml"));
		return sessionFactory;
	}
}
