<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="col-lg-10 col-lg-offset-1 text-center">
		<c:forEach var="feed" items="${feedList}">
			<form action="/keyword/addKeyword/${feed.title}" method="post"
				name="addKeyword">

				<h2>${feed.title}</h2>
				<input type="hidden" id="feedLink" name="feedLink" value="${feed.link}">
				<input type="hidden" id="feedtitle" name="feedtitle" value="${feed.title}">
				<c:if test="true"/>
				<c:forEach var="keyword" items="${keywordList}">
					<c:if test="${feed.title == keyword.title}">
						<h3>${keyword.keyword}</h3>
						<%-- <input type="text" id="feedtitle" name="feedtitle" value="${feed.title}"> --%>
						
					</c:if>
				</c:forEach>
				<input type="text" id="keyword" name="keyword">
				<button class="btn btn-default" type="submit">Save!</button>
			</form>
		</c:forEach>
	</div>


</body>
</html>