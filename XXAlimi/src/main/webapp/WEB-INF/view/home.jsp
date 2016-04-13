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
<link rel="stylesheet" href="/css/stylish-portfolio.css">
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/notification.js"></script>
<script type="text/javascript" >
function equalHeight(group) {    
    tallest = 0;    
    group.each(function() {       
        thisHeight = $(this).height();       
        if(thisHeight > tallest) {          
            tallest = thisHeight;       
        }    
    });    
    group.each(function() { $(this).height(tallest); });
} 
</script>
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
		<li><a href="#top" onclick=$("#menu-close").click(); >Setting</a></li>
	</ul>
	</nav>

	<aside class="homebg">
		<div class="text-vertical-left">
			<h1>My Feeds</h1>
			<p>
				개의 읽지 않은 글들이 있습니다.
			</p>
		</div>
	</aside>

	<c:if test="${feedList[1] == null}">
		<button id="content" value="${feedList[0].title}" onclick="notifyMe()">Refresh</button>
	</c:if>
	<c:if test="${feedList[1] != null }">
		<% int i = 0; %>
		<c:forEach var="feed" items="${feedList}">
			<% i++; %>
		</c:forEach>
		<button id="content" value="${feedList[0].title} 외 <%= i-1%>개" onclick="notifyMe()">Refresh</button>
	</c:if>

	

	<div class="container">
		<div class="row" >
				<ul class="list-group">
					<c:forEach var="feed" items="${feedList}">
						<li class="list-group-item">
							<div class="media col-sm-2">
                    			<figure class="pull-left">
                       				<img class="media-object img-rounded img-responsive"  src="${feed.image.url}" alt="Feed Site Image">
                    			</figure>
                			</div>
                			<div class="col-sm-9">
                    			<h3 class="list-group-item-heading"><strong>${feed.title}</strong></h3>
                    			<hr/>
                    			<ul>
									<c:forEach var="article" items="${feed.entries}" end="2">
										<li><a href="${article.link}">${article.title}</a></li>
									</c:forEach>
								</ul>
							</div>
							<div class="col-sm-1">
								
										<a	href="/home/feed/${feed.title}" class="btn-lg" role="button">
											<i class="glyphicon glyphicon-new-window"></i>
										</a>
								
										<a href="#" class="btn-lg" role="button">
											<i class="glyphicon glyphicon-trash"></i>
										</a>
							
							</div>
                		</li>
                	</c:forEach>
                </ul>

		</div>
	</div>

	<%-- <div class="container">
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1 text-center">
				<ul class="media-list">
					<c:forEach var="feed" items="${feedList}">
						<li class="media">
						<div class="media-left">
							<img id="extImg" class="media-object" src="${feed.image.url}" alt="Feed Image">
						</div>
						<div class="media-body">
							<a href="/home/feed/${feed.title}">
							<h4 class="post-title">${feed.title}</h4>
							</a>
							<p class="post-meta">updated on ${feed.publishedDate}</p>
							<c:forEach var="article" items="${feed.entries}" end="2">
								<div class="media">
<!-- 									<img id="extImg" class="media-object" src="/img/Team Logo.png" alt="Article Image"> -->
									<div class="media-body">
										<h3>${article.title}</h3>
										<h5>posted by ${article.author}</h5>
										<p class="limit" id="description">${article.description.value}</p>
										<a class="btn btn-info" href="${article.link}">Go to read!</a>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div> --%>
	

	
	<jsp:include page="Tail.jsp" />
	<script src="/webjars/jquery/2.2.2/dist/jquery.min.js"></script>
	<script src="/webjars/bootstrap/3.3.6/dist/js/bootstrap.min.js"></script>
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
    $(function(){
    	$(".limit").each(function(i){
    		len=$(this).text().length;
    		if(len>50) {
    			$(this).text($(this).text().substr(0,50)+'...');
    			}
    		});       
    	});
    $(document).ready(function() {   
        equalHeight($(".thumbnail")); 
    });
    // Scrolls to the selected menu item on the page
    /* $(function() {
        $('a[href*=#]:not([href=#])').click(function() {
            if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') || location.hostname == this.hostname) {
                var target = $(this.hash);
                target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                if (target.length) {
                    $('html,body').animate({
                        scrollTop: target.offset().top
                    }, 1000);
                    return false;
                }
            }
        });
    }); */
    </script>
</body>
</html>