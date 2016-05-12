package com.anonyblah.xxalimi.rss;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;



public class RSSFeedParser {

	private String feedLink;

	// private FeedDao feedDao;
	//
	// private ArticleDao articleDao;

	URL url;
	SyndFeedInput input;
	SyndFeed feed;
	List<SyndEntry> articleEntries;
	SyndEntry entry = null;

	public RSSFeedParser(String feedUrl) throws MalformedURLException {
		url = new URL(feedUrl);
		input = new SyndFeedInput();
	}

	public void readFeed() throws IllegalArgumentException, FeedException, IOException {
//		input = new SyndFeedInput();
		feed = input.build(new XmlReader(url));
		feedLink = feed.getLink();
		articleEntries = feed.getEntries();
	}

	
	
	public SyndFeed getFeed() {
		return feed;
	}

	public String getFeedLink() {
		return this.feedLink;
	}
	
	public List<SyndEntry> getArticleEntries() {
		return articleEntries;
	}
	
}