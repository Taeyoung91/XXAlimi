<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage - XXAlimi ver 0.0.1</title>

<link rel="stylesheet" href="/css/bootstrap-social.css">
<link rel="stylesheet"
	href="/webjars/bootstrap/3.3.6/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/stylish-portfolio.css">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/css/stylish-portfolio.css">
<script type="text/javascript" src="/js/notification.js"></script>

</head>

<body class="homebg2">
	<%@include file="/WEB-INF/view/MenuBar.jsp"%>
	<!-- Navigation -->

	<aside class="homebg">
	<div class="text-vertical-left col-md-8 col-md-offset-2">
		</br> </br>
		<figure class="pull-left"> <img
			class="media-object img-rounded img-responsive" src="${imageUrl}"
			alt=""> </figure>
		</br> </br>
		<h3>${title}</h3>
		<h4>updated on ${pubDate}</h4>
	</aside>
	</br>
	</br>
	<aside class="feedbg1 pull-left">
	<div class="text-vertical-left col-md-8 col-md-offset-2">
		</br> </br>

	</div>
	<div class="text-center">
		<h1 class="glyphicon glyphicon-exclamation-sign">The Newest
			Articles</h1>
		</br> </br> </br> </br>
		<c:forEach var="article" items="${articleList}" end="5">
			<c:if test="${article.articleTitle.length() < 41}">
				<a href="${article.articleLink}" class="btn btn-white btn-lg"> <strong
					class="fa fa-sign-in"> ${article.articleTitle}</strong>
				</a>
			</c:if>
		</c:forEach>

		<div class="text-center">
			<h1 role="button" class="glyphicon glyphicon-sort-by-alphabet-alt"
				data-toggle="modal" data-target="#articleModal"></h1>
		</div>
		<div class="modal fade" id="articleModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							<font color="red"> 전체 게시글 </font>
						</h4>
					</div>
					<div class="modal-body">
						<c:forEach var="article" items="${articleList}">

							<a href="${article.articleLink}"> <strong
								class="fa fa-sign-in"> <font color="black">
										${article.articleTitle} </font></strong>
							</a>
							<br>
							<h5>
								<font color="black">posted by ${article.articleAuthority}</font>
							</h5>
							<h5>
								<font color="black">updated by ${article.publishedDate}</font>
							</h5>
							<br>

						</c:forEach>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	</aside>

	<aside class="feedbg2 pull-right">
	<div class="text-vertical-right col-md-8 col-md-offset-2">
		</br> </br>

	</div>
	<div class="text-center">
		<div class="row">
			<h1 class="glyphicon glyphicon-edit">Article with Keyword</h1>
			</br> </br> </br> </br>
			<c:if test="${keywordArticleList[0] == null}">
				<h1>등록한 키워드 없음</h1>
			</c:if>
			<c:if test="${keywordArticleList[0] != null}">

				<c:forEach var="keywordArticle" items="${keywordArticleList}"
					end="5">
					<c:if test="${keywordArticle.articleTitle.length() < 41}">
						<a href="${keywordArticle.articleLink}"
							class="btn btn-white btn-lg" data-toggle="tooltip"
							title="#Keyword :: ${keywordArticle.keyword}"> <strong
							class="fa fa-sign-in"> <font color="white">${keywordArticle.articleTitle}

							</font></strong>
						</a>
					</c:if>
				</c:forEach>
				<div class="text-center">
					<h1 role="button" class="glyphicon glyphicon-sort-by-alphabet-alt"
						data-toggle="modal" data-target="#keywordModal"></h1>
				</div>
			</c:if>
		</div>




		<!-- 모달 팝업 -->
		<div class="modal fade" id="keywordModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							<font color="red"> 키워드를 포함한 게시글 </font>
						</h4>
					</div>
					<div class="modal-body">
						<c:forEach var="keywordArticle" items="${keywordArticleList}">

							<a href="${keywordArticle.articleLink}" data-toggle="tooltip"
								title="keyword = ${keywordArticle.keyword}"> <strong
								class="fa fa-sign-in"><font color="black">
										${keywordArticle.articleTitle}</font></strong>
							</a>
							<h5>
								<font color="black">posted by
									${keywordArticle.articleAuthority}</font>
							</h5>


							<h5>
								<font color="black">updated by
									${keywordArticle.publishedDate}</font>
							</h5>

							</br>
						</c:forEach>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	</aside>

	<%-- 	<div class="feedView">


		<hr class="medium">
		<div class="row">
			<c:forEach var="article" items="${articleList}">
				<div class="col-md-4 col-sm-4 text-center">
					<div class="servies-item">
						<span class="fa-stack fa-4x"> <i
							class="fa fa fa-weibo text-link"></i>
						</span>
						<h3>
							<a href="${article.articleLink}" class="btn btn-dark btn-lg">
								<strong>${article.articleTitle}</strong>
							</a>
						</h3>

						<c:if test="${article.articleAuthority != null }">
							<h5>posted by ${article.articleAuthority}</h5>
						</c:if>

						<c:if test="${article.publishedDate != null }">
							<h5>updated by ${article.publishedDate}</h5>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	</div> --%>

	<script src="/webjars/jquery/2.2.2/dist/jquery.min.js"></script>
	<script src="/webjars/bootstrap/3.3.6/dist/js/bootstrap.min.js"></script>
	<script>
		$(function() {
			$('[data-toggle="tooltip"]').tooltip();
		})
		// Closes the sidebar menu
		$("#menu-close").click(function(e) {
			e.preventDefault();
			$("#sidebar-wrapper").toggleClass("active");
		});
		// Opens the sidebar menu
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#sidebar-wrapper").toggleClass("active");
		});
	</script>
	<jsp:include page="/WEB-INF/view/user/Tail.jsp" />

</body>
</html>