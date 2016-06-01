<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
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
<script type="text/javascript" src="/js/notification.js"></script>
<title>Welcome to XXAlimi</title>
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		<!-- Navigation -->
		<%@include file="/WEB-INF/view/MenuBar.jsp"%>
	</sec:authorize>

	<%-- <c:if test="${ not empty pageContext.request.userPrincipal }"></c:if> --%>

	<!-- Header -->
	<header id="top" class="header">

	<div class="text-vertical-center">
		<h1>XXAlimi</h1>
		<h3>ver 0.1.0 for Team AnonyBlah</h3>
		<br>

		<div class="row">
			<div class="col-xs-4 col-xs-offset-4">
				
				<c:url value="/login" var="loginUrl" />
				<sec:authorize access="isAnonymous()">
					

					<form name="loginSuccess" action="/login" class="form-signin"
						method="post">

						<c:if test="${param.error != null}">
							<font color="red"">
								Login ERROR!</br>
								Check your E-mail or Password
								<%-- </br>
								<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != NULL}">
								MESSAGE: <c:out
										value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
								</c:if> --%>
							</font>
														
						</c:if>
						<input type="text" class="form-control input-lg" id="username" name="username" placeholder="Email Address" required=""
							autofocus="" /> 
						<input type="password" class="form-control input-lg" name="password" placeholder="Password" required="" /> <label class="checkbox">
						<input type="checkbox" name="remember-me" value="true" checked="checked"> Remember me
						</label>
						<button class="btn btn-lg btn-primary btn-block" type="submit">Login!</button>
						<!-- <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
					</fb:login-button> -->
					</form>
					<br />
					<form action="/register" method="get">
						<!-- <button class="btn btn-lg btn-block btn-social btn-facebook"
							onclick="fb_login();">
							<i class="fa fa-facebook"></i> Sign in with Facebook
						</button> -->
						<!-- 				<fb:login-button class="btn btn-lg btn-block btn-social btn-facebook" scope="public_profile,email" onlogin="checkLoginState();">
					Sign in with Facebook
				</fb:login-button> -->
						<h4>
							If you don't have account? <br> Don't worry
						</h4>
						<a href="/register/form" class="btn btn-success btn-lg btn-block">Register
							now!</a>
					</form>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					Hello! ${ pageContext.request.userPrincipal.name} </br>
					<a href="${pageContext.request.contextPath}/logout"><h3>LOGOUT</h3></a>
					</br>
				</sec:authorize>
			</div>
		</div>
	</div>
	</header>
	<jsp:include page="/WEB-INF/view/user/Tail.jsp" />
	<script src="/webjars/jquery/2.2.2/dist/jquery.min.js"></script>
	<script src="/webjars/bootstrap/3.3.6/dist/js/bootstrap.min.js"></script>
	<script>
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

</body>
</html>