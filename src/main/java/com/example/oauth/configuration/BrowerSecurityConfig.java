package com.example.oauth.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.example.oauth.Interceptor.MyFilterSecurityInterceptor;
import com.example.oauth.handler.MyAuthenctiationFailureHandler;
import com.example.oauth.handler.MyAuthenctiationSuccessHandler;
import com.example.oauth.security.MyPasswordEncoder;
import com.example.oauth.service.MyUserDetailsService;
/**
 * 自定义用户认证逻辑
 * @author upbchain12
 *自定义自己的认证逻辑及登录界面
 */
@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {
	private final Log logger = LogFactory.getLog(getClass());

	
	@Autowired
	MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;
	
	@Autowired
	MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;
	
	@Bean
    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
        return new MyUserDetailsService();
    }
	 @Autowired
	 private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
/**
 * 重写页面请求
 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");

		http.formLogin()//定义当需要用户登录时候，转到的登录页面
		.loginPage("/uaa/login")//设置自定义的登录页面
		.loginProcessingUrl("/user/login")//自定义登录验证接口
		.successHandler(myAuthenctiationSuccessHandler)//自定义处理登陆成功
		.failureHandler(myAuthenctiationFailureHandler)//自定义处理登陆失败
			.and().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")//用于管控登录访问权限
				.and()
				.authorizeRequests()//定义哪些URL需要被保护、哪些不需要被保护
				.antMatchers("/**/login","/authentication/require").permitAll()  // 设置所有人都可以访问登录页面 ,需要对应登录页面的访问地址
				.anyRequest().authenticated()//任何请求，登陆后可以访问
				.and().logout().permitAll()//注销行为任意访问
				.and().csrf().disable();//关闭csrf防护
		http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		this.disableLocalConfigureAuthenticationBldr = true;
		auth.userDetailsService(customUserService());
	}
	
/**
 * 密码自定义加密
 * @return
 */
	@Bean
	public PasswordEncoder passwordEncoder() {
	     return new MyPasswordEncoder();
	 }
}
