package com.anonyblah.xxalimi.scheduler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.anonyblah.xxalimi.dao.FeedList;
import com.anonyblah.xxalimi.dao.KeywordList;
import com.anonyblah.xxalimi.rss.RSSFeedParser;
import com.anonyblah.xxalimi.vo.Article;
import com.anonyblah.xxalimi.vo.Keyword;
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
	public HashMap<String, String> FilteredKeyword = null;
	public boolean isFiltered[] = null;
	public String whereKeyword = null;
	
	@Autowired
	private SimpMessagingTemplate template;

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
				int numForUpdatedArticle = articleList.size() - count;
				this.template.convertAndSend("/topic/message", scanningFeed.getTitle() + " :: " + numForUpdatedArticle + "개의 새 글");
				System.out
						.println("[" + dateFormat.format(new Date()) + "]" + scanningFeed.getTitle() + " is Updated!!");
				
				for (int i = 0; i < numForUpdatedArticle; i++) {
					System.out.println(articleList.get(i).getTitle());
				}

				if (KeywordList.keywordList.size() > 0) {
					
					System.out.println(".............Filtering Keyword is Starting...........");
					
					//해당 피드의 업데이트된 기사들을 등록된 키워드들로 필터링 후 isFiltered에 저장
					HashMap<String, String> isFiltered = filteringKeyword(scanningFeed, articleList,
							numForUpdatedArticle);

					List<Keyword> keywordList = KeywordList.keywordList;
					List<Keyword> keywordForOneFeed = new ArrayList<Keyword>();

					String feedTitle = scanningFeed.getTitle();

					
					//하나의 피드에 있는 키워드들 추출
					for (int i = 0; i < keywordList.size(); i++) {
						if (feedTitle.equals(keywordList.get(i).getTitle())) {
							keywordForOneFeed.add(keywordList.get(i));
						}
					}

					
					
					for (int i = 0; i < keywordForOneFeed.size(); i++) {

						System.out.println("### Filtered Keyword = " + keywordForOneFeed.get(i).getKeyword());

						System.out.println(isFiltered.get(keywordForOneFeed.get(i).getKeyword()));

					}
					System.out.println(".............Filtering Keyword is Finished...........");
				}

			} else {
				System.out.println(
						"[" + dateFormat.format(new Date()) + "]" + scanningFeed.getTitle() + " is not updated yet");
			}
			System.out.println("--------- ENd Update Logic---------");

		}

		// /**
		// * Keyword Scan
		// */
		// List<Keyword> keywordList = KeywordList.keywordList;
		// System.out.println("*****Keyword ********");
		// for (int i = 0; i < keywordList.size(); i++) {
		// System.out.println("Title : " + keywordList.get(i).getTitle());
		// System.out.println("Keyword : " + keywordList.get(i).getKeyword());
		// }

	}

	public HashMap<String, String> filteringKeyword(SyndFeed feed, List<SyndEntry> articleList,
			int numForUpdatedArticle) {

		FilteredKeyword = new HashMap<String, String>();
		boolean keywordIsContained = false;

		List<Keyword> keywordList = KeywordList.keywordList;
		List<Keyword> keywordForOneFeed = new ArrayList<Keyword>();

		String feedTitle = feed.getTitle();

		System.out.println(keywordList.size());

		for (int i = 0; i < keywordList.size(); i++) {
			if (feedTitle.equals(keywordList.get(i).getTitle())) {
				keywordForOneFeed.add(keywordList.get(i));
			}
		}

		for (int i = 0; i < numForUpdatedArticle; i++) {
			SyndEntry article = articleList.get(i);

			for (int j = 0; j < keywordForOneFeed.size(); j++) {
				Keyword keyword = keywordForOneFeed.get(j);

				if (article.getAuthor().contains(keyword.getKeyword())) {

					keywordIsContained = true;
					FilteredKeyword.put(keyword.getKeyword(), article.getTitle());

				} else if (article.getDescription().getValue().contains(keyword.getKeyword())) {
					keywordIsContained = true;
					FilteredKeyword.put(keyword.getKeyword(), article.getTitle());
					
				} else if (article.getTitle().contains(keyword.getKeyword())) {
					keywordIsContained = true;
					FilteredKeyword.put(keyword.getKeyword(), article.getTitle());
				}
			}

		}

		return FilteredKeyword;
	}

}
