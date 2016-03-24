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
<link rel="stylesheet"
	href="css/stylish-portfolio.css">
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
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
		<li class="sidebar-brand"><a href="#top" onclick=$("#menu-close").click(); >Start
				Bootstrap</a></li>
		<li><a href="#top" onclick=$("#menu-close").click(); >Home</a></li>
	</ul>
	</nav>

	<!-- Header -->
	<header id="top" class="header">
	<div class="text-vertical-center">
		<h1>XXAlimi</h1>
		<h3>ver 0.0.1 for Team AnonyBlah</h3>
		<br> <a href="/home/refreshFeed" class="btn btn-dark btn-lg">Refresh RSS</a>
	</div>
	</header>

	<div class="container">
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1 text-center">
				<c:forEach var="feed" items="${feedList}">
					<div class="feed-preview">
						<a href="/home/${feed.title}">
							<h2 class="post-title">${feed.title}</h2>
						</a>
						<p class="post-meta">
							Copyright <a href="#">${feed.copyright}</a> updated on
							${feed.pubDate}
						</p>
					</div>
					<hr>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1 text-center">
                    <h4><strong>XXAlimi</strong>
                    </h4>
                    <p>ver 0.0.1<br>Team AnonyBlah</p>
                    <hr class="small">
                    <p class="text-muted">Copyright &copy; Hansung Univ. Capstone Design 2016</p>
                </div>
            </div>
        </div>
    </footer>


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
    // Scrolls to the selected menu item on the page
    $(function() {
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
    });
    </script>
</body>
</html>