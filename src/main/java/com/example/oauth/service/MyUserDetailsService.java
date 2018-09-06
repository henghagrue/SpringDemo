package com.example.oauth.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.mybatis.dao.PermissionDao;
import com.example.mybatis.dao.UserDao;
import com.example.oauth.domain.Permission;
import com.example.oauth.domain.SysRole;
import com.example.oauth.domain.SysUser;


/**
 * 配置用户认证逻辑
 * 
 * @author upbchain12
 *
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	PermissionDao permissionDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("用户的用户名:" + username);
		/*****用户验证方式一***/
		SysUser user = userDao.findByUserName(username);
		if(user == null){
			throw new UsernameNotFoundException("用户名不存在");
		}
		/** 权限方式一 ****/
		List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for(Permission permission : permissions){
			if(permission != null && permission.getName() != null){
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
				//此处将权限信息添加到GrantedAuthority对象中，在后面进行全权限验证时会使用GrantedAuthority对象
				grantedAuthorities.add(grantedAuthority);
			}
		}
		return new User(user.getUsername(),user.getPassword(),grantedAuthorities);
		/***权限方式二***/
/*		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		//用于添加用户的权限，只要把用户权限添加到authorities 就可以
		for(SysRole role:user.getRoles()){
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			System.out.println(role.getName());
		}
		return new User(user.getUsername(),user.getPassword(),authorities);*/
		
		
		/****用户验证方式二***/
//		// TODO 根据用户名，查找到对应的密码，与权限
//		String password = passwordEncoder.encode("123456");
//		logger.info("password:" + password);
//		// 封装用户信息，并返回，参数分别是：用户名，密码，用户权限
//		User user = new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//		return user;
	}

}
