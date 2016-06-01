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
<script src="http://www.google.com/jsapi" type="text/javascript"></script>
<script src="/js/feedSearchApi.js" type="text/javascript"></script>
<script>
	function setModalTitle() {
		var searchText = document.getElementById("input").value;
		document.getElementById("searchResultModalTitle").innerHTML = "<i class=\"glyphicon glyphicon-search\"/> \""
				+ searchText + "\"의 검색 결과";
	}
</script>
<link rel="stylesheet"
	href="/webjars/bootstrap/3.3.6/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/stylish-portfolio.css">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/notification.js"></script>
<title>Insert title here</title>
</head>
<body class="searchbg1">
	<!-- Navigation -->
	<%@include file="/WEB-INF/view/MenuBar.jsp"%>
	<aside class="searchbg">
	<div class="text-vertical-center">
		<div class="text-vertical-left col-md-8 col-md-offset-2">
			<h1>RSS Feeds 추가하기</h1>
			<h4>
				검색을 통해 RSS Feed를 선택하여 추가하거나 <br /> 직접 URL을 입력해서 Feed를 추가하세요!
			</h4>
		</div>

		<div class="col-md-offset-7">

			<strong>${ pageContext.request.userPrincipal.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>
			<a href="${pageContext.request.contextPath}/logout"> <strong>LOGOUT&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>
			</a> </br>

		</div>

		</br> <br />
		</p>
		<p>

			<!-- /.row -->
		</p>
	</div>
	</aside>
	<br>
	<br>
	<div class="row">
		<div class="col-sm-6 col-sm-offset-3">
			<form method="post" onsubmit="return onSubmit()"
				class="form-horizontal">
				<div class="input-group">
					<input id="input" type="text" class="form-control"
						placeholder="검색어 입력..."> 
						<span class="input-group-btn">
							<button class="btn btn-default" type="submit" data-toggle="modal"
							data-toggle="tooltip" data-target="#searchResultModal">Search!</button>
						</span>
				</div>
				<!-- /input-group -->
			</form>
		</div>
		<!-- /.col-lg-6 -->
	</div>
	<!-- /.row -->
	</p>

	<div class="row">
		<div class="col-sm-6 col-sm-offset-3">
			<form action="/add/save" name=insertForm method="post">
				<div class="input-group">
					<input type="url" id="feedUrl" name=feedUrl class="form-control"
						placeholder="URL 입력..." required> <span
						class="input-group-btn">
						<button id="saveBtn" title="Click this to Save!"
							class="btn btn-default hover-tooltip" type="submit">Save!</button>
					</span>
				</div>
				<!-- /input-group -->
			</form>
		</div>
		<!-- /.col-lg-6 -->
	</div>

	<p>
		<div class="row">
		<div class="col-sm-6 col-sm-offset-3">
				<form action="/add/naver/save" name="naverForm" method="post">
					<div class="input-group">
						<input type="text" id="naverWord" name="naverWord"
						class="form-control" placeholder="NAVER 뉴스 검색.." required>

						<span class="input-group-btn">
							<button id="saveBtn" title="Click this to Save!"
							class="btn btn-default hover-tooltip" type="submit">Save!</button>
						</span>
					</div>
					<!-- /input-group -->
				</form>
			</div>
			<!-- /.col-lg-6 -->
		</div>
		<!-- /.row -->
	</p>
   <!-- Modal -->
	<div class="modal fade" id="searchResultModal" tabindex="-1"
		role="dialog">
      <div class="modal-dialog" role="document">
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
                  <span aria-hidden="true">&times;</span>
               </button>
               <h4 class="modal-title" id="searchResultModalTitle">""의 검색 결과</h4>
            </div>
            <div class="modal-body">
               <div id="feedControl" class="list-group"></div>
            </div>
            <div class="modal-footer">
               <button type="button" class="btn btn-default"
						data-dismiss="modal">Close</button>
            </div>
         </div>
      </div>
   </div>
   
   <script src="/webjars/jquery/2.2.2/dist/jquery.min.js"></script>
   <script src="/webjars/bootstrap/3.3.6/dist/js/bootstrap.min.js"></script>
   <script>
				$('#searchResultModal').on('shown.bs.modal', function() {
					setModalTitle();
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