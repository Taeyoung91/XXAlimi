<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i
		class="fa fa-bars"></i></a>
	<nav id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<a id="menu-close" href="#" class="btn btn-light btn-lg pull-right toggle"><i
			class="fa fa-times"></i></a>
		<li class="sidebar-brand"><a href="/login" onclick=$("#menu-close").click(); >XXAlimi</a></li>
		<li><a href="/user/home" onclick=$("#menu-close").click(); >Home</a></li>
		<li><a href="/add/searchPage" onclick=$("#menu-close").click(); >Add+</a></li>
		<li><a href="/user/#top" onclick=$("#menu-close").click(); >Recommand</a></li>
		<li><a href="/user/home/mindmap" onclick=$("#menu-close").click(); >MindMapUI<strong>(Experimental)</strong></a></li>
		<li><a href="/keyword/addKeywordView" onclick=$("#menu-close").click(); >Keyword</a></li>
		<li><a href="/user/#top" onclick=$("#menu-close").click(); >Setting</a></li>
	</ul>
	</nav>
</body>
</html>