<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

	<head lang="en">
		<meta charset="UTF-8">
		<title>登录</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Cache-Control" content="no-siteapp" />

		<link rel="stylesheet" href="AmazeUI-2.4.2/assets/css/amazeui.css" />
		<link href="css/dlstyle.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/user.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				//alert($("#message").val());
				if($("#message").val().length > 0){
					$("#error").css("display", "block");
				}
			});
		</script>
	</head>

	<body>
		<div class="login-boxtitle">
			<a href="home.html"><img alt="logo" src="images/tlogo.jpg" /></a>
		</div>
		<div class="login-banner">
			<div class="login-main">
				<div class="login-banner-bg"><span></span><img src="images/blg.jpg" /></div>
				<div class="login-box">
							<span id="flag"></span>
							<h3 class="title" style="margin-top: 50px;position:relative;">登录系统</h3>
							<div class="clear"></div>
						<div class="login-form">
						  <form action="login" method="post">
							   <div class="user-name">
								    <label for="user"><i class="am-icon-user"></i></label>
								    <input type="text" name="user" id="user" placeholder="邮箱/手机/用户名">
                 </div>
                 <div class="user-pass">
								    <label for="password"><i class="am-icon-lock"></i></label>
								    <input type="password" name="password" id="password" placeholder="请输入密码">
                 </div>
                 	<div class="am-cf">
									<input type="submit" name="loginIn" id="loginIn" value="登 录" class="am-btn am-btn-primary am-btn-sm">
								</div>
              </form>
           </div>
            
            <div class="login-links">
                <label for="remember-me"><input id="remember-me" type="checkbox">记住密码</label>
								<a href="#" class="am-fr">忘记密码</a>
								<a href="register.jsp" class="zcnext am-fr am-btn-default" style="margin-top: 50px">注册</a>
								<br />
            </div>	
            <input type="hidden" value="${param.message}" id="message">
              <label id="error" style="display: none;color:red">*用户名或密码错误</label>
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
	</body>

</html>