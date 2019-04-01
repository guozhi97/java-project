<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<Script type="text/javascript">
$(function() {

	$("#reg-all").click(function() {
		$("#reg-for-input").attr("value", "getall");
		$("#registe-form-getall").submit();
	});
	$("#reg-vip").click(function() {
		$("#reg-for-input").attr("value", "会员");
		$("#registe-form-getall").submit();
	})
	$("#reg-jianzhan").click(function() {
		$("#reg-for-input").attr("value", "建站组");
		$("#registe-form-getall").submit();
	})
	$("#reg-mishuchu").click(function() {
		$("#reg-for-input").attr("value", "秘书处");
		$("#registe-form-getall").submit();
	})
	$("#reg-bangongbu").click(function() {
		$("#reg-for-input").attr("value", "办公部");
		$("#registe-form-getall").submit();
	})
	$("#reg-zuzhibu").click(function() {
		$("#reg-for-input").attr("value", "组织部");
		$("#registe-form-getall").submit();
	})
	$("#reg-bianchengbu").click(function() {
		$("#reg-for-input").attr("value", "编程部");
		$("#registe-form-getall").submit();
	})
	$("#reg-jishubu").click(function() {
		$("#reg-for-input").attr("value", "技术部");
		$("#registe-form-getall").submit();
	})
	$("#reg-meixuanbu").click(function() {
		$("#reg-for-input").attr("value", "美宣部");
		$("#registe-form-getall").submit();
	})
	$("#reg-download").click(function() {
		$("#registe-form-download").submit();
	});
});
</Script>
<style>
div#panel-registe{
	display: none;
	width:1050px;
	overflow: hidden;
}
div#panel-registe>div:nth-of-type(2) {
	clear: both;
	 height: 20px;
}
</style>


<!-- 获取注册名单 -->
	<form action="${pageContext.request.contextPath }/admins/getstudents"
		method="post" id="registe-form-getall">
		<input type="hidden" value="getall" name="reg-form" id="reg-for-input">
	</form>
<!-- 下载名单 -->
	<form action="${pageContext.request.contextPath }/admins/download"
		method="post" id="registe-form-download">
		<input type="hidden" value="${active }" name="reg-form" id="reg-for-down">
	</form>
	
<div class="panel-body"
	id="panel-registe">
	<!-- 面板的顶部按钮部分 -->
	<div class="btn-group" data-toggle="buttons">
		<button type="button" class="btn btn-default" id="reg-all">所有注册学生</button>
		<button type="button" class="btn btn-default" id="reg-vip">会员</button>
		<button type="button" class="btn btn-default" id="reg-jianzhan">建站组</button>
		<button type="button" class="btn btn-default" id="reg-mishuchu">秘书处</button>
		<button type="button" class="btn btn-default" id="reg-bangongbu">办公部</button>
		<button type="button" class="btn btn-default" id="reg-zuzhibu">组织部</button>
		<button type="button" class="btn btn-default" id="reg-bianchengbu">编程部</button>
		<button type="button" class="btn btn-default" id="reg-jishubu">技术部</button>
		<button type="button" class="btn btn-default" id="reg-meixuanbu">美宣部</button>
		<button type="button" class="btn btn-default" id="reg-download"
			style="margin-left: 50px;">下载名单</button>
		<button type="button" class="btn btn-info" id="reg-meixuanbu">${requestScope.reg_sum}</button>

	</div>
	<div></div>
	<!-- 面板的列表数据显示 -->
	<c:if test="${empty requestScope.students }">
				暂时没有学生注册
				</c:if>
	<c:if test="${!empty requestScope.students }">
		<table class="table table-bordered">
			<tr class="info">
				<th>学号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>专业</th>
				<th>苑区</th>
				<th>意愿</th>
				<th>联系方式</th>
				<th>处理</th>
			</tr>
			<tr>
				<c:forEach items="${requestScope.students }" var="stu">
					<tr>
						<td>${stu.sid }</td>
						<td>${stu.name }</td>
						<td>${stu.gender }</td>
						<td>${stu.major }</td>
						<td>${stu.address }</td>
						<td>${stu.will }</td>
						<td>${stu.tel }</td>
						<td><a
							href="${pageContext.request.contextPath }/admins/delete?sid=${stu.sid}">删除</a></td>
					</tr>
				</c:forEach>
			</tr>
		</table>
	</c:if>
</div>