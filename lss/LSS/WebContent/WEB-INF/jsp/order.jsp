<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>订单管理</title>
		<link href="AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="basic/css/demo.css" rel="stylesheet" type="text/css" />
		<link href="css/personal.css" rel="stylesheet" type="text/css">
		<link href="css/orstyle.css" rel="stylesheet" type="text/css">
		<script src="AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="AmazeUI-2.4.2/assets/js/amazeui.js" type="text/javascript"></script>

	</head>

	<body>
		<!--顶部导航条 -->
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
					<c:choose>
					<c:when test="${sessionScope.userName==null}">
					<!-- <div class="topMessage my-shangcheng">
						<div class="menu-hd MyShangcheng"><a href="login.jsp" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
					</div> -->
					<div class="topMessage home">
						<div class="menu-hd"><a href="getMyOrder" target="_top" class="h"><i class="am-icon-list am-icon-fw"></i>订单中心</a></div>
					</div>
					<div class="topMessage mini-cart">
						<div class="menu-hd"><a id="mc-menu-hd" href="shopcart.jsp" target="_top"><i class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong id="J_MiniCartNum" class="h">${sessionScope.bookList.size()}</strong></a></div>
					</div>
					</c:when>
					<c:otherwise>
				<!-- 	<div class="topMessage my-shangcheng">
						<div class="menu-hd MyShangcheng"><a href="information.jsp" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
					</div> -->
					<div class="topMessage home">
						<div class="menu-hd"><a href="getMyOrder" target="_top" class="h"><i class="am-icon-list am-icon-fw"></i>订单中心</a></div>
					</div>
					<div class="topMessage mini-cart">
						<div class="menu-hd"><a id="mc-menu-hd" href="shopcart.jsp" target="_top"><i class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong id="J_MiniCartNum" class="h">${sessionScope.bookList.size()}</strong></a></div>
					</div>
					<div class="topMessage favorite">
						<div class="menu-hd"><a href="exit" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>退出</span></a></div>
					</div>
					</c:otherwise>
					</c:choose>
				</ul>
				</div>

				<!--悬浮搜索框-->
				<div class="nav white">
					
					<div class="logoBig">
						<li><img src="images/nlogo2.jpg" /></li>
					</div>

					<div class="search-bar pr">
						<a name="index_none_header_sysc" href="#"></a>
						<form action="home.jsp">
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

					<div class="user-order">

						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">订单管理</strong> / <small>Order</small></div>
						</div>
						<hr/>

						<div class="am-tabs am-tabs-d2 am-margin" data-am-tabs>

						<!-- 	<ul class="am-avg-sm-5 am-tabs-nav am-nav am-nav-tabs">
								<li class="am-active"><a href="#tab1">所有订单</a></li>
							</ul> -->

							<div class="am-tabs-bd">
								<div class="am-tab-panel am-fade am-in am-active" id="tab1">
									<div class="order-top">
										<div class="th th-item">
											<td class="td-inner">书籍</td>
										</div>
										<div class="th th-number">
											<td class="td-inner">状态</td>
										</div>
											
										<div class="th th-status">
											<td class="td-inner">收货地址</td>
										</div>
										<div class="th th-change">
											<td class="td-inner">交易操作</td>
										</div>
									</div>

					

									
									<div class="order-main">
										<div class="order-list">
										
								<c:forEach var="item" items="${list}" varStatus="status">
									<!--交易成功-->
											<div class="order-status5">
												<div class="order-title">
													<div class="dd-num">订单编号：<a href="javascript:;">${item.order.getOrder_id()}</a></div>
													<span>下单时间：${item.order.getCreate_time()}</span>
													<!--    <em>店铺：小桔灯</em>-->
												</div>
												<div class="order-content">
													<div class="order-left">
														<ul class="item-list">
														
													
														<li class="td td-item">
																	<c:forEach var="bookItem" items="${item.list}" varStatus="st">
																<div class="item-info">
																	<div class="item-basic-info">
																		<!-- <a href="#"> -->
																			<p>书名：${bookItem.getBook_name()}</p>
																			<p class="info-little">索取号：${bookItem.getIndex_num()}
																				<br/>作者：${bookItem.getAuthor_name()}</p>
																		<!-- </a> -->
																	</div>
																</div>
																	</c:forEach>
															</li>
																<li class="td td-number">
																<div class="item-number">
																	<span>进行中</span>
																</div>
															</li>
															<li class="td td-operation">
																<div class="item-operation">
																	
																</div>
															</li> 
													
															
														</ul>

												

														<!-- <ul class="item-list">
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
																	<span>进行中</span>
																</div>
															</li>
															<li class="td td-operation">
																<div class="item-operation">
																	
																</div>
															</li> 
														</ul> -->
													</div>
													<div class="order-right">
														<div class="move-right">
															<li class="td td-status">
																<div class="item-status">
																	<p class="Mystatus">&nbsp; ${item.getAddress().getAddress()}</p>
																</div>
															</li>
															<li class="td td-change">
																<a href="deleteOrder?order_id=${item.order.getOrder_id()}"><div class="am-btn am-btn-danger anniu">
																	删除订单</div></a>
															</li>
														</div>
													</div>
												</div>
											</div>
							</c:forEach>
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

			<!-- <aside class="menu">
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
							<li class="active"><a href="order.jsp">订单管理</a></li>
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

			</aside> -->
		</div>
	</body>

</html>