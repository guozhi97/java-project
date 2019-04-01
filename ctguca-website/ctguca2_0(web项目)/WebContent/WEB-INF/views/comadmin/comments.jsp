<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<Script type="text/javascript">
$(function() {

});
</Script>
<style>
div#panel-comments{
	display: none;
	width:1050px;
	overflow: hidden;
}

</style>
	
<div class="panel-body"
	id="panel-comments">
	<!-- 面板的列表数据显示 -->
	<c:if test="${empty requestScope.foranswer }">
				暂时没有请求的评论
				</c:if>
	<c:if test="${!empty requestScope.foranswer }">
		<table class="table table-bordered">
			<tr class="info">
				<th>评论内容</th>
				<th>对应的问题序号</th>
				<th>发表时间</th>
				<th>删除评论</th>
				<th>删除请求</th>
			</tr>
			<tr>
				<c:forEach items="${requestScope.foranswer }" var="ans">
					<tr>
						<td>${ans.content }</td>
						<td>${ans.pid}</td>
						<td>${ans.time }</td>
						<td><a
							href="${pageContext.request.contextPath }/admins/delpro_ans?aid=${ans.aid}&pid=-1">删除</a></td>
						<td><a
							href="${pageContext.request.contextPath }/admins/delrequeryp_a?aid=${ans.aid}&pid=-1">删除</a></td>
					</tr>
				</c:forEach>
			</tr>
		</table>
	</c:if>
	<hr>
	<c:if test="${empty requestScope.forproblem }">
				暂时没有请求删除的提问
				</c:if>
	<c:if test="${!empty requestScope.forproblem }">
		<table class="table table-bordered">
			<tr class="info">
				<th>提问的标题</th>
				<th>提问的内容</th>
				<th>发表时间</th>
				<th>删除提问</th>
				<th>删除请求</th>
			</tr>
			<tr>
				<c:forEach items="${requestScope.forproblem }" var="pro">
					<tr>
						<td>${pro.title }</td>
						<td>${pro.content}</td>
						<td>${pro.time }</td>
						<td><a
							href="${pageContext.request.contextPath }/admins/delpro_ans?aid=-1&pid=${pro.pid}">删除</a></td>
						<td><a
							href="${pageContext.request.contextPath }/admins/delrequeryp_a?aid=-1&pid=${pro.pid}">删除</a></td>
					</tr>
				</c:forEach>
			</tr>
		</table>
	</c:if>
</div>