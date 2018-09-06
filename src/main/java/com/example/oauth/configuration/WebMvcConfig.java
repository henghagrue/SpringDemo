package com.example.oauth.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * SpringMVC 配置，注册访问/login转向login.html页面
 * @author upbchain12
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/login").setViewName("login");
	}
}
