<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage - XXAlimi ver 0.0.1</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/3.3.6/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/webjars/bootstrap-sweetalert/0.4.5/lib/sweet-alert.css">
<link rel="stylesheet" href="/css/stylish-portfolio.css">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">
<script src="/webjars/sockjs-client/1.0.3/dist/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/2.3.4/lib/stomp.min.js"></script>
<script type="text/javascript" src="/js/notification.js"></script>

<script type="text/javascript">
	/* function deleteFeed() {
		swal({
			title : "Are you sure?",
			text : "You will not be able to recover this Feed!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Yes, delete it!",
			cancelButtonText : "No, cancel plx!",
			closeOnConfirm : false,
			closeOnCancel : false,
		}, function(isConfirm) {

			if (isConfirm) {
				document.forms["deleteForm"].submit();

			} else {
				swal("Cancelled", "피드 삭제가 취소되었습니다", "error");
			}
		});
	} */
	function setModalTitle() {
		var searchText = document.getElementById("feedName").value;
		document.getElementById("feedTitleModal").innerHTML = "<i class=\"glyphicon glyphicon-search\"/> \""
				+ searchText + "\"의 검색 결과";
	}
	function confirmDrop() {
		swal({
			title : "Are you sure?",
			text : "You will not be able to recover this Account!!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Yes, delete it!",
			cancelButtonText : "No, cancel plx!",
			closeOnConfirm : false,
			closeOnCancel : false,
		}, function(isConfirm) {

			if (isConfirm) {
				document.forms["leaveAlimi"].submit();

			} else {
				swal("Cancelled", "계정 삭제가 취소되었습니다", "error");
			}
		});

	}
</script>


</head>
<body class="homebg1">
	<!-- Navigation -->
	<%@include file="/WEB-INF/view/MenuBar.jsp"%>

	<aside class="homebg">
	<div class="text-vertical-left col-md-8 col-md-offset-2">
		<br> <br>
		<h1>My Feeds</h1>
		<h4>등록한 피드를 확인하세요.</h4>
	</div>

	<div class="col-md-offset-7">

		<form name="leaveAlimi" action="/leave" method="post">
			USER ID : ${ pageContext.request.userPrincipal.name}&nbsp;&nbsp; <a
				href="${pageContext.request.contextPath}/logout" type="button"
				class="btn btn-link"> <strong>LOGOUT</strong>
			</a> <input type="hidden" id="email" name="email"
				value="${pageContext.request.userPrincipal.name}">
			<button type="button" class="btn btn-link" onclick="confirmDrop()">
				<strong>회원탈퇴</strong>
			</button>
		</form>

	</div>
	</aside>

	</br>
	</br>

	<div class="col-md-8 col-md-offset-2">
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<c:set var="buttonIndex" value="0" />
			<c:forEach var="feed" items="${feedList}">
				<div class="panel panel-default">

					<div class="panel-heading" role="tab" id="heading${buttonIndex}">
						<h4 class="panel-title">
							<a role="button" data-toggle="collapse" data-parent="#accordion"
								href="#${buttonIndex}" aria-expanded="true"
								aria-controls="${buttonIndex}"> <strong
								class="col-md-offset-1">${feed.title}</strong> <span
								class="glyphicon glyphicon-menu-down pull-left"
								aria-hidden="true"></span>
							</a>
							<%-- <form name="deleteForm" action="/home/feedDelete" method="post">
                     <input type="hidden" id="feedUrl" name="feedUrl"
                           value="${feed.link}" /> 
                     <a href class="pull-right" data-toggle="modal" data-target="#myModal">
                           <strong><span class="glyphicon glyphicon-remove"></span></strong>
                        </a>
                     <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                           aria-labelledby="myModalLabel">
                           <div class="modal-dialog" role="document">
                              <div class="modal-content">
                                 <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                       aria-label="Close">
                                       <span aria-hidden="true">&times;</span>
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel">Confirm for
                                       Delete</h4>
                                 </div>
                                 <div class="modal-body">Are you sure that you want to
                                    delete this feed?</div>
                                 <div class="modal-footer">
                                    <button type="button" class="bin ban-default"
                                       data-dismiss="modal">Cancel</button>
                                    <button type="submit" class="bin ban-primary">Delete</button>
                                 </div>
                              </div>
                           </div>
                        </div>
                     </form> --%>
							<a href="/delete/feed/${feed.title}" class="pull-right"> <strong><span
									class="glyphicon glyphicon-remove" data-toggle="modal"
									data-target="#myModal" aria-hidden="true"></span></strong>
							</a>
						</h4>

					</div>
					<div id="${buttonIndex}" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="heading${buttonIndex}">
						<div class="panel-body">
							<div class="col-md-1">
								<figure class="pull-left"> <img
									class="media-object img-rounded img-responsive"
									src="${feed.imageUrl}" alt=""> </figure>
							</div>

							<a href="/home/feed/${feed.title}"><h6 class="pull-right">기사
									더보기</h6></a> </br> </br>

							<!-- <form method="post" onsubmit="return onSubmit()"> -->
							<div class="pull-right">
								<h2>
									<button class="glyphicon glyphicon-list-alt" type="submit"
										data-toggle="modal" data-toggle="tooltip"
										data-target="#keywordModal">
										
										<input type="hidden" id="feedName" value="${feed.title}" />
										
									</button>
								</h2>
							</div>
							<!-- </form> -->

							<div class="col-md-10">
								<c:set var="i" value="0" />
								<c:set var="doneLoop" value="false" />
								<c:forEach var="article" items="${articleList}"
									varStatus="status">
									<c:if test="${not doneLoop}">
										<c:if test="${feed.link == article.feedLink}">
											<c:set var="i" value="${i + 1}" />
											<li><a href="${article.articleLink}">${article.articleTitle}</a></li>
											<c:if test="${i == 5}">
												<c:set var="doneLoop" value="true" />
											</c:if>
										</c:if>
									</c:if>
								</c:forEach>
							</div>


						</div>
					</div>
					<c:set var="buttonIndex" value="${buttonIndex + 1}" />
				</div>
			</c:forEach>

		</div>
	</div>

	</div>


	<div class="modal fade" id="keywordModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="feedTitleModal">""의 키워드</h4>
				</div>
				<div class="modal-body">
					<div id="feedControl" class="list-group"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<script src="/webjars/jquery/2.2.2/dist/jquery.min.js"></script>
	<script src="/webjars/bootstrap/3.3.6/dist/js/bootstrap.min.js"></script>
	<script src="/webjars/bootstrap-sweetalert/0.4.5/lib/sweet-alert.js"></script>
	<!-- Custom Theme JavaScript -->
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
		$(function() {
			$(".limit").each(function(i) {
				len = $(this).text().length;
				if (len > 50) {
					$(this).text($(this).text().substr(0, 50) + '...');
				}
			});
		});
		$(document).ready(function() {
			equalHeight($(".thumbnail"));
			startWebSocket();
		});

		/* $('#keywordModal').on('shown.bs.modal', function() {
			setModalTitle();
		}) */
	</script>
	<br>
	<br>
	<jsp:include page="/WEB-INF/view/user/Tail.jsp" />
</body>
</html>