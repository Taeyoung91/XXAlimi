package com.anonyblah.xxalimi.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.anonyblah.xxalimi.vo.Users;

@Repository
public interface UserDao {
	
	
	@Results({
		@Result(property = "email", column = "username")
	})
	@Select("select * from users "
			+ "where username = #{email}")
	Users findByEmail(String email) throws Exception;
	
	@Insert("insert into users(username, password, name, role, cre_date, enabled)" + 
			"values (#{email}, #{password}, #{name}, #{role}, now(), 1)")
	void insert(Users user) throws Exception;

	int delete(int no) throws Exception;
}
