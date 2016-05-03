<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script src="/js/facebookLogin.js" type="text/javascript"></script>
<title>Welcome to XXAlimi</title>
</head>
<body>
	<!-- Header -->
	<header id="top" class="header">
	<div class="text-vertical-center">
		<h1>XXAlimi</h1>
		<h3>ver 0.1.0 for Team AnonyBlah</h3>
		<br>
		<div class="row">
			<div class="col-xs-4 col-xs-offset-4">
				<c:if test="${ empty pageContext.request.userPrincipal }">
					<form name="loginSuccess" action="/user/home" class="form-signin"
						method="post">
						<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
						<c:if test="${param.error != null}">
							<p>
								Login ERROR!</br>
								<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != NULL}">
								MESSAGE: <c:out
										value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
								</c:if>
							</p>
						</c:if>
						<input type="text" class="form-control input-lg" name="username"
							placeholder="Email Address" required="" autofocus="" /> <input
							type="password" class="form-control input-lg" name="password"
							placeholder="Password" required="" /> <label class="checkbox">
							<input type="checkbox" value="remember-me" id="rememberMe"
							name="rememberMe"> Remember me
						</label>
						<%-- 					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
						<button class="btn btn-lg btn-primary btn-block" type="submit">Login!</button>
						<!-- <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
					</fb:login-button> -->
					</form>
					<br />
					<form action="/register" method="get">
					<button class="btn btn-lg btn-block btn-social btn-facebook"
						onclick="fb_login();">
						<i class="fa fa-facebook"></i> Sign in with Facebook
					</button>
					<!-- 				<fb:login-button class="btn btn-lg btn-block btn-social btn-facebook" scope="public_profile,email" onlogin="checkLoginState();">
					Sign in with Facebook
				</fb:login-button> -->
					<h4>
						If you don't have account? <br> Don't worry
					</h4>
					<a href="/register" class="btn btn-success btn-lg btn-block">Register
						now!</a>
						</form>
				</c:if>
				<c:if test="${ not empty pageContext.request.userPrincipal }">
					<!-- <!-- Navigation -->
					<a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i
						class="fa fa-bars"></i></a>
					<nav id="sidebar-wrapper">
					<ul class="sidebar-nav">
						<a id="menu-close" href="#"
							class="btn btn-light btn-lg pull-right toggle"><i
							class="fa fa-times"></i></a>
						<li class="sidebar-brand"><a href="/login" onclick=$("#menu-close").click(); >XXAlimi</a></li>
						<li><a href="/user/home" onclick=$("#menu-close").click(); >Home</a></li>
						<li><a href="/add/searchPage" onclick=$("#menu-close").click(); >Add+</a></li>
						<li><a href="/user/#top" onclick=$("#menu-close").click(); >Recommand</a></li>
						<li><a href="/user/home/mindmap" onclick=$("#menu-close").click(); >MindMapUI<strong>(Experimental)</strong></a></li>
						<li><a href="/keyword/addKeywordView" onclick=$("#menu-close").click(); >Keyword</a></li>
						<li><a href="/user/#top" onclick=$("#menu-close").click(); >Setting</a></li>
					</ul>
					</nav>
					Hello! ${ pageContext.request.userPrincipal.name} </br>
					<a href="${pageContext.request.contextPath}/logout">LOGOUT</a>
					</br>
					<script type="text/javascript">
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
				</c:if>
			</div>
		</div>
	</div>
	</header>
	<script src="/webjars/jquery/2.2.2/dist/jquery.min.js"></script>
	<script src="/webjars/bootstrap/3.3.6/dist/js/bootstrap.min.js"></script>
</body>
</html>