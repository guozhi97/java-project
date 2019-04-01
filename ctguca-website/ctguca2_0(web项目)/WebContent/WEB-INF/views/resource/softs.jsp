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
		$("#lists-c-devc-display").hide();
		$("#lists-java-jdk-display").hide();
		$("#lists-java-eclipse-display").hide();
	}
	
	$(function() {
		/* c/c++ */
		$("#lists-c").click(function() {
			$("#lists-c-well").toggle();		
			return false;
		});
		$("#lists-c-devc").click(function() {
			closeOther();
			$("#lists-c-devc-display").show();
			return false;
		});
		
		/* java */
		$("#lists-java").click(function() {
			$("#lists-java-well").toggle();
			return false;
		});
		$("#lists-java-jdk").click(function() {
			closeOther();
			$("#lists-java-jdk-display").show();
			return false;
		})
		$("#lists-java-eclipse").click(function() {
			closeOther();
			$("#lists-java-eclipse-display").show();
			return false;
		})
	});
</script>
<body>
	<!-- top -->
	<div class="top">
		<a href="" id="top-title" style="text-decoration: none;">CTGUCA&nbsp;|下载</a>
	</div>
	<!-- top end-->

	<!-- content -->
	<div class="content">
		<!-- 内容左侧列表组 -->
		<div class="list-group" style="float: left; width: 20%;">
			<button class="list-group-item active" style="font-family: 微软雅黑">软件列表</button>

			<!-- c/c++列表 -->
			<button id="lists-c" class="list-group-item"
				style="font-family: 微软雅黑">C/C++</button>
			<div id="lists-c-well" style="display: none;">
				<button id="lists-c-devc"
					class="list-group-item list-group-item-warning"
					style="font-family: 微软雅黑">Dev-C++</button>
			</div>
			<!-- c/c++列表  end-->

			<!-- java 列表 -->
			<button id="lists-java" class="list-group-item"
				style="font-family: 微软雅黑">java</button>
			<div id="lists-java-well" style="display: none;">
				<button id="lists-java-jdk"
					class="list-group-item list-group-item-warning"
					style="font-family: 微软雅黑">JDK 8.0</button>
				<button id="lists-java-eclipse"
					class="list-group-item list-group-item-warning"
					style="font-family: 微软雅黑">Eclipse</button>
			</div>
			<!-- java 列表 -end->
				
			<!-- 内容左侧列表组 end -->

			<!-- 内容右侧侧面板 -->
		</div>
		<%@ include file="/WEB-INF/views/resource/allsoft/dev_c_c++.jsp"%>

		<%@ include file="/WEB-INF/views/resource/allsoft/jdk.jsp"%>
		
		<%@ include file="/WEB-INF/views/resource/allsoft/eclipse.jsp"%>

		<!-- 内容右侧侧面板 end-->
	</div>
	<!-- content end-->
</body>
</html>