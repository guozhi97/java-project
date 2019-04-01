<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>收货地址</title>

		<link href="../AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">

		<link href="../css/personal.css" rel="stylesheet" type="text/css">
		<link href="../css/addstyle.css" rel="stylesheet" type="text/css">

		<script src="../AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="../AmazeUI-2.4.2/assets/js/amazeui.js" type="text/javascript"></script>

	</head>

	<body>
		<div class="hmtop">
			<!--顶部导航条 -->
			<div class="am-container header">
				<ul class="message-l">
					<div class="topMessage">
						<div class="menu-hd">
							<a href="#"  class="h">刘仁华同学${sessionScope.userName}</a>
							<a href="#" >欢迎您！</a>
						</div>
					</div>
				</ul>
				<ul class="message-r">
				
					<div class="topMessage my-shangcheng">
						<div class="menu-hd MyShangcheng"><a href="information.jsp" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
					</div>
					<div class="topMessage home">
						<div class="menu-hd"><a href="order.jsp" target="_top" class="h"><i class="am-icon-list am-icon-fw"></i>订单中心</a></div>
					</div>
					<div class="topMessage mini-cart">
						<div class="menu-hd"><a id="mc-menu-hd" href="shopcart.jsp" target="_top"><i class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong id="J_MiniCartNum" class="h">0</strong></a></div>
					</div>
					<div class="topMessage favorite">
						<div class="menu-hd"><a href="login.jsp" target="_top"><i class="am-icon-heart am-icon-fw"></i><span><a href="login.jsp">退出</a></span></a></div>
				</ul>
				</div>
				<!--悬浮搜索框-->

				<div class="nav white">
					
					<div class="logoBig">
						<li><img src="../images/nlogo2.jpg" /></li>
					</div>

					<div class="search-bar pr">
						<a name="index_none_header_sysc" href="#"></a>
						<form>
							<input id="searchInput" name="index_none_header_sysc" type="text" placeholder="搜索一下，你就知道" autocomplete="off">
							<input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="submit">
						</form>
					</div>
				</div>

				<div class="clear"></div>
			</div>
			
				<div class="center">
			<div class="col-main">
				<div class="main-wrap">

					<div class="user-address" style="display: block;">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">地址管理</strong> / <small>Address&nbsp;list</small></div>
						</div>
						<hr/>
							
						<a class="new-abtn-type" data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0}">添加新地址</a>
						<!--例子-->
						<div class="am-modal am-modal-no-btn" id="doc-modal-1">

							<div id="add-dress">

								<!--标题 -->
								<div class="am-cf am-padding">
									<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">编辑地址</strong> / <small>Edit&nbsp;address</small></div>
								</div>
								<hr/>

								<div class="am-u-md-12 am-u-lg-8" style="margin-top: 20px;">
									<form class="am-form am-form-horizontal">

										<div class="am-form-group">
											<label for="user-name" class="am-form-label">收货人</label>
											<div class="am-form-content">
												<input type="text" id="user-name" placeholder="收货人">
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-phone" class="am-form-label">手机号码</label>
											<div class="am-form-content">
												<input id="user-phone" placeholder="手机号必填" type="email">
											</div>
										</div>
										<div class="am-form-group">
											<label for="user-address" class="am-form-label">所在地</label>
											<div class="am-form-content address">
												<select data-am-selected>
													<option value="b" selected>湖北省</option>
												</select>
												<select data-am-selected>
													<option value="b" selected>宜昌市</option>
												</select>
												<select data-am-selected>
													<option value="a">夷陵区</option>
													<option value="b" selected>西陵区</option>
													<option value="c">伍家岗区</option>
													<option value="d" >点军区</option>
													<option value="e">高新区</option>
													
												</select>
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-intro" class="am-form-label">详细地址</label>
											<div class="am-form-content">
												<textarea class="" rows="3" id="user-intro" placeholder="输入详细地址"></textarea>
												<small>100字以内写出你的详细地址...</small>
											</div>
										</div>

										<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
												<a class="am-btn am-btn-danger">保存</a>
												<a href="javascript: void(0)" class="am-close am-btn am-btn-danger" data-am-modal-close>取消</a>
											</div>
										</div>
									</form>
								</div>

							</div>

						</div>

					</div>

					<script type="text/javascript">
						$(document).ready(function() {							
							$(".new-option-r").click(function() {
								$(this).parent('.user-addresslist').addClass("defaultAddr").siblings().removeClass("defaultAddr");
							});
							
							var $ww = $(window).width();
							if($ww>640) {
								$("#doc-modal-1").removeClass("am-modal am-modal-no-btn");
							}
						});
					</script>

					<div class="clear"></div>

				</div>
				<!--底部-->
				   <div class="footer ">
						<div class="footer-bd ">
							<p> 
								<em>Copyright © 2016-2017 <a href="http://www.ctgu.edu.cn/" style="color: #0044bb" target="_blank" title="三峡大学">三峡大学计算机与信息学院</a> . 所有权拥有者</em>
							</p>
						</div>
					</div>
			</div>

			<aside class="menu">
				<ul>
					<li class="person">
						<a href="index.html">个人中心</a>
					</li>
					<li class="person">
						<a href="#">个人资料</a>
						<ul>
							<li> <a href="information.jsp">个人信息</a></li>
							<li> <a href="changePass.jsp">修改密码</a></li>
							<li class="active"> <a href="address.jsp">收货地址</a></li>
						</ul>
					</li>
					<li class="person">
						<a href="#">订单中心</a>
						<ul>
							<li><a href="order.jsp">订单管理</a></li>
							<li>&nbsp </li>
							<li>&nbsp  </li>
							<li>&nbsp </li>
							<li>&nbsp </li>
							<li>&nbsp </li>
							<li>&nbsp  </li>
							<li>&nbsp </li>
							<li>&nbsp </li>
							<li>&nbsp </li>
							<li>&nbsp  </li>
						</ul>
					</li>

				</ul>
			</aside>
		</div>
	</body>

</html>