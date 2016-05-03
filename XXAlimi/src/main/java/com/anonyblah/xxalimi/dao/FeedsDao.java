package com.anonyblah.xxalimi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.anonyblah.xxalimi.vo.Feeds;
import com.sun.syndication.feed.synd.SyndFeed;

/**
 * @see Feeds
 */

@Repository
public interface FeedsDao {
	
	@Select("select feedurl from feeds"
			+ "where username = #{email}")
	List<SyndFeed> selectList(String email) throws Exception;

	@Insert("insert into feeds (username, feedlink, feedtitle, feedauthor, copyright, imageurl, language, pub_date, cre_date)"
	+ "values (#{email}, #{link}, #{title}, #{author}, #{copyright}, #{imageUrl}, #{language}, #{publishedDate}, now())")
	void insert(Feeds feeds) throws Exception;
	// int delete(int no) throws Exception;
	// SyndFeed selectOne(int no) throws Exception;
	// int update(SyndFeed member) throws Exception;
	// SyndFeed exist(String feedUrl) throws Exception;

}
