package com.anonyblah.xxalimi.dao;

import java.util.List;

import com.anonyblah.xxalimi.vo.Feed;
import com.sun.syndication.feed.synd.SyndFeed;

/**
 * @see Feed
 */
public interface FeedDao {
	  List<SyndFeed> selectList() throws Exception;
	  int insert(SyndFeed member) throws Exception;
	  int delete(int no) throws Exception;
	  SyndFeed selectOne(int no) throws Exception;
	  int update(SyndFeed member) throws Exception;
	  SyndFeed exist(String feedUrl) throws Exception;

}
