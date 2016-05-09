package com.anonyblah.xxalimi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.anonyblah.xxalimi.vo.Keywords;


@Repository
public interface KeywordDao {

	@Results({
		@Result(property = "email", column = "username"),
		@Result(property = "title", column = "feedtitle")
	})
	@Select("select feedtitle, username, keyword from keywords "
			+ "where username = #{email}")
	List<Keywords> findTitleAndKeywordByEmail(String email) throws Exception;
	
	@Results({
		@Result(property = "email", column = "username"),
		@Result(property = "title", column = "feedtitle")
	})
	@Select("select keyword from keywords "
			+ "where username = #{email} "
			+ "and feedlink = #{feedLink}")
	List<Keywords> findKeywordByEmailAndFeedLink(@Param("email") String email, @Param("feedLink") String feedLink) throws Exception;
	
	@Insert("insert into keywords (username, feedtitle, feedlink, keyword, cre_date) "
			+ "value (#{email}, #{title}, #{feedLink}, #{keyword}, now())")
	void insert(Keywords keyword) throws Exception;
	
	@Results({
		@Result(property = "email", column = "username"),
		@Result(property = "title", column = "feedtitle")
	})
	@Delete("delete from keywords "
			+ "where username = #{email} "
			+ "and feedlink = #{feedlink}")
	void delete(@Param("email") String email, @Param("feedlink") String feedlink) throws Exception;
}
