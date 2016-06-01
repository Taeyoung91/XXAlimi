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
<script src="/webjars/bootstrap-sweetalert/0.4.5/lib/sweet-alert.js"></script>
<link rel="stylesheet"
	href="/webjars/bootstrap-sweetalert/0.4.5/lib/sweet-alert.css">
<script type="text/javascript">
	function confirmInfo() {
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var password_confirm = document.getElementById("password_confirm").value;
		var email = document.getElementById("email").value;
		var emailList = document.getElementById("emailList").value;

		var tempEmailList = emailList.substring(1, emailList.length - 1);
		var realEmailList = tempEmailList.split(', ');

		for (var i = 0; i < realEmailList.length; i++) {
			if (email === realEmailList[i]) {
				sweetAlert("Error", "Email이 이미 존재합니다!", "error");
				return false;
			}
		}

		if (username.length == 0) {
			sweetAlert("Error", "이름을 입력하세요!", "error");
			return false;
		}
		if (email.length == 0) {
			sweetAlert("Error", "Email을 입력하세요!", "error");
			return false;
		}
		if (password.length < 6) {
			sweetAlert("Error", "패스워드는 6자리 이상입니다!", "error");
			return false;
		}

		if (password != password_confirm) {
			sweetAlert("Error", "입력하신 비밀번호가 일치하지 않습니다!", "error");
			return false;

		} else {
			return true;

		}
	}
	function confirmEmail() {
		var email = document.getElementById("email").value;
		var emailList = document.getElementById("emailList").value;

		var tempEmailList = emailList.substring(1, emailList.length - 1);
		var realEmailList = tempEmailList.split(', ');
		var isDuplicated = false;
		
		if (email.length == 0) {
			sweetAlert("Error", "Email을 입력하세요!", "error");
			return;
		}
		
		for (var i = 0; i < realEmailList.length; i++) {
			if (email === realEmailList[i]) {
				isDuplicated = true;
			}
		}
		if (isDuplicated) {
			sweetAlert("이미 존재하는 Email입니다");
		} else {
			sweetAlert("사용가능한 Email입니다");
		}

	}
</script>
</head>
<body>
	<aside class="register">
	<div class="text-vertical-left">
		<div class="row">
			<div class="col-xs-6 col-xs-offset-3">
				<div class="well">
					<h1>
						<Strong>Register</Strong>
					</h1>
					<hr class="double-dashed">
					<form name="registerForm" class="form-horizontal"
						action="/register/success" onsubmit="return confirmInfo()"
						method="POST">

						<input type="hidden" id="emailList" value="${emailList}" />
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
									<p class="help-block">
										Please provide your E-mail
										<button type="button" class="btn btn-primary btn-xs"
											onclick="confirmEmail()">중복검사</button>
									</p>

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
									<input type="hidden" name="role" value="ROLE_USER" />
									<button class="btn btn-success" type="submit">Register</button>
									<a href="/home" class="btn btn-danger" role="button">Back</a>
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
	<script>
		
	</script>

</body>
</html>