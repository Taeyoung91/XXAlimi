<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="/webjars/bootstrap/3.3.6/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/stylish-portfolio.css">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="/css/bootstrap-social.css">
<link rel="stylesheet" href="/css/mindmap/js-mindmap.css" type="text/css">
<link rel="stylesheet" href="/css/mindmap/style.css" type="text/css">
<!-- jQuery -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>
  <!-- UI, for draggable nodes -->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.15/jquery-ui.min.js"></script>
<!-- <script src="/js/mindmap/dataprint.js" type="text/javascript"></script> -->
<script src="/js/mindmap/raphael-min.js" type="text/javascript"></script>
<script src="/js/mindmap/js-mindmap.js" type="text/javascript"></script>
<script src="/js/mindmap/script.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/notification.js"></script>
	
<title>Feeds Space</title>

</head>
<body>

	<!-- Navigation -->

	<%@include file="/WEB-INF/view/MenuBar.jsp" %>  


	<ul class="space">
		<li><a href="#">YOU!</a>
			<ul id="feeds">
				<c:forEach var="feed" items="${feedList}">
					<li><a href="#">${feed.title}</a>
						<ul>
							<c:forEach var="article" items="${articleList}" end="4">
								<li><a href="${article.articleLink}">${article.articleTitle}</a></li>>
							</c:forEach>
						</ul>
					</li>
				</c:forEach>
			</ul>
		</li>
	</ul>

</body>
</html>