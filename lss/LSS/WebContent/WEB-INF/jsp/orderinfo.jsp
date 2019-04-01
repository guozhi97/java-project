<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>订单管理</title>

		<link href="AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">

		<link href="css/personal.css" rel="stylesheet" type="text/css">
		<link href="css/orstyle.css" rel="stylesheet" type="text/css">

		<script src="AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="AmazeUI-2.4.2/assets/js/amazeui.js" type="text/javascript"></script>

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
				
				<!-- 	<div class="topMessage my-shangcheng">
						<div class="menu-hd MyShangcheng"><a href="information.jsp" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
					</div> -->
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

					<div class="user-orderinfo">

						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">订单详情</strong> / <small>Order&nbsp;details</small></div>
						</div>
						<hr/>
						
						<div class="order-infoaside">
							<div class="order-logistics">
								<a href="logistics.html">
									<div class="icon-log">
										<i><img src="images/receive.png"></i>
									</div>
									<div class="latest-logistics">
										<p class="text">书籍已被签收，很高兴为您服务</p>
										<div class="time-list">
											<span class="date">2017-6-19</span><span class="week">周六</span><span class="time">15:35:42</span>
										</div>
									
									</div>
									<span class="am-icon-angle-right icon"></span>
								</a>
								<div class="clear"></div>
							</div>
							<div class="order-addresslist">
								<div class="order-address">
									<div class="icon-add">
									</div>
									<p class="new-tit new-p-re">
										<span class="new-txt">小叮当</span>
										<span class="new-txt-rd2">159****1622</span>
									</p>
									<div class="new-mu_l2a new-p-re">
										<p class="new-mu_l2cw">
											<span class="title">收货地址：</span>
											<span class="province">湖北</span>省
											<span class="city">武汉</span>市
											<span class="dist">洪山</span>区
											<span class="street">雄楚大道666号(中南财经政法大学)</span></p>
									</div>
								</div>
							</div>
						</div>
						<div class="order-infomain">

							<div class="order-top">
								<div class="th th-item">
									<td class="td-inner">商品</td>
								</div>
							
								<div class="th th-number">
									<td class="td-inner">数量</td>
								</div>
							
								<div class="th th-status">
									<td class="td-inner">交易状态</td>
								</div>
								<div class="th th-change">
									<td class="td-inner">交易操作</td>
								</div>
							</div>

							<div class="order-main">

								<div class="order-status3">
									<div class="order-title">
										<div class="dd-num">订单编号：<a href="javascript:;">1601430</a></div>
										<span>成交时间：2015-12-20</span>
										<!--    <em>店铺：小桔灯</em>-->
									</div>
									<div class="order-content">
										<div class="order-left">
											<ul class="item-list">
												<li class="td td-item">
													
													<div class="item-info">
														<div class="item-basic-info">
															<a href="#">
																<p>微型计算机技术</p>
																			<p class="info-little">出版社：科学出版社
																				<br/>责任人：陈慈发</p>
															</a>
														</div>
													</div>
												</li>
											
												<li class="td td-number">
													<div class="item-number">
														<span>×</span>2
													</div>
												</li>
											</ul>
											
											<ul class="item-list">
												<li class="td td-item">
													
													<div class="item-info">
														<div class="item-basic-info">
															<a href="#">
															<p>微型计算机技术</p>
																			<p class="info-little">出版社：科学出版社
																				<br/>责任人：陈慈发</p>
															</a>
														</div>
													</div>
												</li>
											
												<li class="td td-number">
													<div class="item-number">
														<span>×</span>2
													</div>
												</li>
											</ul>
											
										</div>
										
										<div class="order-right">
											
											<div class="move-right">
												<li class="td td-status">
													<div class="item-status">
														<p class="order-info">已收货</p>
													</div>
												</li>
												<li class="td td-change">
													<div class="am-btn am-btn-danger anniu">
														确认收货</div>
												</li>
											</div>
										</div>
									</div>

								</div>
							</div>
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
						<a href="index.html">个人中心</a>
					</li>
					<li class="person">
						<a href="#">个人资料</a>
						<ul>
							<li> <a href="information.jsp">个人信息</a></li>
							<li> <a href="changePass.jsp">修改密码</a></li>
							<li> <a href="address.jsp">收货地址</a></li>
						</ul>
					</li>
					<li class="person">
						<a href="#">订单中心</a>
						<ul>
							<li class="active"><a href="order.html">订单管理</a></li>
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