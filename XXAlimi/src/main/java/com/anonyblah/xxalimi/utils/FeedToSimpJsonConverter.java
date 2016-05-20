package com.anonyblah.xxalimi.utils;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.anonyblah.xxalimi.service.ArticleService;
import com.anonyblah.xxalimi.vo.Articles;
import com.anonyblah.xxalimi.vo.Feeds;

public class FeedToSimpJsonConverter {
	
	@Autowired
	ArticleService articleService;
	
	public JSONArray convert(List<Feeds> feedList, List<Articles> articles) throws Exception {
		JSONArray converted = new JSONArray();
		for(int i=0;i<feedList.size();i++) {
			JSONObject json = new JSONObject();
			json.put("title", feedList.get(i).getTitle() != null ? feedList.get(i).getTitle() : "");
			json.put("author", feedList.get(i).getAuthor() != null ? feedList.get(i).getAuthor() : "");
			json.put("img", feedList.get(i).getImageUrl() != null ? feedList.get(i).getImageUrl() : "");
			JSONArray entries = new JSONArray();
			for(int j=0;j<articles.size();j++) {
				if(articles.get(j).getFeedLink().equals(feedList.get(i).getLink())) {
					JSONObject temp = new JSONObject();
					temp.put("title", articles.get(j).getArticleTitle() != null ? articles.get(j).getArticleTitle() : "");
					temp.put("descript", articles.get(j).getContent() != null ? articles.get(j).getContent() : "");
					temp.put("author", articles.get(j).getArticleAuthority() != null ? articles.get(j).getArticleAuthority() : "");
					temp.put("link", articles.get(j).getArticleLink() != null ? articles.get(j).getArticleLink() : "");
					temp.put("updated", articles.get(j).getPublishedDate() != null ? articles.get(j).getPublishedDate() : "");
					entries.add(temp);
				}
			}
			json.put("entries", entries);
			converted.add(json);
		}
		return converted;
	}
	public String nullCheck(String data) {
		if(data == null) {
			return "";
		} else {
			return data;
		}
	}
}
