<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
<style>
* {
	margin: 0px;
	padding: 0px;
	font-family: '微软雅黑';
}

header {
	font-size: 30px;
	color: gray;
	height: 60px;
	width: 1260px;
	margin: 0 auto;
	border-bottom: 5px solid gray;
	text-align: center;
}

section {
	height: 600px;
	width: 1260px;
	margin: 10px auto;
}

section>div:first-of-type {
	height: 500px;
	width: 200px;
	float: left;
}

section>div:last-of-type {
	height: 500px;
	width: 1050;
	float: left;
	margin-left: 8px;
}

section div.panel-heading {
	display: none;
	height: 40px;
	width: 1050px;
	text-align: center;
}
</style>

<script type="text/javascript">
	$(function() {
		var admin_index = "${requestScope.admin_index}";
		var sure_url = "${pageContext.request.contextPath }/admins/sure";
		var args = {
			'admin_index' : admin_index
		};
		$.post(sure_url, args,
				function(data) {
					if (data == "no") {
						$(location).attr('href',
								"${pageContext.request.contextPath }");
					}
				});
		/* 以上为验证身份 */

		function hideall() {
			$("panel-registe").hide();
			$("panel-softs").hide();
			$("#panel-requery").hide();
			$("#panel-comments").hide();
			$("#panel-toall").hide();
		}

		/* 注册名单部分 */

		function showRegisteForm() {
			hideall();
			$("#panel-top").html("注册申请管理");
			$("#panel-top").show();
			$("#panel-registe").show();
		}

		var registeform_index = "${requestScope.active}";
		if (registeform_index != null && "" != registeform_index) {
			showRegisteForm();
		}
		/* 注册名单部分 end */

		/* 管理软件收录 */

		function showSoftsForm() {
			hideall();
			$("#panel-top").html("收录软件管理");
			$("#panel-top").show();
			$("#panel-softs").show();

			
		}
		var softsform_index = "${requestScope.tosoft}";
		if (softsform_index != null && "" != softsform_index) {
			showSoftsForm();
		}
		/* 管理软件收录  end*/

		/* 反馈建议管理 */
		function showRequeryForm() {
			hideall();
			$("#panel-top").html("反馈建议管理");
			$("#panel-top").show();
			$("#panel-requery").show();
		}
		var requeryform_index = "${requestScope.torequery}";
		if (requeryform_index != null && "" != requeryform_index) {
			showRequeryForm();
		}
		/* 反馈建议管理  end*/
		
		/* 论坛管理 */
		function showCommentForm() {
			hideall();
			$("#panel-top").html("反馈建议管理");
			$("#panel-top").show();
			$("#panel-comments").show();
		}
		var commentform_index = "${requestScope.tocomment}";
		if (commentform_index != null && "" != commentform_index) {
			showCommentForm();
		}
		/* 论坛管理  end*/
		
		/* 公告管理 */
		function showToallForm() {
			hideall();
			$("#panel-top").html("公告管理");
			$("#panel-top").show();
			$("#panel-toall").show();
		}
		var toall_index = "${requestScope.gettoall}";
		if (toall_index != null && "" != toall_index) {
			showToallForm();
		}
		/* 公告管理  end*/

		
	});
</script>
<body>
	<header>网站后台管理</header>
	<section>
		<div class="list-group">
			<button type="button" class="list-group-item active">操作选项</button>
			<a
				href="${pageContext.request.contextPath }/admins/getstudents?reg-form=getall"
				class="list-group-item list-group-item-info" id="registe-request">注册申请管理</a>
			<a href="${pageContext.request.contextPath }/admins/getsofts"
				class="list-group-item list-group-item-info" id="btn-softs">软件资源管理</a>
			<a href="${pageContext.request.contextPath }/admins/getrequery"
				class="list-group-item list-group-item-info" id="btn-requery">反馈建议管理</a>
			<a href="${pageContext.request.contextPath }/admins/tocomments"
				class="list-group-item list-group-item-info" id="btn-answer">论坛管理</a>
			<a href="${pageContext.request.contextPath }/admins/getwords"
				class="list-group-item list-group-item-info" id="btn-toall">公告管理</a>
			<a href="${pageContext.request.contextPath }/index.html"
				class="list-group-item list-group-item-info" id="return">回到主界面</a>
		</div>

		<div class="panel panel-danger">
			<div class="panel-heading" id="panel-top">标题</div>
			<%@ include file="/WEB-INF/views/comadmin/registeform.jsp"%>
			<%@ include file="/WEB-INF/views/comadmin/softsform.jsp"%>
			<%@ include file="/WEB-INF/views/comadmin/requerys.jsp"%>
			<%@ include file="/WEB-INF/views/comadmin/comments.jsp"%>
			<%@ include file="/WEB-INF/views/comadmin/toall.jsp"%>
			

		</div>
	</section>
</body>
</html>