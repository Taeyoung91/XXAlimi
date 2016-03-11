<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<% //탭 4개의 스타일이 적용된 RSS 피드 - Google AJAX Feed API 사용 %>
		<% //출처 : http://blog.naver.com/whitefre/140075585046 %>
		<title>Tabbed Feed Control - Google AJAX Feed API Sample1</title>
		<% //스타일 적용 %>
		<style type="text/css">
			body *, table *,
			body {
				font-family: "Arial", sans-serif;
				font-size: 13px;
				}
			#feedControl {
				width: 400px;
				margin: 20px;
				}
		</style>
		<% //Google의 Feed Api를 불러오는 코드 %>
		<script src="http://www.google.com/jsapi" type="text/javascript"></script>
		<script language="Javascript" type="text/javascript">//<![CDATA[
			//load the AJAX Feed API
			google.load("feeds", "1");
			function OnLoad() {
				//create a feed control
				var feedControl = new google.feeds.FeedControl();
				//feedControl.addFeed(Rss주소, 이름)
				feedControl.addFeed("http://www.digg.com/rss/index.xml", "Digg");
				feedControl.addFeed("https://googleblog.blogspot.kr/atom.xml", "atom");
				feedControl.addFeed("http://feeds.feedburner.com/Techcrunch", "Tech");
				feedControl.addFeed("http://blogoscoped.com/rss.xml", "blog");
				//feedControl.addFeed("http://feeds.feedburner.com/NaverNewsGameESports", "NaverNewsGame");
				//feedControl.addFeed("http://www.hansung.ac.kr/web/www/cmty_01_01?p_p_id=EXT_BBS&p_p_lifecycle=0&p_p_state=exclusive&p_p_mode=view&p_p_col_id=column-1&p_p_col_pos=1&p_p_col_count=3&_EXT_BBS_struts_action=%2Fext%2Fbbs%2Frss", "Hansung");
				feedControl.setLinkTarget(google.feeds.LINK_TARGET_BLANK);
				feedControl.setNumEntries(10);
				feedControl.draw(document.getElementById("feedControl"),
						{drawMode : google.feeds.FeedControl.DRAW_MODE_TABBED});
			}
			google.setOnLoadCallback(OnLoad);
		//]]>
		</script>
	</head>
	<body>
		<div id="feedControl">Loading</div>
	</body>
</html>