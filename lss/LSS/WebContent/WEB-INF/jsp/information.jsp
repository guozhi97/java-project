<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>个人信息</title>

		<link href="AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">

		<link href="css/personal.css" rel="stylesheet" type="text/css">
		<link href="css/infstyle.css" rel="stylesheet" type="text/css">
		<script src="AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="AmazeUI-2.4.2/assets/js/amazeui.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/user.js"></script>
	</head>

	<body>
		<div class="hmtop">
			<!--顶部导航条 -->
			<div class="am-container header">
				<ul class="message-l">
					<div class="topMessage">
						<div class="menu-hd">
							<a href="#"  class="h">${sessionScope.userName}同学</a>
							<a href="#" >欢迎您！</a>
						</div>
					</div>
				</ul>
				<ul class="message-r">
				
					<!-- <div class="topMessage my-shangcheng">
						<div class="menu-hd MyShangcheng"><a href="information.jsp" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
					</div> -->
					<div class="topMessage home">
						<div class="menu-hd"><a href="getMyOrder" target="_top" class="h"><i class="am-icon-list am-icon-fw"></i>订单中心</a></div>
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
						<li><img src="images/nlogo2.jpg" /></li>
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

					<div class="user-info">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">个人资料</strong> / <small>Personal&nbsp;information</small></div>
						</div>
						<hr/>

						<!--个人信息 -->
						<div class="info-main">
							<form class="am-form am-form-horizontal" action="UserInfo" method="post"  onsubmit="return checkAllInfo()">
								
								<div class="am-form-group">
									<label for="user-name2" class="am-form-label">用户名</label>
									<div class="am-form-content">
										<input type="text" id="user-name1" readonly="readonly" value="${sessionScope.userName}">
									</div>
								</div>
								
								<div class="am-form-group">
									<label for="user-name" class="am-form-label">姓名</label>
									<div class="am-form-content">
										<input type="text" name="user-name2"  id="user-name2" placeholder="姓名" value="${sessionScope.userName}">
										<p><span class="flag" id="flag1"></span></p>
									</div>
								</div>

								<div class="am-form-group">
									<label class="am-form-label">性别</label>
									<div class="am-form-content sex">
										<label class="am-radio-inline">
											<input type="radio" checked="checked" name="radio10" value="male" data-am-ucheck> 男
										</label>
										<label class="am-radio-inline">
											<input type="radio" name="radio10" value="female" data-am-ucheck> 女
										</label>
										
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-name2" class="am-form-label">QQ</label>
									<div class="am-form-content">
										<input type="text" name="user-qq" id="user-qq" placeholder="qq号码" value=${ sessionScope.user.getQq()}>
										<p><span class="flag" id="flag2"></span></p>
									</div>
								</div>
			
								<div class="am-form-group">
									<label for="user-name2" class="am-form-label">借阅编号</label>
									<div class="am-form-content">
										<input type="text" name="user-number" id="user-number" placeholder="借阅编号" value=${ sessionScope.user.getReal_name()}>
										<p><span class="flag" id="flag3"></span></p>
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-email" class="am-form-label">所属单位</label>
									<div class="am-form-content">
										<input id="user-place" name="user-place"  placeholder="三峡大学学生处" type="text" value=${ sessionScope.user.getDept()}>
										<p><span class="flag" id="flag4"></span></p>
									</div>
								</div>	
								<div class="am-form-group">
									<label for="user-phone" class="am-form-label">办公电话</label>
									<div class="am-form-content">
										<input id="user-phone" name="user-phone" placeholder="telephonenumber" type="tel" value=${ sessionScope.user.getOffice_phone()}>
										<p><span class="flag" id="flag5"></span></p>
									</div>
								</div>
								
								<div class="info-btn">
									<button class="am-btn am-btn-danger" type="submit">保存修改</button>
								</div>

							</form>
						</div>

					</div>

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
						<a href="person">个人中心</a>
					</li>
					<li class="person">
						<a href="#">个人资料</a>
						<ul>
							<li class="active"> <a href="#">个人信息</a></li>
							<li> <a href="changePass.jsp">修改密码</a></li>
							<li> <a href="address.jsp">收货地址</a></li>
						</ul>
					</li>
					<li class="person">
						<a href="getMyOrder">订单中心</a>
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