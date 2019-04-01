<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<style>
div#panel-toall{
	display: none;
	width:1050px;
	overflow: hidden;
}
div#panel-toall>form{
	width:1050px;
}
div#panel-toall>form>textarea {
	width:1010px;
	margin:0 auto;
	height:300px;
}
div#panel-toall>form>input{
	float:right;
	margin-right: 50px;
}
</style>
	
<div class="panel-body"
	id="panel-toall">
	<!-- 面板的列表数据显示 -->
	<form action="${pageContext.request.contextPath }/admins/updatatoall" method="post">
		<textarea name="words">
		${requestScope.toall }
		</textarea>
		<input type="submit" value="修改" class="btn btn-info">
	</form>
</div>