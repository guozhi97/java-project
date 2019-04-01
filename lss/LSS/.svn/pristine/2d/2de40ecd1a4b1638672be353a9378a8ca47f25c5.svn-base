<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<title>购物车页面</title>

		<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="basic/css/demo.css" rel="stylesheet" type="text/css" />
		<link href="css/cartstyle.css" rel="stylesheet" type="text/css" />
		<link href="css/optstyle.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="js/jquery.js"></script>
		<script src="AmazeUI-2.4.2/assets/js/amazeui.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript">
	
			$(document).ready(function(){
				$("ul").delegate('.remove', 'click', function () {
					var a = $(this);
					var li = a.parent().parent().parent().find("li:eq(2)");
					var item = li.parent();
					var num = li.find("div span");
					
					
					$.ajax({
						dataType : "json",
						type : "POST", // 提交方式
						url:"remove",
						data:{
							"book_num":$(num).html()
						},
						success:function(data){
							$("#J_MiniCartNum").html(data);
							$("#itemCount").html(data);
							$(item).remove();
						
						},
						error:function(){
							alert("网络连接不稳定，请稍后再试");
						}
					})
					
					
				/* 	var num = li[2].innerHTML.find("div");
					alert(num.innerHTML); */
				}); 
				
			});
		</script>
	</head>

	<body>

		<!--顶部导航条 -->
		<div class="am-container header">
				<ul class="message-l">
					<div class="topMessage">
						<div class="menu-hd">
							<c:choose>
							<c:when test="${sessionScope.user==null}">
							<a href="login.jsp" target="_top" class="h">亲,请登录</a>
							<a href="register.jsp" target="_top">免费注册</a>
							</c:when>
							<c:otherwise>
							<a href="#"  class="h">${sessionScope.user.getReal_name()}</a>
							<a href="#" >欢迎您</a>
							</c:otherwise>
							</c:choose>
						</div>
					</div>
				</ul>
				<ul class="message-r">
					<c:choose>
					<c:when test="${sessionScope.userName==null}">
				<!-- 	<div class="topMessage my-shangcheng">
						<div class="menu-hd MyShangcheng"><a href="login.jsp" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
					</div> -->
					<div class="topMessage home">
						<div class="menu-hd"><a href="getMyOrder" target="_top" class="h"><i class="am-icon-list am-icon-fw"></i>订单中心</a></div>
					</div>
					<div class="topMessage mini-cart">
						<div class="menu-hd"><a id="mc-menu-hd" href="login.jsp" target="_top"><i class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong id="J_MiniCartNum" class="h">${sessionScope.bookList.size()}</strong></a></div>
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
						<div class="menu-hd"><a id="mc-menu-hd" href="shopcart.jsp" target="_top"><i class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong id="J_MiniCartNum" class="h">0</strong></a></div>
					</div>
					<div class="topMessage favorite">
						<div class="menu-hd"><a href="login.jsp" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>退出</span></a></div>
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

			<!--购物车 -->
			<div class="concent">
				<div id="cartTable">
					<div class="cart-table-th">
						<div class="wp">
							<div class="th th-chk">
								<div id="J_SelectAll1" class="select-all J_SelectAll">

								</div>
							</div>
							<div class="th th-item">
								<div class="td-inner">图书信息</div>
							</div>
						
							<div class="th th-amount">
								<div class="td-inner">作者</div>
							</div>
							
							<div class="th th-op">
								<div class="td-inner">操作</div>
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
												<a href="#" target="_blank" title="${item.getBook_name()}" class="item-title J_MakePoint" data-point="tbcart.8.11">${item.getBook_name()}</a><br>
												<span class="item-title">馆藏：${item.getHolding_num()}</span><br>
												<span class="item-title">可借：${item.getCan_borrow()}</span>
											</div>
										</div>
									</li>
									<li class="td td-info">
										<div class="item-props item-props-can">
											索取号：<span class="sku-line">${item.getBook_num()}</span>
										
										</div>
									</li>
								
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
                  删除</a>
										</div>
									</li>
								</ul>
								
							</div>
						</div>
					</tr>
					<div class="clear"></div>
						
						
						    
						</c:forEach> 



					<div class="clear"></div>

				<div class="float-bar-wrapper">
				<!-- 	<div id="J_SelectAll2" class="select-all J_SelectAll">
						<div class="cart-checkbox">
							<input class="check-all check" id="J_SelectAllCbx2" name="select-all" value="true" type="checkbox" checked="checked">
							<label for="J_SelectAllCbx2"></label>
						</div>
						<span>全选</span>
					</div> -->
					
					<div class="float-bar-right">
						<div class="amount-sum">
							<span class="txt">已选商品</span>
							<em id="itemCount">${sessionScope.bookList.size()}</em><span class="txt">件</span>
							<div class="arrow-box">
								<span class="selected-items-arrow"></span>
								<span class="arrow"></span>
							</div>
						</div>
					
						<div class="btn-area">
							<c:choose>
							<c:when test="${sessionScope.user==null }">
							<a href="login.jsp" id="J_Go" class="submit-btn submit-btn-disabled" aria-label="请注意如果没有选择宝贝，将无法结算">
								<span>去&nbsp;下&nbsp;单</span></a>
							</c:when>
							<c:otherwise>
							<a href="commit" id="J_Go" class="submit-btn submit-btn-disabled" aria-label="请注意如果没有选择宝贝，将无法结算">
								<span>去&nbsp;下&nbsp;单</span></a>
							</c:otherwise>
							</c:choose>
						</div>
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
		
	</body>
	
		<script type="text/javascript">
	var	num = $("input[id^='J_CheckBox']:checked").length;
	var allNum = num;
	document.ready = function(){
		
		$("#J_SelectAllCbx2").click(function(){
			num = 0;
			if(this.checked){
				 $("input[id^='J_CheckBox']").each(function(){
						$(this).prop("checked","checked");
						num++;
					});
				 //console.log("数量为:",num);
				 $("#J_SelectedItemsCount").html(num);
			}else{
				num = 0;
				 $("input[id^='J_CheckBox']").each(function(){
						$(this).prop("checked",false);
					});
				 $("#J_SelectedItemsCount").html(num);
			}
		});
		
		$("#J_SelectedItemsCount").html(num);

		
	};
	function onclickAction(){
		num = $("input[id^='J_CheckBox']:checked").length;
		$("#J_SelectedItemsCount").html(num);
		if(num < allNum){
			$("#J_SelectAllCbx2").prop("checked",false);
		}if(num == allNum){
			$("#J_SelectAllCbx2").prop("checked",true);
		}
	}
	

	
	</script>

</html>