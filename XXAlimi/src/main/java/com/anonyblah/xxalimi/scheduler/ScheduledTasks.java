package com.anonyblah.xxalimi.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.anonyblah.xxalimi.controls.HomeController;
import com.anonyblah.xxalimi.dao.ArticlesDao;
import com.anonyblah.xxalimi.rss.RSSFeedParser;
import com.anonyblah.xxalimi.service.ArticleService;
import com.anonyblah.xxalimi.service.FeedService;
import com.anonyblah.xxalimi.service.KeywordService;
import com.anonyblah.xxalimi.service.SessionService;
import com.anonyblah.xxalimi.service.UserDetailsImpl;
import com.anonyblah.xxalimi.vo.Articles;
import com.anonyblah.xxalimi.vo.Feeds;
import com.anonyblah.xxalimi.vo.Keywords;
import com.sun.syndication.feed.synd.SyndFeed;

@Component("ScanningFeed")
public class ScheduledTasks {

	private static Logger log = LoggerFactory.getLogger(HomeController.class);

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");

	@Autowired
	FeedService feedService;

	@Autowired
	ArticleService articleService;

	@Autowired
	KeywordService keywordService;

	@Autowired
	SessionService sessionService;

	@Autowired // Spring에서 자동으로 Set
	private ArticlesDao articleDao;

	@Autowired
	private Articles articles;

	@Autowired
	private SimpMessagingTemplate template; // 웹 소켓방식으로 업데이트된 내용을 클라이언트로 보내기 위해
											// 사용되는 객체

