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
<title>Sign Up</title>
</head>
<body>
	<aside class="register">
	<div class="text-vertical-left">
		<div class="row">
			<div class="col-xs-6 col-xs-offset-3">
				<div class="well"><h1><Strong>Register</Strong></h1>
				<hr class="double-dashed">
				<form class="form-horizontal" action="/register" method="POST">
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="username">Username</label>
							<div class="controls">
								<input type="text" id="username" name="username" placeholder=""
									class="form-control input-lg">
								<p class="help-block">Username can contain any letters or
									numbers, without spaces</p>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="email">E-mail</label>
							<div class="controls">
								<input type="email" id="email" name="email" placeholder=""
									class="form-control input-lg">
								<p class="help-block">Please provide your E-mail</p>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="password">Password</label>
							<div class="controls">
								<input type="password" id="password" name="password"
									placeholder="" class="form-control input-lg">
								<p class="help-block">Password should be at least 6
									characters</p>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="password_confirm">Password
								(Confirm)</label>
							<div class="controls">
								<input type="password" id="password_confirm"
									name="password_confirm" placeholder=""
									class="form-control input-lg">
								<p class="help-block">Please confirm password</p>
							</div>
						</div>

						<div class="control-group">
							<!-- Button -->
							<div class="controls">
								<button class="btn btn-success" type="submit">Register</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			</div>
		</div>

	</div>
	</aside>
	<script src="/webjars/jquery/2.2.2/dist/jquery.min.js"></script>
	<script src="/webjars/bootstrap/3.3.6/dist/js/bootstrap.min.js"></script>
</body>
</html>