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
<link rel="stylesheet" href="css/stylish-portfolio.css">
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/view/Header.jsp" />

	<form action="/add/save" name=insertForm method=get>
		<table border=0 width=600 align=center>
			<tr>
				<td align=right>URL :</td>
				<td><input type=text name=feedUrl size=30 maxlength=200></td>
				<td><input type="submit" value="저장"></td>
			</tr>

		</table>
	</form>

	<!-- <footer>
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
	</footer> -->
	<jsp:include page="/WEB-INF/view/Tail.jsp" />
	

</body>
</html>