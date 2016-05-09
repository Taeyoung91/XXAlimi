package com.anonyblah.xxalimi.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import com.anonyblah.xxalimi.vo.User;

@Repository
public interface UserDao {
	@Insert("insert into users(username, password, name, cre_date, enabled)" + 
			"values (#{email}, #{password}, #{name}, now(), 1)")
	void insert(User user) throws Exception;

	int delete(int no) throws Exception;
}
