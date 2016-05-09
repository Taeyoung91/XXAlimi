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
<link rel="stylesheet"
	href="/webjars/bootstrap/3.3.6/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/stylish-portfolio.css">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<!-- Header -->
	<header id="top" class="header">
	<div class="text-vertical-center">
		<h1>XXAlimi</h1>
		<h3>ver 0.0.1 for Team AnonyBlah</h3>
		<br> <a href="/user/home/refreshFeed" class="btn btn-dark btn-lg">Refresh
			RSS</a>
	</div>
	</header>


	<div class="feedView">

		<h2 class="post-title">${articleList.articleTitle}</h2>

		<p class="post-meta">posted by ${articleList.articleAuthority}</p>
		<hr class="medium">
		<div class="row">

			<div class="col-md-4 col-sm-4 text-center">
				<div class="servies-item">
					<span class="fa-stack fa-4x"> <i
						class="fa fa-circle fa-stack-2x"></i> <i
						class="fa fa-cloud fa-stack-1x text-primary"></i>
					</span>
					<h3>
						<strong>${articleList.content}</strong>
					</h3>
					<a class="btn btn-info" href="${articleList.articleLink}">Go to read!</a>
				</div>
			</div>

		</div>
	</div>


	<footer>
	<div class="container">
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1 text-center">
				<h4>
					<strong>XXAlimi</strong>
				</h4>
				<p>
					ver 0.0.1<br>Team AnonyBlah
				</p>
				<hr class="small">
				<p class="text-muted">Copyright &copy; Hansung Univ. Capstone
					Design 2016</p>
			</div>
		</div>
	</div>
	</footer>
</body>
</html>