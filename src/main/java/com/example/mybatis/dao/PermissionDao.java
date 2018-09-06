package com.example.mybatis.dao;

import java.util.List;

import com.example.oauth.domain.Permission;

public interface PermissionDao {
	public List<Permission> findAll();
	public List<Permission> findByAdminUserId(int userId);
}
