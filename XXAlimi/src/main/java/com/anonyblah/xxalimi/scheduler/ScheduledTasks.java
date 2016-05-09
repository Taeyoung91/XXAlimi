package com.anonyblah.xxalimi.scheduler;

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

import com.anonyblah.xxalimi.dao.ArticlesDao;
import com.anonyblah.xxalimi.dao.FeedsDao;
import com.anonyblah.xxalimi.dao.KeywordDao;
import com.anonyblah.xxalimi.rss.RSSFeedParser;
import com.anonyblah.xxalimi.vo.Articles;
import com.anonyblah.xxalimi.vo.Feeds;
import com.anonyblah.xxalimi.vo.Keywords;
import com.anonyblah.xxalimi.vo.User;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

@Component("ScanningFeed")
public class ScheduledTasks {

	@Autowired
	private Keywords keywords;

	@Autowired
	private KeywordDao keywordDao;

	@Autowired // Spring에서 자동으로 Set
	private ArticlesDao articleDao;
	//
	@Autowired
	private FeedsDao feedsDao;

	@Autowired
	private Articles articles;

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	@Autowired
	User user;

	@Autowired
	private SimpMessagingTemplate template;

	@Transactional
	public void scanningFeed() throws Exception {
		System.out.println(
				"scanningFeed() 실행==============================================================================\n\n");

		if (user.getEmail() != null) {

			List<Feeds> allFeed = feedsDao.findFeedUrlAndTitleByEmail(user.getEmail());

			System.out.println("allFeed : " + allFeed.size());

			for (int index = 0; index < allFeed.size(); index++) {
				System.out.println("index : " + index);

				List<Articles> articleList = articleDao.findArticlesByEmailAndFeedUrl(allFeed.get(index).getLink(),
						user.getEmail());

				String updateArticleTitle = null;

				boolean isUpdated;

				System.out.println("---------Update Logic---------");
				isUpdated = false;

				int count = 0;
				int notkeywordcount = 0;

				Hibernate.initialize(articleList);

				RSSFeedParser parser = new RSSFeedParser(allFeed.get(index).getLink());
				parser.readFeed();
				SyndFeed syndFeed = parser.getFeed();

				System.out.println(articleList.get(0).getArticleTitle() + " articleList.size : " + articleList.size());
				for (int j = 0; j < parser.getArticleEntries().size(); j++) {
					System.out.println("index.j : " + index + ", " + j);
					if (articleList.get(0).getArticleLink().equals(parser.getArticleEntries().get(j).getLink())) { // update된
																													// 새
																													// 피드의
																													// 기사와
																													// 기존
																													// DB에
																													// 저장된
																													// 해당
																													// 피드의첫기사
																													// 링크를
																													// 비교하여
																													// 같으면
																													// not
																													// updated
						System.out
								.println("articleList.get(0).getArticleTitle()" + articleList.get(0).getArticleTitle());

						System.out.println("j : " + j);
						break;
					} else { // update된 기사가 있으면
						notkeywordcount++; // 키워드 상관없이 업데이트된 기사 수
						
						System.out.println("\n\n\n\nERROR : " + allFeed.get(index).getLink() + "\n\n\n");
						
						List<Keywords> feedKeywordList = keywordDao.findKeywordByEmailAndFeedLink(user.getEmail(),
								allFeed.get(index).getLink());
						if (feedKeywordList.isEmpty()) { // 해당 피드에 저장된 keyword가
															// 업으면 db에 update된
															// 기사 저장
							System.out.println("parser.getArticleEntries().get(0).getTitle()"
									+ parser.getArticleEntries().get(0).getTitle());
							articles.setUsersfeedTitle(user.getEmail() + syndFeed.getTitle());
							articles.setArticleLink(parser.getArticleEntries().get(j).getLink());
							articles.setArticleTitle(parser.getArticleEntries().get(j).getTitle());
							articles.setEmail(user.getEmail());
							articles.setFeedTitle(syndFeed.getTitle());
							articles.setFeedLink(allFeed.get(index).getLink());
							articles.setContent(parser.getArticleEntries().get(j).getDescription().getValue());
							articles.setArticleAuthority(parser.getArticleEntries().get(j).getAuthor());
							if (syndFeed.getPublishedDate() != null) {
								articles.setPublishedDate(
										dateFormat.format(parser.getArticleEntries().get(j).getPublishedDate()));
							}
							articleDao.insert(articles);
							count++;
							updateArticleTitle = parser.getArticleEntries().get(0).getTitle();
						} else { // 해당 피드에 저장된 keyword가 있으면
							System.out.println("\n.............Filtering Keyword is Starting...........\n");
							System.out.println("feedLink : " + allFeed.get(index));

							// 해당 피드의 업데이트된 기사들을 등록된 키워드들로 필터링 후 isFiltered에 저장

							boolean keywordIsContained = false;
							int keywordUpdatedCount = 0;

							System.out.println(
									"notkeywordAndupdatedArticleTitle" + parser.getArticleEntries().get(j).getTitle());
							for (int k = 0; k < feedKeywordList.size(); k++) {

								if (parser.getArticleEntries().get(j).getAuthor()
										.contains(feedKeywordList.get(k).getKeyword())) {

									keywordIsContained = true;

								} else if (parser.getArticleEntries().get(j).getDescription().getValue()
										.contains(feedKeywordList.get(k).getKeyword())) {
									keywordIsContained = true;

								} else if (parser.getArticleEntries().get(j).getTitle()
										.contains(feedKeywordList.get(k).getKeyword())) {
									keywordIsContained = true;
								}
							}
							if (keywordIsContained) {
								System.out.println("\nkeyword적용된 기사 등록!!!!!!!!!!!!!!!!!!\n"
										+ parser.getArticleEntries().get(j).getTitle() + "\n");
								articles.setUsersfeedTitle(user.getEmail() + allFeed.get(index).getTitle());
								articles.setArticleLink(parser.getArticleEntries().get(j).getLink());
								articles.setArticleTitle(parser.getArticleEntries().get(j).getTitle());
								articles.setEmail(user.getEmail());
								articles.setFeedTitle(allFeed.get(index).getTitle());
								articles.setFeedLink(allFeed.get(index).getLink());
								articles.setContent(parser.getArticleEntries().get(j).getDescription().getValue());
								articles.setArticleAuthority(parser.getArticleEntries().get(j).getAuthor());
								if (parser.getArticleEntries().get(j).getPublishedDate() != null) {
									articles.setPublishedDate(
											dateFormat.format(parser.getArticleEntries().get(j).getPublishedDate()));
								}
								articleDao.insert(articles);

								keywordIsContained = false;
								count++;
								updateArticleTitle = parser.getArticleEntries().get(j).getTitle();
							}
							System.out.println("keyword count : " + count);

							System.out.println("\n.............Filtering Keyword is Finished...........");
						}
					}

				}

				System.out.println("updatedCount : " + count);
				if (count != 0) {
					isUpdated = true;

				}

				if (isUpdated) {
					int numForUpdatedArticle = count;
					System.out.println("user.getEmail()" + user.getEmail());
					System.out.println("articleList.get(index).getEmail())" + articleList.get(index).getEmail());
					if (user.getEmail().equals(articleList.get(index)
							.getEmail())/* && numForUpdatedArticle < 14 */) {
						if (numForUpdatedArticle == 1) {
							this.template.convertAndSend("/topic/message",
									articleList.get(index).getFeedTitle() + "\n" + updateArticleTitle);
						} else {
							this.template.convertAndSend("/topic/message", articleList.get(index).getFeedTitle() + "\n"
									+ updateArticleTitle + " 외 " + (numForUpdatedArticle - 1) + "개의 새 글");
						}
					}

					System.out.println("[" + dateFormat.format(new Date()) + "]" + articleList.get(index).getFeedTitle()
							+ " is Updated!!");

					for (int i = 0; i < numForUpdatedArticle; i++) {
						System.out.println("파서에서 updated : " + parser.getArticleEntries().get(i).getTitle());
						System.out.println("DB에 저장돼있는 not updated : " + articleList.get(i).getArticleTitle());
					}

				}

				else {
					System.out.println("[" + dateFormat.format(new Date()) + "]" + articleList.get(index).getFeedTitle()
							+ " is not updated yet");
				}
				System.out.println("--------- ENd Update Logic---------");

			}

		}
	}

}
