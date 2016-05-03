package com.anonyblah.xxalimi.rss;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Component
public class RSSFeedParser{
	
//	private FeedDao feedDao;
//	
//	private ArticleDao articleDao;
	
	
    private SyndFeed feed;
    private List<SyndEntry> entries;        

    SyndFeedInput input;

	
	public RSSFeedParser() {
		this.input = new SyndFeedInput();
	}
	
	public void setFeedUrl(String feedUrl) throws IllegalArgumentException, FeedException, IOException {
		feed = input.build(new XmlReader(new URL(feedUrl)));		//	feed 저장(완성)
	}


	public SyndFeed getFeed() {
		return this.feed;
	}

	
	public List<SyndEntry> getEntries() {
		entries = feed.getEntries();
		
//		System.out.println("article0_Author" + entries.get(0).getAuthor());
		System.out.println("article0_Link" + entries.get(0).getLink());
		System.out.println("article0_Title" + entries.get(0).getTitle());
		System.out.println("article0_Uri" + entries.get(0).getUri());
		System.out.println("article0_Description" + entries.get(0).getDescription().getValue());
//		System.out.println("article0_Author" + entries.get(0).getAuthor());
//		System.out.println("article0_Author" + entries.get(0).getAuthor());
		
		return entries;
	}


	
}