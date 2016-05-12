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

</head>
<body>
	<!-- Navigation -->
	<%@include file="/WEB-INF/view/MenuBar.jsp"%>

	<aside class="homebg">
	<div class="text-vertical-left">
		<h1>My Feeds</h1>
		<p>개의 읽지 않은 글들이 있습니다.</p>
	</div>
	<div class="text-horizontal-right">

		USER ID : ${ pageContext.request.userPrincipal.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="${pageContext.request.contextPath}/logout"> <strong>LOGOUT</strong>
		</a> </br>

	</div>

	</aside>
	</br>
	</br>
	</br>
	<div class="container">
		<div class="row">
			<ul class="list-group">
				<c:forEach var="feed" items="${feedList}">
					<li class="list-group-item">
						<div class="media col-sm-2">
							<figure class="pull-left"> <img
								class="media-object img-rounded img-responsive"
								src="${feed.imageUrl}" alt=""> </figure>

						</div>
						<div class="col-sm-9">
							<h3 class="list-group-item-heading">
								<strong>${feed.title}</strong>
							</h3>
							<hr />
							<ul>
								<c:set var="i" value="0" />
								<c:set var="doneLoop" value="false" />
								<c:forEach var="article" items="${articleList}"
									varStatus="status">
									<c:if test="${not doneLoop}">
										<c:if test="${feed.link == article.feedLink}">
											<c:set var="i" value="${i + 1}" />
											<li><a href="${article.articleLink}">${article.articleTitle}</a></li>
											<c:if test="${i == 3}">
												<c:set var="doneLoop" value="true" />
											</c:if>
										</c:if>
									</c:if>
								</c:forEach>
							</ul>
						</div>
						<div class="col-sm-1">
							<a href="/home/feed/${feed.title}" class="btn-lg" role="button">
								<i class="glyphicon glyphicon-new-window"></i>
							</a>

							<button class="btn btn-lg btn-warning delete-feed"
								data-feed-id="${feed.title}">
								<span class="glyphicon glyphicon-trash"></span>&nbsp;
							</button>
						</div>
					</li>
				</c:forEach>
			</ul>

		</div>
	</div>

	<jsp:include page="Tail.jsp" />
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
        startWebSocket();
    });
    $('button.delete-feed').click(function() {
        var feedId = $(this).attr("data-feed-id");
        deletePhoto(feedId);
      });

      function deletePhoto(feedId) {
        swal({
          title: "Are you sure?", 
          text: "Are you sure that you want to delete this feed?", 
          type: "warning",
          showCancelButton: true,
          closeOnConfirm: false,
          confirmButtonText: "Yes, delete it!",
          confirmButtonColor: "#ec6c62"
        }, function() {
          $.ajax({
            url: "/delete/feed/" + feedId,
            type: "DELETE"
          })
          .done(function(data) {
            swal("Deleted!", "Your file was successfully deleted!", "success");
          })
          .error(function(data) {
            swal("Oops", "We couldn't connect to the server!", "error");
          });
        });
      }
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