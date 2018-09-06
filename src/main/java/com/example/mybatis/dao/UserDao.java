package com.example.mybatis.dao;

import com.example.oauth.domain.SysUser;

public interface UserDao {
	public SysUser findByUserName(String username);
}
