<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<style>
div#panel-requery{
	display: none;
	width:1050px;
	overflow: hidden;
}
</style>

<div class="panel-body"
	id="panel-requery">
	<!-- 面板的列表数据显示 -->
	<c:if test="${empty requestScope.requerys }">
				暂时没有反馈建议
				</c:if>
	<c:if test="${!empty requestScope.requerys }">
		<table class="table table-bordered">
			<tr class="info">
				<th>反馈内容</th>
				<th>编辑</th>
			</tr>
			<tr>
				<c:forEach items="${requestScope.requerys }" var="req">
					<tr>
						<td>${req.content}</td>
						<td><a href="${pageContext.request.contextPath }/admins/delrequery?rid=${req.rid}">删除</a></td>
					</tr>
				</c:forEach>
			</tr>
		</table>
	</c:if>
</div>