	@Transactional
	public void scanningFeed() throws Exception {
		boolean keywordIsContained = false;
		System.out.println(
				"scanningFeed() 실행==============================================================================\n\n");

		if (sessionService.getLoggedInUsers().size() > 0) { // 현재 로그인 된 상태 일 때

			List<Feeds> allFeed = feedService.outputFeed(); // 저장된 피드 전체 DB에서
															// 불러오기
			boolean keywordIsEmpty = true;
			System.out.println("allFeed : " + allFeed.size());

			for (int index = 0; index < allFeed.size(); index++) { // 저장된 각 피드
																	// 별로 파싱 진행

				String updatedArticleTitle = null;

				boolean isUpdated = false;

				int count = 0;
				int keywordCount = 0;

				List<Articles> articleList = articleService
						.outputArticlesByUserFeedTitle(allFeed.get(index).getUsersfeedTitle()); // 각
																								// 피드에
																								// 딸린
																								// 기사
																								// 전체
																								// DB에서
																								// 불러오기
				
				System.out.println("alk;sdfasdfasdfasd;lfkajsdlkdfl;aksdjfla;skdfjasl;kdfj" + articleList.size());

				System.out.println("---------Update Logic---------");

				// Hibernate.initialize(articleList);

				RSSFeedParser parser = new RSSFeedParser(allFeed.get(index).getLink()); // 파싱
																						// 진행
				parser.readFeed();
				SyndFeed syndFeed = parser.getFeed();

				System.out.println(articleList.get(0).getArticleTitle() + " articleList.size : " + articleList.size());

				for (int j = 0; j < parser.getArticleEntries().size(); j++) { // 새로
																				// 파싱한
																				// 모든
																				// 기사
																				// 하나씩
																				// keyword
																				// 조사
					System.out.println("index.j : " + index + ", " + j);
					if (articleList.get(0).getArticleLink().equals(parser.getArticleEntries().get(j).getLink())) {
						// update된 기사와 기존 DB에 저장된 해당 피드의 첫기사 링크를 비교하여 같으면 not
						// updated
						break; // 다음 기사로 넘어간다.
					} else { // update된 기사가 있으면
						List<Keywords> feedKeywordList = keywordService
								.OutputKeywordByLink(allFeed.get(index).getLink());
								// 해당 피드에 저장된 keyword들 DB에서 불러오기
						String tempKey = null;
						// if (feedKeywordList.isEmpty()) { // 해당 피드에 저장된
						// keyword가 업으면 db에 update된 기사 저장

						// } else { // 해당 피드에 저장된 keyword가 있으면
						if (!feedKeywordList.isEmpty()) {
							System.out.println("\n.............Filtering Keyword is Starting...........\n");
							keywordIsEmpty = false;
							

							for (int k = 0; k < feedKeywordList.size(); k++) { // 각
																				// keyword별로
																				// 해당
																				// 기사에
																				// keyword가
																				// 저장되어
																				// 있는지
																				// 탐색

								if (parser.getArticleEntries().get(j).getAuthor()
										.contains(feedKeywordList.get(k).getKeyword())) {

									keywordIsContained = true;
									tempKey = feedKeywordList.get(k).getKeyword();
									System.out.println("$@#$@#$@#$@#$keyword============================#$@$@#$@#$@#$"
											+ "asdfasdf asf asdf  === " + tempKey);

								} else if (parser.getArticleEntries().get(j).getDescription().getValue()
										.contains(feedKeywordList.get(k).getKeyword())) {
									keywordIsContained = true;
									tempKey = feedKeywordList.get(k).getKeyword();
									System.out.println("$@#$@#$@#$@#$keyword============================#$@$@#$@#$@#$"
											+ "asdfasdf asf asdf  === " + tempKey);

								} else if (parser.getArticleEntries().get(j).getTitle()
										.contains(feedKeywordList.get(k).getKeyword())) {
									keywordIsContained = true;
									tempKey = feedKeywordList.get(k).getKeyword();
									System.out.println("$@#$@#$@#$@#$keyword============================#$@$@#$@#$@#$"
											+ "asdfasdf asf asdf  === " + tempKey);
								}
							}
							if(keywordIsContained){
								keywordCount ++;
							}
							// if (keywordIsContained) { // 키워드 포함된 기사 찾은 경우
							// System.out.println("\nkeyword적용된 기사
							// 등록!!!!!!!!!!!!!!!!!!\n"
							// + parser.getArticleEntries().get(j).getTitle() +
							// "\n");
							// articles.setUsersfeedTitle(allFeed.get(index).getEmail()
							// + allFeed.get(index).getTitle());
							// articles.setArticleLink(parser.getArticleEntries().get(j).getLink());
							// articles.setArticleTitle(parser.getArticleEntries().get(j).getTitle());
							// articles.setEmail(allFeed.get(index).getEmail());
							// articles.setFeedTitle(allFeed.get(index).getTitle());
							// articles.setFeedLink(allFeed.get(index).getLink());
							// articles.setContent(parser.getArticleEntries().get(j).getDescription().getValue());
							// articles.setArticleAuthority(parser.getArticleEntries().get(j).getAuthor());
							// if
							// (parser.getArticleEntries().get(j).getPublishedDate()
							// != null) {
							// articles.setPublishedDate(
							// dateFormat.format(parser.getArticleEntries().get(j).getPublishedDate()));
							// }
							// articleDao.insert(articles);
							//
							// keywordIsContained = false;
							//
							// count++;
							//
							// updatedArticleTitle =
							// parser.getArticleEntries().get(j).getTitle();
							// }
							System.out.println("keyword count : " + count);

							System.out.println("\n.............Filtering Keyword is Finished...........");
							// }
						}
						articles.setUsersfeedTitle(allFeed.get(index).getEmail() + syndFeed.getTitle());
						articles.setArticleLink(parser.getArticleEntries().get(j).getLink());
						articles.setArticleTitle(parser.getArticleEntries().get(j).getTitle());
						articles.setEmail(allFeed.get(index).getEmail());
						articles.setFeedTitle(syndFeed.getTitle());
						articles.setFeedLink(allFeed.get(index).getLink());
						articles.setContent(parser.getArticleEntries().get(j).getDescription().getValue());
						articles.setArticleAuthority(parser.getArticleEntries().get(j).getAuthor());
						if (syndFeed.getPublishedDate() != null) {
							articles.setPublishedDate(
									dateFormat.format(parser.getArticleEntries().get(j).getPublishedDate()));
						}
						
						System.out.println("TEMPKEYTEMPKEYTEMPKEYTEMPKEYTEMPKEYTEMPKEYTEMPKEYTEMPKEYTEMPKEYTEMPKEYTEMPKEYTEMPKEYTEMPKE=" + tempKey);
						articles.setKeyword(tempKey);
						articleDao.insert(articles); // DB에 기사 저장

						count++;
						keywordIsContained = false;
						updatedArticleTitle = parser.getArticleEntries().get(0).getTitle();

					}

				}

				System.out.println("updatedCount : " + count);

				if (count != 0) {
					isUpdated = true;
				}

				if(isUpdated && !keywordIsEmpty){
					int numForUpdatedArticle = keywordCount;
					for (final Object user : sessionService.getLoggedInUsers()) {
						final UserDetailsImpl temp = (UserDetailsImpl) user;
						log.info(temp.getUsername());
						
						for(int n=0; n<articleList.size(); n++){
							if(articleList.get(n).getKeyword() != null){
								updatedArticleTitle = articleList.get(n).getArticleTitle();
								if (temp.getUsername().equals(articleList.get(n).getEmail())) {
									if (numForUpdatedArticle == 1) {
										this.template.convertAndSendToUser(temp.getUsername(), "/message/notification",
												articleList.get(n).getFeedTitle() + "\n" + updatedArticleTitle + 
												"\n" + " # 키워드 :: " + articleList.get(n).getKeyword());
										sendToMobile(temp.getUsername(), articleList.get(n).getFeedTitle());
										break;
									} else {
										this.template.convertAndSendToUser(temp.getUsername(), "/message/notification",
												articleList.get(n).getFeedTitle() + "\n" + updatedArticleTitle + " 외 "
														+ (numForUpdatedArticle - 1) + "개의 새 글" + 
														"\n" + " # 키워드 :: " + articleList.get(n).getKeyword());
										sendToMobile(temp.getUsername(), articleList.get(n).getFeedTitle());
										break;
									}
								}
							}
						}
						
						
					}
					keywordIsEmpty = true;
					System.out.println("[" + dateFormat.format(new Date()) + "]" + articleList.get(index).getFeedTitle()
							+ " is Updated!!");
					continue;
					
				}
				
				else if (isUpdated && keywordIsEmpty) {
					int numForUpdatedArticle = count;
					for (final Object user : sessionService.getLoggedInUsers()) {
						final UserDetailsImpl temp = (UserDetailsImpl) user;
						log.info(temp.getUsername());
						
						
						if (temp.getUsername().equals(articleList.get(index).getEmail())) {
							if (numForUpdatedArticle == 1) {
								this.template.convertAndSendToUser(temp.getUsername(), "/message/notification",
										articleList.get(index).getFeedTitle() + "\n" + updatedArticleTitle);
							} else {
								this.template.convertAndSendToUser(temp.getUsername(), "/message/notification",
										articleList.get(index).getFeedTitle() + "\n" + updatedArticleTitle + " 외 "
												+ (numForUpdatedArticle - 1) + "개의 새 글") ;
							}
							sendToMobile(temp.getUsername(), articleList.get(index).getFeedTitle());
						}
					}

					System.out.println("[" + dateFormat.format(new Date()) + "]" + articleList.get(index).getFeedTitle()
							+ " is Updated!!");
					
				}
				

				else {
					System.out.println("[" + dateFormat.format(new Date()) + "]" + articleList.get(index).getFeedTitle()
							+ " is not updated yet");
				}
				System.out.println("--------- ENd Update Logic---------");

			}

		}

	}
	private void sendToMobile(String email, String title) {
		// For Mobile App
		this.template.convertAndSend("/topic/message/" + email, title + "에서 새 글을 읽을 수 있습니다.");
	}
}
