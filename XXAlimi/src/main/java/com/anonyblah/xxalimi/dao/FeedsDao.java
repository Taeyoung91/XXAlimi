package com.anonyblah.xxalimi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.anonyblah.xxalimi.vo.Feeds;

/**
 * @see Feeds
 */

@Repository
public interface FeedsDao {
	
	@Results({
			@Result(property = "id", column = "feed_id"),
			@Result(property = "link", column = "feedlink"),
			@Result(property = "usersfeedTitle", column = "usersfeedtitle"),
			@Result(property = "title", column = "feedtitle"),
			@Result(property = "author", column = "feedauthor"),
			@Result(property = "email", column = "username"),
			@Result(property = "publishedDate", column = "pub_date"),
			@Result(property = "createdDate", column = "cre_date")
	})	
	@Select("select * from feeds "
			+ "where username = #{email} "
			+ "order by feed_id desc")
	List<Feeds> findAllFeedsByEmail(String email) throws Exception;
	
	
	@Results({
		@Result(property = "title", column = "feedtitle"),
		@Result(property = "link", column = "feedlink"),
		@Result(property = "email", column = "username")
	})	
	@Select("select feedtitle, feedlink from feeds "
			+ "where username = #{email}")
	List<Feeds> findFeedUrlAndTitleByEmail(String email) throws Exception;

	
	@Insert("insert into feeds (username, feedlink, usersfeedtitle, feedtitle, feedauthor, copyright, imageurl, language, pub_date, cre_date)"
	+ "values (#{email}, #{link}, #{usersfeedTitle}, #{title}, #{author}, #{copyright}, #{imageUrl}, #{language}, #{publishedDate}, now())")
	void insert(Feeds feeds) throws Exception;
	
	
	@Delete("delete from feeds "
			+ "where username = #{email} "
			+ "and feedlink = #{feedUrl}")
	void delete(@Param("feedUrl") String feedUrl, @Param("email") String email) throws Exception;
	// SyndFeed selectOne(int no) throws Exception;
	// int update(SyndFeed member) throws Exception;
	// SyndFeed exist(String feedUrl) throws Exception;

}
