<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="${pageContext.request.contextPath }/js/jquery/jquery.min.js"></script>
<link
	href="${pageContext.request.contextPath }/css/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath }/js/bootstrap/bootstrap.min.js"></script>
<title>三峡大学计算机协会</title>
</head>

<script type="text/javascript">
	$(function() {
		$("#words").click(function() {
			document.getElementById("towords").click();
		});
		$("#softs").click(function() {
			document.getElementById("tosofts").click();
		});
		$("#comments").click(function(){
			document.getElementById("tocomments").click();
		});
		$("[data-toggle='tooltip']").tooltip();
	});
</script>

<style>
* {
	margin: 0px;
	padding: 0px;
	font-family: '微软雅黑';
}
section {
	width:1260px;
	margin:0px auto;
	height: 600px;
	text-align: center;
	background: url(${pageContext.request.contextPath }/img/resource.jpg);
	background-repeat: no-repeat;
	background-size:cover;
	overflow:hidden;
}
section>button{
	margin-top:200px;
	margin-right:40px;
}

</style>

<body>
	<!-- 隐藏的链接，用于页面跳转 -->
	<a id="towords"
		href="${pageContext.request.contextPath }/resource/words"
		target="_blank" style="display: none;"> </a>
	<a id="tosofts"
		href="${pageContext.request.contextPath }/resource/softs"
		target="_blank" style="display: none;"> </a>
	<a id="tocomments"
		href="${pageContext.request.contextPath }/answer/answer"
		target="_blank" style="display: none;"> </a>

	<!-- top -->
	<%@ include file="/WEB-INF/top-bot/top.jsp"%>
	<!-- top end -->
	<!-- content -->
	<section>
		<button type="button" class="btn btn-info btn-lg"  id="words" data-toggle="tooltip" data-placement="top" title="c、c++" >编程学习教程</button>   
		<button type="button" class="btn btn-info btn-lg"	id="softs"  data-toggle="tooltip" data-placement="top" title="c/c++、java">编程软件下载</button>
		<button type="button" class="btn btn-info btn-lg"	id="comments"  data-toggle="tooltip" data-placement="top" title="你的问题我们一起解决">协会论坛</button>   
	</section>

	<!-- content end-->
	<!-- bottom  -->
	<%@ include file="/WEB-INF/top-bot/bottom.jsp"%>
	<!--bottom end  -->
</body>
</html>