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
	document.getElementById("searchResultModalTitle").innerHTML = "<i class=\"glyphicon glyphicon-search\"/> \"" +searchText + "\"의 검색 결과";
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
<title>Insert title here</title>
</head>
<body>
	<!-- Navigation -->
	<a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i
		class="fa fa-bars"></i></a>
	<nav id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<a id="menu-close" href="#"
			class="btn btn-light btn-lg pull-right toggle"><i
			class="fa fa-times"></i></a>
		<li class="sidebar-brand"><a href="#top" onclick=$("#menu-close").click(); >XXAlimi</a></li>
		<li><a href="/home" onclick=$("#menu-close").click(); >Home</a></li>
		<li><a href="/add/searchPage" onclick=$("#menu-close").click(); >Add+</a></li>
		<li><a href="#top" onclick=$("#menu-close").click(); >Recommand</a></li>
		<li><a href="/home/mindmap" onclick=$("#menu-close").click(); >MindMapUI<strong>(Experimental)</strong></a></li>
		<li><a href="#top" onclick=$("#menu-close").click(); >Setting</a></li>
	</ul>
	</nav>
	<aside class="searchbg" style="padding:15px">
	<div class="text-vertical-center">
	<h1>RSS Feed 추가하기</h1>
	<p>
		검색을 통해 RSS Feed를 선택하여 추가하거나 <br /> 직접 URL을 입력해서 Feed를 추가하세요!
	</p>
	<p><br /></p>
	<p>
	<div class="row">
    	<div class="col-sm-6 col-sm-offset-3">
			<form method="post" onsubmit="return onSubmit()" class="form-horizontal">
				<div class="input-group">
					<input id="input" type="text" class="form-control"
						placeholder="검색어 입력..."> <span class="input-group-btn">
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
	<p></p>
	<p>
	<div class="row">
    	<div class="col-sm-6 col-sm-offset-3">
			<form action="/add/save" name=insertForm method="post">
				<div class="input-group">
					<input type="url" id="feedUrl" name=feedUrl class="form-control"
						placeholder="URL 입력..." required> <span class="input-group-btn">
						<button id="saveBtn" title="Click this to Save!" class="btn btn-default hover-tooltip" type="submit">Save!</button>
					</span>
				</div>
				<!-- /input-group -->
			</form>
		</div>
		<!-- /.col-lg-6 -->
	</div>
	<!-- /.row -->
	</p>
	</div>
	</aside>
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
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- <form action="/add/save" name=insertForm method=get>
		<table border=0 width=600 align=center>
			<tr>
				<td align=right>URL :</td>
				<td><input type=text name=feedUrl size=30 maxlength=200></td>
				<td><input type="submit" value="저장"></td>
			</tr>

		</table>
	</form> -->
	<script src="/webjars/jquery/2.2.2/dist/jquery.min.js"></script>
	<script src="/webjars/bootstrap/3.3.6/dist/js/bootstrap.min.js"></script>
	<script>
	$('#searchResultModal').on('shown.bs.modal', function () {
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

</body>
</html>