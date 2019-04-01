<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<style>
div#panel-softs{
	display: none;
	width:1050px;
	overflow: hidden;
}
div#panel-softs>form:first-of-type>input{
	margin:10px;
	width:200px;
	display:inline-block;
}
div#panel-softs>form:first-of-type>input:last-of-type {
	width:100px;
}
</style>
	
<div class="panel-body"
	id="panel-softs">
	<form action="${pageContext.request.contextPath }/admins/addsoft" method="post">
	<input class="form-control" type="text" name="name" placeholder="名称+#+用于搜索的关键词">
	<input class="form-control" type="url" name="url" placeholder="填写软件的下载网址">
	<input type="submit" value="addSoft" class="btn btn-info">
	</form>
	<!-- 面板的列表数据显示 -->
	<c:if test="${empty requestScope.softs }">
				暂时没有收录软件
	</c:if>
	<c:if test="${!empty requestScope.softs }">
		<table class="table table-bordered">
			<tr class="info">
				<th>软件ID</th>
				<th>完整名称</th>
				<th>下载网址</th>
				<th>删除</th>
			</tr>
			<tr>
				<c:forEach items="${requestScope.softs  }" var="sof">
					<tr>
						<td>${sof.soid }</td>
						<td>${sof.name }</td>
						<td>${sof.url }</td>
						<td><a
							href="${pageContext.request.contextPath }/admins/deletesoft?soid=${sof.soid}">删除</a></td>
					</tr>
				</c:forEach>
			</tr>
		</table>
	</c:if>
</div>