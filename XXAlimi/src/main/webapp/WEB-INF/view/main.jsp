<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
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
				<form name="loginSuccess" action="/login/success" class="form-signin" method="post">
					<input type="text" class="form-control input-lg" name="username"
						placeholder="Email Address" required="" autofocus="" />
					<input type="password" class="form-control input-lg" name="password"
						placeholder="Password" required="" /> <label class="checkbox">
					<input type="checkbox" value="remember-me" id="rememberMe"
						name="rememberMe"> Remember me
					</label>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Login!</button>
					<!-- <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
					</fb:login-button> -->
				</form>
				<br/>
				<button class="btn btn-lg btn-block btn-social btn-facebook" onclick="fb_login();">
						<i class="fa fa-facebook"></i> Sign in with Facebook
				</button>
<!-- 				<fb:login-button class="btn btn-lg btn-block btn-social btn-facebook" scope="public_profile,email" onlogin="checkLoginState();">
					Sign in with Facebook
				</fb:login-button> -->
				<h4>
					If you don't have account? <br> Don't worry
				</h4>
				<a href="/login/register" class="btn btn-success btn-lg btn-block">Register now!</a>
			</div>
		</div>
	</div>
	</header>
	<script src="/webjars/jquery/2.2.2/dist/jquery.min.js"></script>
	<script src="/webjars/bootstrap/3.3.6/dist/js/bootstrap.min.js"></script>
</body>
</html>