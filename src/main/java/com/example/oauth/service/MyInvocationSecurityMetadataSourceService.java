package com.example.oauth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.example.mybatis.dao.PermissionDao;
import com.example.oauth.domain.Permission;

public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private PermissionDao permissionDao;
	
	private HashMap<String,Collection<ConfigAttribute>> map = null;
	
	public void loadResourceDefine(){
		map = new HashMap<String,Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> array;
		ConfigAttribute cfg;
		List<Permission> permissions = permissionDao.findAll();
		for(Permission permission:permissions){
			array = new ArrayList<ConfigAttribute>();
			cfg = new SecurityConfig(permission.getName());
			//此处只添加了用户的名字，其实海尔可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。
			//此处添加的信息将会作为MyAccessDecisionmanager类的decide的第三个参数
			array.add(cfg);
			//用权限的getUrl()作为map的key，用ConfigAttribute的集合作为value
			map.put(permission.getUrl(), array);
		}
	}
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if(map == null)loadResourceDefine();
		//object中包含用户请求的request信息
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

}
