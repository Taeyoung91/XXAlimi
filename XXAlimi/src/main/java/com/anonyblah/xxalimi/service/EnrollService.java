package com.anonyblah.xxalimi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.anonyblah.xxalimi.rss.RSSFeedParser;
import com.anonyblah.xxalimi.vo.Feeds;
import com.sun.syndication.feed.synd.SyndFeed;

@Service
public class EnrollService {

	// private static Logger log =
	// LoggerFactory.getLogger(HomeController.class);

	@Autowired
	FeedService feedService;

	@Autowired
	ArticleService articleService;

	@Autowired
	LoginService loginService;

	public boolean enrollFeed(String feedUrl) throws Exception {

		RSSFeedParser parser = new RSSFeedParser(feedUrl);

		parser.readFeed();

		SyndFeed syndFeed = parser.getFeed(); // 파서된 feed 가져옴

		List<Feeds> feedList = feedService.outputFeedByEmail(loginService.getID());

		boolean isDuplicated = false;

		for (int i = 0; i < feedList.size(); i++) {

			String compareFeedLink = feedList.get(i).getLink();
			System.out.println("compareFeedLink ====== " + compareFeedLink);

			if (compareFeedLink.equals(parser.getFeedUrl())) {
				isDuplicated = true;
				break;
			}

		}

		if (!isDuplicated) {
			feedService.insertFeed(syndFeed, feedUrl); // 피드 주소 DB에 저장

			articleService.insertArticleList(parser, syndFeed, feedUrl); // 해당
																			// 피드의
																			// 기사리스트
																			// DB에
																			// 저장
		}
		
		return isDuplicated;
	}

}
