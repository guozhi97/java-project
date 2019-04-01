<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link href="${pageContext.request.contextPath }/css/combottom.css" rel="stylesheet">

<script type="text/javascript" src="${pageContext.request.contextPath }/js/combottom.js"></script>

<footer>
	<!-- 底部便捷链接 -->
	<div class="bot-well" style="margin-left: 120px;">
		<ul>
			<li class="bot-head">常用资源</li>
			<li class="bot-content"><a
				href="https://sm.myapp.com/original/Development/Dev-Cpp_5.11_TDM-GCC_4.9.2_Setup.exe">dev-c++</a></li>
			<li class="bot-content"><a
				href="http://dl-t1.wmzhe.com/30/30118/jdk_8.0.1310.11_64.exe">jdk1.8下载</a></li>
			<li class="bot-content"><a
				href="http://wangshuo.jb51.net:81/201806/tools/eclipse_photo_win64_jb51.rar">eclipse4.8下载</a></li>
		</ul>
	</div>
	<div class="bot-well">
		<ul>
			<li class="bot-head">关于我们</li>
			<li class="bot-content"><a href="${pageContext.request.contextPath }/index/about_detail">关于计协</a></li>
			<li class="bot-content"><a href="${pageContext.request.contextPath }/index/tel">联系我们</a></li>
			<li class="bot-content"><a href="#" id="a-requery">反馈建议</a></li>
		</ul>
	</div>
	<div class="bot-well">
		<ul>
			<li class="bot-head">会员服务</li>
			<li class="bot-content"><a href="#" onclick="javascript:alert('暂未开放！');">近期讲座</a></li>
			<li class="bot-content"><a href="#" onclick="javascript:alert('暂未开放！');">近期活动</a></li>
			<li class="bot-content"><a target="_blank"
				href="${pageContext.request.contextPath }/answer/answer">问题解答</a></li>
		</ul>
	</div>
	<div class="bot-well">
		<ul>
			<li class="bot-head">友情链接</li>
			<li class="bot-content"><a href="http://www.cnki.net/"
				data-toggle="tooltip" data-placement="right" title="校内网可免费下载"
				target="_blank">中国知网</a></li>
			<li class="bot-content"><a href="https://www.nowcoder.com/"
				data-toggle="tooltip" data-placement="right" title="学习资源网站"
				target="_blank">牛客网</a></li>
			<li class="bot-content"><a
				href="http://jwc.ctgu.edu.cn/fwqxztt.asp" data-toggle="tooltip"
				data-placement="right" title="学生选课入口" target="_blank">三峡大学教务处</a></li>
			<li class="bot-content"><a href="http://210.42.41.119/"
				target="_blank">三峡大学图书馆</a></li>
		</ul>
	</div>
	<!-- 底部便捷链接 end-->

	<div style="clear: both;"></div>

	<!-- 最底部的div -->
	<article>
		<HR color=#987cb9 SIZE=3>
		<span>©2018--2020 CTGU CA , 三峡大学计算机协会 | All rights reserved.</span> <a href="#" id="a-admin" >后台管理</a>
	</article>
	<!-- 最底部的div end-->

	<!-- 投诉建议的模态窗体 -->
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button data-dismiss="modal" class="close" type="button">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">反馈建议</h4>
				</div>
				<form action="${pageContext.request.contextPath }/index/requery" method="post">
				<div class="modal-body" style="height:200px;">
					<textarea class="form-control" style="height:80%;" name="content" required="required"></textarea>
				</div>
				<div class="modal-footer">
					<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
					<input class="btn btn-primary"  type="submit" value="提交">
				</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- 投诉建议的模态窗体 end-->

	<!-- 后台管理的模态窗体 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button data-dismiss="modal" class="close" type="button">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">后台管理</h4>
				</div>
				<div class="modal-body">
					<p>管理员登录</p>
					<div class="input-group">
						<form action="${pageContext.request.contextPath }/index/admin" target="_blank"
							method="POST" id="admin-form">
							<input id="admin-name" type="text" class="form-control"
								placeholder="用户名" aria-describedby="basic-addon1" name="name">
							<input id="admin-pwd" type="password" class="form-control"
								placeholder="密码" aria-describedby="basic-addon1" name="pwd">
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
					<button class="btn btn-primary" id="bot-submit" type="button">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- 后台管理的模态窗体 end-->
</footer>