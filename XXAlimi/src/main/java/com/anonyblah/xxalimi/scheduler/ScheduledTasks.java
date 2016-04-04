package com.anonyblah.xxalimi.scheduler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.anonyblah.xxalimi.dao.FeedList;
import com.anonyblah.xxalimi.rss.RSSFeedParser;
import com.anonyblah.xxalimi.vo.Article;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;

@Component("ScanningFeed")
public class ScheduledTasks {

	// @Autowired // Spring에서 자동으로 Set
	// private FeedDao feedDao;
	//
	// @Autowired
	// private ArticleDao articleDao;

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Transactional
	public void scanningFeed() throws IllegalArgumentException, FeedException, IOException {
		List<SyndFeed> allFeed = FeedList.feedList;

		SyndFeed scanningFeed;

		List<SyndEntry> articleList = null;
		Iterator<SyndFeed> iter = allFeed.iterator();

		List<String> oldID = null;
		List<String> newID = null;

		boolean isUpdated;

		while (iter.hasNext()) {
			System.out.println("---------Update Logic---------");
			isUpdated = false;

			oldID = new ArrayList<String>();
			newID = new ArrayList<String>();

			scanningFeed = iter.next();

			int count = 0;

			articleList = scanningFeed.getEntries();
			Hibernate.initialize(articleList);

			for (int i = 0; i < articleList.size(); i++) {
				oldID.add(articleList.get(i).getLink());
			}

			int parserSize = ParserList.parserList.size();

			for (int i = 0; i < parserSize; i++) {

				RSSFeedParser parser = ParserList.parserList.get(i);

				if (parser.getFeedLink().equals(scanningFeed.getLink())) {
					
					scanningFeed = parser.readFeed();
					for (int j = 0; j < allFeed.size(); j++) {
						if (allFeed.get(j).getLink().equals(scanningFeed.getLink())) {
							allFeed.set(j, scanningFeed);
						}
					}
					articleList = scanningFeed.getEntries();
					Hibernate.initialize(articleList);

					for (int j = 0; j < articleList.size(); j++) {
						newID.add(articleList.get(j).getLink());
					}

				
				}
			}

			System.out.println("old = " + oldID.size() + "new = " + newID.size());
			for (int i = 0; i < newID.size(); i++) {

				for (int j = 0; j < oldID.size(); j++) {
					if (newID.get(i).equals(oldID.get(j))) {
						count++;
						break;
					}
				}
			}

			System.out.println(count);
			if (count != newID.size()) {
				isUpdated = true;
			}

			if (isUpdated) {
				articleList = scanningFeed.getEntries();
				System.out
						.println("[" + dateFormat.format(new Date()) + "]" + scanningFeed.getTitle() + " is Updated!!");
				for(int i=0; i<articleList.size()-count; i++){
					System.out.println(articleList.get(i).getTitle());
				}
			} else {
				System.out.println(
						"[" + dateFormat.format(new Date()) + "]" + scanningFeed.getTitle() + " is not updated yet");
			}
			System.out.println("--------- ENd Update Logic---------");

		}


	}

}
