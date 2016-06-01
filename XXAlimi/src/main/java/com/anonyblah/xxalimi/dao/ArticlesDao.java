package com.anonyblah.xxalimi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.anonyblah.xxalimi.vo.Articles;


@Repository
public interface ArticlesDao {
		
	@Results({
		@Result(property = "id", column = "article_id"),
		@Result(property = "usersfeedTitle", column = "usersfeedtitle"),
		@Result(property = "email", column = "username"),
		@Result(property = "feedLink", column = "feedlink"),
		@Result(property = "articleLink", column = "articlelink"),
		@Result(property = "articleTitle", column = "articletitle"),
		@Result(property = "publishedDate", column = "pub_date"),
		@Result(property = "createdDate", column = "cre_date")
	})	
	@Select("select * from articles "
			+ "where username = #{email} "
			+ "order by cre_date desc")
	List<Articles> findAllArticlesByEmail(String email) throws Exception;
	
	@Results({
			@Result(property = "id", column = "article_id"),
			@Result(property = "usersfeedTitle", column = "usersfeedtitle"),
			@Result(property = "email", column = "username"),
			@Result(property = "feedTitle", column = "feedtitle"),
			@Result(property = "feedLink", column = "feedlink"),
			@Result(property = "articleLink", column = "articlelink"),
			@Result(property = "articleTitle", column = "articletitle"),
			@Result(property = "publishedDate", column = "pub_date"),
			@Result(property = "createdDate", column = "cre_date")
		})	
	@Select("select * from articles "
			+ "where usersfeedtitle = #{usersfeedTitle} " 	
			+ "order by cre_date desc")
	List<Articles> findArticleByTitle(String usersfeedTitle) throws Exception;
	
	
	@Results({
			@Result(property = "email", column = "username"),
			@Result(property = "feedLink", column = "feedlink"),
			@Result(property = "articleLink", column = "articlelink"),
			@Result(property = "articleTitle", column = "articletitle"),
			@Result(property = "createdDate", column = "cre_date")
	})
	@Select("select username, articletitle, articlelink, feedtitle, feedlink from articles "
			+ "where feedlink = #{feedLink} "
			+ "and username = #{email} "
			+ "order by cre_date desc")
	List<Articles> findArticlesByEmailAndFeedUrl(@Param("feedLink") String feedLink, @Param("email") String email) throws Exception;
	

	@Insert("insert into articles (usersfeedtitle, username, articlelink, feedtitle, feedlink, articleauthority, articletitle, content, pub_date, cre_date, keyword)"
			+ "values (#{usersfeedTitle}, #{email}, #{articleLink}, #{feedTitle}, #{feedLink}, #{articleAuthority}, #{articleTitle}, #{content}, #{publishedDate}, now(), #{keyword})")
	void insert(Articles article) throws Exception;

	@Results({
		@Result(property = "id", column = "article_id"),
		@Result(property = "usersfeedTitle", column = "usersfeedtitle"),
		@Result(property = "email", column = "username"),
		@Result(property = "feedLink", column = "feedlink"),
		@Result(property = "articleLink", column = "articlelink"),
		@Result(property = "articleTitle", column = "articletitle"),
		@Result(property = "publishedDate", column = "pub_date"),
		@Result(property = "createdDate", column = "cre_date")
	})	
	@Select("select * from articles "
			+ "where usersfeedtitle = #{userFeedTitle} "
			+ "order by cre_date desc")
	List<Articles> findArticleByUserFeedTitle(String userFeedTitle);
}
