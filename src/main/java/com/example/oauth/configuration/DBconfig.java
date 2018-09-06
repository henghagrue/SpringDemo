package com.example.oauth.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

/**
 *  MyBatis 配置 -- 配置数据源
 *  Druid : DruidDataSource
 * @author upbchain12
 *
 */
@Configuration
public class DBconfig {
	@Autowired
	private Environment env;
	
	@Bean(name="dataSource")
	public DataSource dataSource(){
//		Properties properties = new Properties();
//        DruidDataSource druidDataSource = new DruidDataSource();
//        PropertySource<?> appProperties = env.getPropertySources().get("applicationConfig: [classpath:/application.yml]");
//        Map<String,Object>  source = (Map<String, Object>) appProperties.getSource();
//        properties.putAll(source);
//        druidDataSource.configFromPropety(properties);
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        druidDataSource.setUrl(env.getProperty("spring.datasource.url"));
        druidDataSource.setPassword(env.getProperty("spring.datasource.password"));
        druidDataSource.setUsername(env.getProperty("spring.datasource.username"));
        druidDataSource.setDbType("MYSQL");
	    return druidDataSource;
		
	}
	
	
}
