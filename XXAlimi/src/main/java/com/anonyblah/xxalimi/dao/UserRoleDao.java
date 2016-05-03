package com.anonyblah.xxalimi.dao;

import org.apache.ibatis.annotations.Insert;

import com.anonyblah.xxalimi.vo.UserRole;

public interface UserRoleDao {
	@Insert("insert into authorities (username, authority)" + 
			"values (#{email}, 'ROLE_USER')")
	void insert(UserRole userRole) throws Exception;
}
