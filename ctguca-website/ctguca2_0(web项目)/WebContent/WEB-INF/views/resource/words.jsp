<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="${pageContext.request.contextPath }/js/jquery/jquery.min.js"></script>
<link
	href="${pageContext.request.contextPath }/css/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath }/js/bootstrap/bootstrap.min.js"></script>

<title>三峡大学计算机协会</title>
</head>
<style>
.top {
	height: 20%;
	border-bottom: 3px solid gray;
	text-align: left;
}

#top-title {
	margin-left: 50px;
	font-size: 35px;
	font-family: Times New Roman;
	color: #487b83;
}

.content {
	padding: 20px;
}

.con-dis-bod-title {
	font-size: 20px;
	font-family: 微软雅黑;
	color: black;
	text-align: center;
	margin: 10px 0;
}

.con-dis-bod-int1 {
	font-size: 12px;
	color: gray;
	margin-bottom: 5px;
}

.con-dis-bod-int2 {
	font-size: 16px;
	color: black;
	font-family: 微软雅黑;
	margin-bottom: 10px;
}
</style>
<script type="text/javascript">
	/* 关闭所有的下载面板 */
	function closeOther(){
		$("#lists-c-words-display").hide();
		$("#lists-c-video-display").hide();
		
		$("#lists-c2-words-display").hide();
		$("#lists-c2-video-display").hide();
		
	}
	
	$(function() {
		/* c */
		$("#lists-c").click(function() {
			$("#lists-c-well").toggle();		
			return false;
		});
		$("#lists-c-words").click(function() {
			closeOther();
			$("#lists-c-words-display").show();
			return false;
		});
		$("#lists-c-video").click(function() {
			closeOther();
			$("#lists-c-video-display").show();
			return false;
		});
		
		/* c++ */
		$("#lists-c2").click(function() {
			$("#lists-c2-well").toggle();		
			return false;
		});
		$("#lists-c2-words").click(function() {
			closeOther();
			$("#lists-c2-words-display").show();
			return false;
		});
		$("#lists-c2-video").click(function() {
			closeOther();
			$("#lists-c2-video-display").show();
			return false;
		});

	});
</script>
<body>
	<!-- top -->
	<div class="top">
		<a href="" id="top-title" style="text-decoration: none;">CTGUCA&nbsp;|教程</a>
	</div>
	<!-- top end-->

	<!-- content -->
	<div class="content">
		<!-- 内容左侧列表组 -->
		<div class="list-group" style="float: left; width: 20%;">
			<button class="list-group-item active" style="font-family: 微软雅黑">教程列表</button>

			<!-- c列表 -->
			<button id="lists-c" class="list-group-item"
				style="font-family: 微软雅黑">C语言</button>
			<div id="lists-c-well" style="display: none;">
				<button id="lists-c-words"
					class="list-group-item list-group-item-warning"
					style="font-family: 微软雅黑">文字版教程</button>
				<button id="lists-c-video"
					class="list-group-item list-group-item-warning"
					style="font-family: 微软雅黑">在线视频（基础）</button>
			</div>
			<!-- c列表  end-->

			<!-- C++ 列表 -->
			<button id="lists-c2" class="list-group-item"
				style="font-family: 微软雅黑">C++</button>
			<div id="lists-c2-well" style="display: none;">
				<button id="lists-c2-words"
					class="list-group-item list-group-item-warning"
					style="font-family: 微软雅黑">文字版教程</button>
				<button id="lists-c2-video"
					class="list-group-item list-group-item-warning"
					style="font-family: 微软雅黑">在线视频</button>
			</div>
			<!-- C++ 列表 -end->
				
			<!-- 内容左侧列表组 end -->

			<!-- 内容右侧侧面板 -->
		</div>
		<%@ include file="/WEB-INF/views/resource/allwords/c_words.jsp"%>
		
		<%@ include file="/WEB-INF/views/resource/allwords/c_video.jsp"%>
		
		<%@ include file="/WEB-INF/views/resource/allwords/c++_words.jsp"%>
		
		<%@ include file="/WEB-INF/views/resource/allwords/c++_video.jsp"%>



		<!-- 内容右侧侧面板 end-->
	</div>
	<!-- content end-->
</body>
</html>