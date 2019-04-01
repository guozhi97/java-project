<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0 ,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<title>结算页面</title>
		<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="basic/css/demo.css" rel="stylesheet" type="text/css" />
		<link href="css/cartstyle.css" rel="stylesheet" type="text/css" />
		<link href="css/jsstyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/address.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#save").click(function(){
					
					if($("#user_id").val().length==0||
							$("#user-name").val().length==0||
							$("#user-phone").val().length==0||
							$("#user-intro").val().length==0){
						$("#warn").html("*您的信息必须填写完整");
						$("#warn").css("color","red");
						return;
					}else if($("#user-phone").val().length>11){
						$("#warn").html("*手机号太长了，请检查是否填写错误");
						$("#warn").css("color","red");
						return;
					}
					$("#warn").css("display","none");
					
					$.ajax({
						url:"addAddress",
						type : "GET", // 提交方式
						data:{
							"user_id":$("#user_id").val(),
							"user_name":$("#user-name").val(),
							"mobile":$("#user-phone").val(),
							
							"address":$("#delivArea option:selected").val() + $("#department option:selected").val()+$("#user-intro").val()
						},
						success:function(data){
							location.reload();
						/* 	$(document.body).css("overflow","visible");
							$('.theme-login').removeClass("selected");
							$('.item-props-can').removeClass("selected");					
							$('.theme-popover-mask').hide();
							$('.theme-popover').slideUp(200);
							$("#address li").remove(); */
							/*  $.each(data.addressList,function(i,item){
								$("#address").append("<li class='user-addresslist'>"+
										"<div class='address-left'>"+
											"<div class='user DefaultAddr'>"+
												"<span class='buy-address-detail'>"   +
		                 						"<span class='buy-user'>"+item.reader_name+" </span>"+
												"<span class='buy-phone'>"+item.mobile+" </span>"+
												"</span>"+
											"</div>"+
											"<div class='default-address DefaultAddr'>"+
												"<span class='buy-line-title buy-line-title-type'>收货地址：</span>"+
										   "<span class='province'>"+item.address+" </span>"+
											"</div>"+
											"<ins class='deftip'>默认地址</ins>"+
										"</div>"+
										"<div class='address-right'>"+
											"<a href='person/address.html'>"+
												"<span class='am-icon-angle-right am-icon-lg'></span></a>"+
										"</div>"+
										"<div class='clear'></div>"+

										"<div class='new-addr-btn'>"+
											"<a href='#' class='hidden'>设为默认</a>"+
											"<span class='new-addr-bar hidden'>|</span>"+
											"<a href='#'>编辑</a>"+
											"<span class='new-addr-bar'>|</span>"+
											"<input type='hidden' value='"+item.address_id+" ' class='address_id'>"+
											"<a href='#' class = 'remove'>删除</a>"+
										"</div>"+
									"</li>");	 
							}); */	

						},
						error:function(){
							alert("网络似乎出了点问题，没能成功提交请求");
						}
						
					});
				});
					
					
					$("#holyshit269").click(function(){
					 	if($("#selected_address_id").size() <= 0){
							alert("您必须填写地址信息才能提交订单");
							return;					 		
					 	}
						$.ajax({
							url:"doOrder",
							type : "POST", // 提交方式
							data:{
								"user_id":$("#user_id").val(),
								"address_id":$("#selected_address_id").val(),
								"extra":$("#extra").val(),
							},
							success:function(data){
								if(data == "ok"){
									alert("订单已经提交成功，请耐心等待发货");
									location.href="home.jsp";
								}
								else{
									alert("订单提交失败，请稍后重试");
								}
							},
							error:function(){
								alert("您的网络似乎出现了问题，订单提交失败");
							}
						});
					});
				
				$("ul").delegate('li div .remove', 'click', function () {
					var div = $(this).parent();
					var address_id = div.find(".address_id").val();
					
					$.ajax({
						url:"removeAddress",
						type:"post",
						data:{
							"address_id":address_id
						},
						success:function(data){
							if(data=="ok")
								location.reload();
						},
						error:function(){
							alert("网络连接出现了问题，请求提交失败");
						}
						
					});
					});
				
				
				
				});
		</script>
	</head>
	<body>
		<!--顶部导航条 -->
		<div class="hmtop">
			<!--顶部导航条 -->
			<div class="am-container header">
				<ul class="message-l">
					<div class="topMessage">
						<div class="menu-hd">
						<input id="user_id" type="hidden" value="${sessionScope.userId}">
							<a href="#"  class="h">${sessionScope.userName}同学</a>
							<a href="#" >欢迎您！</a>
						</div>
					</div>
				</ul>
<ul class="message-r">
					<c:choose>
					<c:when test="${sessionScope.userName==null}">
			<!-- 		<div class="topMessage my-shangcheng">
						<div class="menu-hd MyShangcheng"><a href="login.jsp" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
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
					</c:when>
					<c:otherwise>
					<!-- <div class="topMessage my-shangcheng">
						<div class="menu-hd MyShangcheng"><a href="information.jsp" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
					</div> -->
					<div class="topMessage home">
						<div class="menu-hd"><a href="getMyOrder" target="_top" class="h"><i class="am-icon-list am-icon-fw"></i>订单中心</a></div>
					</div>
					<div class="topMessage mini-cart">
						<div class="menu-hd"><a id="mc-menu-hd" href="shopcart.jsp" target="_top"><i class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong id="J_MiniCartNum" class="h">${sessionScope.bookList.size()}</strong></a></div>
					</div>
					<div class="topMessage favorite">
						<div class="menu-hd"><a href="shopcart.jsp" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>退出</span></a></div>
					</div>
					</c:otherwise>
					</c:choose>
				</ul>
				</div>
			<!--悬浮搜索框-->

				<div class="nav white">
					<div class="logoBig">
						<li><img src="images/nlogo.jpg" /></li>
					</div>

				<div class="search-bar pr">
					<a name="index_none_header_sysc" href="#"></a>
					<form action="home.jsp">
						<input id="searchInput" name="index_none_header_sysc" type="text" placeholder="搜索" autocomplete="off">
						<input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="submit">
					</form>
				</div>
			</div>

			<div class="clear"></div>
			<div class="concent">
				<!--地址 -->
				<div class="paycont">
					<div class="address">
						<h3>确认收货地址 </h3>
						<div class="control">
							<div class="tc-btn createAddr theme-login am-btn am-btn-danger">使用新地址</div>
						</div>
						<div class="clear"></div>
						<ul id="address">
						
						<c:forEach var="item" items="${addressList}" varStatus="status">
							<div class="per-border"></div>
							<li class="user-addresslist DefaultAddr">
				
								<div class="address-left">
									<div class="user DefaultAddr">
									
										<span class="buy-address-detail">   
                 						<span class="buy-user">${item.getReader_name()} </span>&nbsp;&nbsp;
										<span class="buy-phone">${item.getMobile()}</span>
										</span>
									</div>
									<div class="default-address DefaultAddr">
										<span class="buy-line-title buy-line-title-type">收货地址：</span>
								   <span class="myaddress">${item.getAddress()}</span>
								 
									</div>
									<c:choose>
									<c:when test="${status.index == 0 }">
										<ins class="deftip">默认地址</ins></c:when>
										<c:otherwise>
											
										</c:otherwise>
									</c:choose>
						
								</div>
								<div class="address-right">
									<a href="person/address.html">
										<span class="am-icon-angle-right am-icon-lg"></span></a>
								</div>
								<div class="clear"></div>

								<div class="new-addr-btn">
									<a href="#" class="hidden">设为默认</a>
									<span class="new-addr-bar hidden">|</span>
									<a href="#">编辑</a>
									<span class="new-addr-bar">|</span>
									<input type="hidden" value="${item.getAddress_id()}" class="address_id">
									<a href="#" class = "remove">删除</a>
								</div>

							</li>
							</c:forEach>
							
							
							<!-- <div class="per-border"></div>
							<li class="user-addresslist">
								<div class="address-left">
									<div class="user DefaultAddr">

										<span class="buy-address-detail">   
                   <span class="buy-user">刘仁华 </span>
										<span class="buy-phone">15871145629</span>
										</span>
									</div>
									<div class="default-address DefaultAddr">
										<span class="buy-line-title buy-line-title-type">收货地址：</span>
										<span class="buy--address-detail">
								   <span class="province">湖北</span>省
										<span class="city">宜昌</span>市
										<span class="dist">西陵</span>区
										<span class="street">大学路八号</span>
										</span>
										</span>
									</div>
									<ins class="deftip hidden">默认地址</ins>
								</div>
								<div class="address-right">
									<span class="am-icon-angle-right am-icon-lg"></span>
								</div>
								<div class="clear"></div>

								<div class="new-addr-btn">
									<a href="#">设为默认</a>
									<span class="new-addr-bar">|</span>
									<a href="#">编辑</a>
									<span class="new-addr-bar">|</span>
									<a href="javascript:void(0);"  onclick="delClick(this);">删除</a>
								</div>
							</li> -->
						</ul>

						<div class="clear"></div>
					</div>
					<!--物流 -->
					<div class="clear"></div>
					<!--订单 -->
					<div class="concent">
						<div id="payTable">
							<h3>确认订单信息</h3>
							<div class="cart-table-th">
								<div class="wp">

									<div class="th th-item">
										<div class="td-inner">商品信息</div>
									</div>
								
									<div class="th th-amount">
										<div class="td-inner">作者</div>
									</div>
								
									<div class="th th-oplist">
										<div class="td-inner">配送方式</div>
									</div>

								</div>
							</div>
							<div class="clear"></div>

				
				
				<c:forEach var="item" items="${sessionScope.bookList}" varStatus="status"> 
						
						
						<tr class="item-list">
						<div class="bundle  bundle-last ">
							<div class="bundle-hd">
								<div class="bd-promos">
									
								</div>
							</div>
							<div class="clear"></div>
							<div class="bundle-main">
								<ul class="item-content clearfix">
									<li class="td td-chk">
									<!-- 	<div class="cart-checkbox ">
											<input class="check" id="J_CheckBox_1" name="items[]" value="170037950254" type="checkbox" checked="checked" onclick="onclickAction()">
											<label for="J_CheckBox_170037950254"></label>
										</div> -->
									</li>
									<li class="td td-item">
										
										<div class="item-info">
											<div class="item-basic-info">
												<a href="#" target="_blank" title="${item.getBook_name()}" class="item-title J_MakePoint" data-point="tbcart.8.11">书名：${item.getBook_name()}</a><br>
												<span class="item-title">馆藏：${item.getHolding_num()}</span><br>
												<span class="item-title">可借：${item.getCan_borrow()}</span><br>
												<span>索取号：<span class="sku-line">${item.getBook_num()}</span>
											</div>
										</div>
									</li>
								<!-- 	<li class="td td-info">
										<div class="item-props item-props-can">
											
										
										</div>
									</li> -->
								
									<li class="td td-amount">
										<div class="amount-wrapper ">
											<div class="item-amount ">
												<div class="sl">
												<span class="item-title">${item.getAuthor()}</span>
													<!-- <input class="min am-btn" name="" type="button" value="-" />
													<input class="text_box" name="" type="text" value="3" style="width:30px;" />
													<input class="add am-btn" name="" type="button" value="+" /> -->
												</div>
											</div>
										</div>
									</li>
								
									<li class="td td-op">
										<div class="td-inner">
										
											<a href="javascript:;" data-point-url="#" class="remove">
                  送货上门</a>
										</div>
									</li>
								</ul>
								
							</div>
						</div>
					</tr>
					<div class="clear"></div>
						
						
						    
						</c:forEach> 
				
						

							<tr class="item-list">
							
								<div class="bundle  bundle-last">

									<div class="bundle-main">
										
										
										<div class="clear"></div>

									</div>
							</tr>
							<div class="clear"></div>
							</div>

						
							</div>
							<div class="clear"></div>
							<div class="pay-total">
						<!--留言-->
							<div class="order-extra">
								<div class="order-user-info">
									<div id="holyshit257" class="memo">
										<label>买家留言：</label>
										<input type="text" id="extra" title="选填,对本次交易的说明（建议填写已经和卖家达成一致的说明）" placeholder="选填,建议填写和卖家达成一致的说明" class="memo-input J_MakePoint c2c-text-default memo-close">
										<div class="msg hidden J-msg">
											<p class="error">最多输入500个字符</p>
										</div>
									</div>
								</div>

							</div>
							<!--优惠券 -->
						
							</div>
							
							<!--信息 -->
							<div class="order-go clearfix">
								<div class="pay-confirm clearfix">
								
								<c:choose>
									
									
									<c:when test="${addressList.size() <= 0}">
										<h5>当前没有地址，请添加地址</h5>
									</c:when>
								<c:otherwise>
									<div class="box">
										<div id="" class="pay-address">

											<p class="buy-footer-address" style="text-align: left;">
												<span class="buy-line-title buy-line-title-type">寄送至：</span>
												<span class="street" id="selected_address">${addressList.get(0).getAddress()}</span>
											</p>
											<p class="buy-footer-address">
												<span class="buy-line-title">收货人：</span>
												<span class="buy-address-detail">   
                                         <span class="buy-user" id="selected_user">${addressList.get(0).getReader_name()} </span>
												<span class="buy-phone" id="selected_mobile">${addressList.get(0).getMobile()}</span>
												</span>
												  <input type="hidden" id="selected_address_id" value="${addressList.get(0).getAddress_id()}">
											</p>
										</div>
									</div>
						</c:otherwise>
						</c:choose>
									<div id="holyshit269" class="submitOrder">
										<div class="go-btn-wrap">
											<a  class="btn-go"  title="点击此按钮，提交订单"><span id="commitOrder">提交订单</span></a>
										</div>
									</div>
									<div class="clear"></div>
								</div>
							</div>
						</div>

						<div class="clear"></div>
					</div>
				</div>
				 <div class="footer ">
						<div class="footer-bd ">
							<p> 
								<em>Copyright © 2016-2017 <a href="http://www.ctgu.edu.cn/" style="color: #0044bb" target="_blank" title="三峡大学">三峡大学计算机与信息学院</a> . 所有权拥有者</em>
							</p>
						</div>
					</div>
			</div>
			<div class="theme-popover-mask"></div>
			<div class="theme-popover">

				<!--标题 -->
				<div class="am-cf am-padding">
					<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">新增地址</strong> / <small>Add address</small></div>
				</div>
				<hr/>

				<div class="am-u-md-12">
					<form class="am-form am-form-horizontal">

						<div class="am-form-group">
							<label for="user-name" class="am-form-label" >收货人</label>
							<div class="am-form-content">
								<input type="text" id="user-name" placeholder="收货人">
							</div>
						</div>

						<div class="am-form-group">
							<label for="user-phone" class="am-form-label">手机号码</label>
							<div class="am-form-content">
								<input id="user-phone" placeholder="手机号必填" type="text">
							</div>
						</div>

							<div class="am-form-group">
											<label for="user-address" class="am-form-label">所在地</label>
											<div class="am-form-content address">
													<select data-am-selecte id="delivArea">department
														<c:forEach var="item" items="${delivList}" varStatus="status">
													<option value="${item.getDeliv_area_name()}">${item.getDeliv_area_name()}</option>
											</c:forEach>
												</select>
											<!-- 	<select data-am-selected>
													<option value="b" selected>宜昌市</option>
												</select> -->
												<select data-am-selected id="department">
											<c:forEach var="item" items="${departmentList}" varStatus="status">
													<option value="${item.getDept_name()}">${item.getDept_name()}</option>
											</c:forEach>
												</select>
											</div>
										</div>

						<div class="am-form-group">
							<label for="user-intro" class="am-form-label">详细地址</label>
							<div class="am-form-content">
								<textarea class="" rows="3" id="user-intro" placeholder="输入详细地址"></textarea>
								<small id="warn">100字以内写出你的详细地址...</small>
							</div>
						</div>

						<div class="am-form-group theme-poptit">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<div class="am-btn am-btn-danger" id="save">保存</div>
								<div class="am-btn am-btn-danger close">取消</div>
							</div>
						</div>
					</form>
				</div>

			</div>

			<div class="clear"></div>
	</body>

</html>