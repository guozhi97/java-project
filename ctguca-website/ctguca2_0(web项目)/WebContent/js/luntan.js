$(function() {
	console.log('www.guoz.work');

	
	//分析cookie值，显示上次的登陆信息
	var userNameValue = getCookieValue("uid");
	var passwordValue = getCookieValue("pwd");
//	console.log();
	var U = new user(userNameValue, passwordValue);
	var anss=new article();

	//分析url看是否有数据传过来
	
//	var thisURL = encodeURIComponent(document.URL);
	var thisURL = document.URL;
	console.log(thisURL);
	var words = thisURL.split('=')[1];
	if(words!=undefined&&words!=''){
		words=unescape(words)
		$('#h-input>input').val(words);
		console.log($('#h-input>input').val());
		DownloadArForContent(words);
		setTimeout(	U.toLogin,3000);
	}else{
		U.toLogin();
	}
	
	
	function article() { // 存放临时数据
		this.arcticles = "no";
		this.tuid = "0";
	}
	$('#h-input').next().children('button').click(function(){
		DownloadArForContent($('#h-input>input').val());
		console.log($('#h-input>input').val());
	});
	
	function DownloadArForContent(words) { // 从服务器上获取搜索的文章
		var datas = {
				'words' : words
			}	
		$.ajax({
			type : "post",
			url : "/ctguca/luntan/search",
			async : true,
			data : datas,
			success : DownloadAllArSuc,
			error : function() {
				alert("网络故障，请稍后重试");
			}
		});
	}
	
	function DownloadUserArcticle() { // 从服务器上获取用户自己的文章
		var datas = {
				'uid' : U.uid
			}	
		$.ajax({
			type : "post",
			url : "/ctguca/luntan/uarcticle",
			async : true,
			data : datas,
			success : DownloadAllArSuc,
			error : function() {
				alert("网络故障，请稍后重试");
			}
		});
	}
	
	$('.s-top>li').eq(2).click(function(){
		if(U.state){
			DownloadUserArcticle();			
		}else{
			alert('请先登录账号');
		}
	})
	
	
	function jubaoar(marid){//举报文章或者提问
		var datas = {
				'arid' : marid
			}
			$.ajax({
				type : "post",
				url : "/ctguca/luntan/jubaoar",
				async : true, // 同步上传数据
				data : datas,
				success : function(){
					alert('感谢您为论坛环境做出的贡献');
				},
				error : function() { // 没有得到返回的数据，网络中断
					alert("网络故障，请稍后重试");
				}
			});	
	}
	
	$('.discuss>li:nth-of-type(3)').click(function(){
		jubaoar($(this).parent().parent().attr('id'));
		console.log($(this).parent().parent().attr('id'));
	})
	
	function jubaoan(manid){//举报回复的功能
		var datas = {
				'anid' : manid
		}
		$.ajax({
			type : "post",
			url : "/ctguca/luntan/jubaoan",
			async : true, // 同步上传数据
			data : datas,
			success : function(){
				alert('感谢您为论坛环境做出的贡献');
			},
			error : function() { // 没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});	
	}
	
	$('.c-list>span:nth-of-type(2)').click(function(){
		jubaoan($(this).parent().attr('id'));
	})
	
	
	function getInformation() {// 从服务器获取用户信息
//		console.log("getInformation");
		var datas = {
			'uid' : U.uid
		}
		$.ajax({
			type : "post",
			url : "/ctguca/luntan/usforan",
			async : false, // 同步上传数据
			data : datas,
			success : getInformationSuc,
			error : function() { // 没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}
	
	function getInformationSuc(data) { // 返回结果并修改
		var j1 = JSON.parse(data);
		U.name = j1.name;
		U.disc = j1.disc;
		U.ico = j1.ico;
//		console.log("getInformation:" + data);
	}
	function addApploud(marid){//点赞
		var datas = {
			'arid' :marid
		}
		$.ajax({
			type : "post",
			url : "/ctguca/luntan/addapploud",
			async : false, // 同步上传数据
			data : datas,
			success : function(){
				console.log('ok');
			},
			error : function() { // 没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});		
	}
	
	$('.discuss>li:first-of-type').click(function(){
		var num=parseInt($(this).children('span').attr('name'));
		num++;
		$(this).children('span').attr('name',num);
		$(this).children('span').text('甩赞'+num);
		addApploud($(this).parent().parent().attr('id'));
	})
	

	function updateUser() { // 更新界面上的信息
//		console.log("updateUser");
		$('.top-drop').prev('img').attr('src', U.ico); // 头像更新
		$('.top-drop>dd').eq(1).text('切换登录');

	}

	function user(muid, mpwd) { // 一个用户对象
		this.uid = muid;
		this.pwd = mpwd;
		this.name = "等修改呢~";
		this.disc = "这个人有点懒";
		this.ico = "touxiang/tou0.jpg";
		this.state = false; // 账号状态

		this.toLogin = function() { // 登录
			var datas = {
				'uid' : this.uid,
				'pwd' : this.pwd
			}
			$.ajax({
				type : "post",
				url : "/ctguca/luntan/login",
				async : false, // 同步上传数据
				data : datas,
				success : this.loginSuc,
				error : function() { // 没有得到返回的数据，网络中断
					alert("网络故障，请稍后重试");
				}
			});
		}
		this.loginSuc = function(data) { // 获取登录返回结果
			 console.log("login:" + data);
			if (data == "yes") {
				U.state = true;
				getInformation();
				updateUser();
			} else {
				U.state = false;
			}
			DownloadAllArcticle();
		}

		this.toLoginOther = function(muid, mpwd) { // 切换账号
			this.uid = muid;
			this.pwd = mpwd;
			this.state = false;
		}
		this.toRegister = function() { // 注册
			var datas = {
				'uid' : this.uid,
				'pwd' : this.pwd,
				'name' : this.name,
				'disc' : this.disc,
				'ico' : this.ico
			}
			$.ajax({
				type : "post",
				url : "/ctguca/luntan/register",
				async : false, // 同步上传数据
				data : datas,
				success : this.RegisterSuc,
				error : function() { // 没有得到返回的数据，网络中断
					alert("网络故障，请稍后重试");
				}
			});
		}
		this.RegisterSuc = function(data) { // 获取注册返回的数据
			// console.log("register:" + data);
			if (data == "yes") {
				U.state = true;
				getInformation();
				updateUser();
			} else {
				U.state = false;
			}
			// console.log("register2:"+U.state);
		}

		this.updateInformation = function(uname, udisc, uico, upwd) {
			this.name = uname;
			this.disc = udisc;
			this.ico = uico;
			this.pwd = upwd;
			var datas = {
				'uid' : this.uid,
				'pwd' : upwd,
				'name' : uname,
				'disc' : udisc,
				'ico' : uico
			}
			$.ajax({
				type : "post",
				url : "/ctguca/luntan/update",
				async : false, // tong步上传数据
				data : datas,
				success : this.updateInForSuc,
				error : function() { // 没有得到返回的数据，网络中断
					alert("网络故障，请稍后重试");
				}
			});
		}
		this.updateInForSuc = function(data) { // 返回修改信息的结果
			console.log('update1:' + data)
			if (data == "yes") {
				updateUser();
				alert('信息修改成功');
			}
		}

	}

	function uploadArcticle(mtitle, mcontent) { // 写文章
		var datas = {
			'uid' : U.uid,
			'title' : mtitle,
			'content' : mcontent,
			'time' : new Date().toLocaleDateString()
		}
		$.ajax({
			type : "post",
			url : "/ctguca/luntan/addarcticle",
			async : false, // 同步上传数据
			data : datas,
			success : uploadArSuc,
			error : function() { // 没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function uploadArSuc(data) { // 返回上传文章的结果
		console.log('updateArcticle:' + data);
		if (data == "yes") {
			alert('发表成功');
		} else {
			alert('出现一个错误');
		}
	}

	function uploadAnswer(mcontent, marid) { // 写回复
		var datas = {
			'uid' : U.uid,
			'content' : mcontent,
			'arid' : marid,
			'ansid' : -1,
			'time' : new Date().toLocaleDateString()
		}
		$.ajax({
			type : "post",
			url : "/ctguca/luntan/addanswer",
			async : true, // 异步上传数据
			data : datas,
			success : uploadAnSuc,
			error : function() { // 没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function uploadAnSuc(data) { // 返回发表的结果
		console.log('updateAnswer:' + data);
		if (data == "yes") {
			alert('发表成功');
		} else {
			alert('出现一个错误');
		}
	}

	function addArcticle(json) {
		var ar = $('article>div.list').eq(0).clone(true);
		ar.attr('id', json.arid);
		ar.css('display', 'block');
		ar.children('.ainfo').children('img').attr('src', json.ico);
		ar.children('.ainfo').children('span').eq(0).text(json.name);
		ar.children('.ainfo').children('span').eq(2).text(json.disc);
		ar.children('h3.atitle').text(json.title);
		ar.children('div.acontent').text(json.content);
		ar.children('ul.discuss').children('li').eq(3).text(json.time);
		ar.children('ul.discuss').children('li').eq(0).children('span').text(
				"甩赞" + json.apploud);
		ar.children('ul.discuss').children('li').eq(0).children('span').attr('name',json.apploud);
		ar.children('ul.discuss').children('li').eq(1).children('span').text(
				json.ansnum + "条评论");
		ar.children('ul.discuss').children('li').eq(1).children('span').attr('name',json.ansnum);
		ar.children('.comments').children('h4').eq(0).children('span').text(
				json.ansnum);
		$('article>div.list').eq(0).after(ar);
	}
	function DownloadAllArcticle() { // 从服务器上获取所有文章
		$.ajax({
			type : "post",
			url : "/ctguca/luntan/allarc_ans",
			async : true,
			success : DownloadAllArSuc,
			error : function() {
				alert("网络故障，请稍后重试");
			}
		});
	}
	function DownloadAllArSuc(data) { // 传回来的data是一个json数组字符串
//		console.log('DownloadAllArSuc:' + data)
		if(data=='no'){
			alert('暂时没有文章或提问哦！');
			return;
		}
		var jsonarr = JSON.parse(data.trim());
		var ar = $('article>div.list').eq(0).clone(true);
		$('article').html("");
		$('article').eq(0).append(ar);	
		$.each(jsonarr, function(index, ele) { // 遍历json数组
			addArcticle(ele);
		})
	}

	function DownloadAllAnswer(marid) { // 返回一篇文章或者提问的所有回复
		var datas = {
			'arid' : marid
		}
		$.ajax({
			type : "post",
			url : "/ctguca/luntan/all_ans",
			async : false, // 同步上传数据
			data : datas,
			success : DownloadAllAnSuc,
			error : function() { // 没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}
	function DownloadAllAnSuc(data) { // 传回来的data是一个json数组字符串
//		console.log('DownloadAllAnSuc:' + data);
		var jsonarr = JSON.parse(data);
		anss.answers= jsonarr;// 放到临时数组里
	}


	// 为顶部输入框旁的按钮添加缩小动画
	$("#h-input>input").focus(function() {
		$(this).parent().next().children('button').addClass('toless');
		$(this).parent().next().next().fadeToggle();
	});
	$("#h-input>input").blur(function() {
		$(this).parent().next().children('button').removeClass('toless');
		$(this).parent().next().next().fadeToggle();
	});

	// 修改信息相关
	$('dl.top-drop>dd').eq(0).click(function() {
		if (U.state) {
			$(".medelone>div:first-of-type>img").attr('src', U.ico);
			$(".medelone>div:first-of-type>input").eq(0).val(U.name);
			$(".medelone>div:first-of-type>input").eq(1).val(U.pwd);
			$(".medelone>div:first-of-type>input").eq(2).val(U.disc);
			$('div.me-bg').show();
			$('.medelone').show();
		} else {
			alert("请先登录账号");
		}
	})
	$('.medelone>span').eq(0).click(function() {
		$(this).parent().hide();
		$('div.me-bg').hide();
	})
	$('select#qietu').click(function() { // 切换头像
							$('select#qietu').prev().attr('src',
							$('select#qietu>option:checked').val());
							});
	$('.medelone').submit(function() { // 提交信息
		var ico = $(".medelone>div:first-of-type>img").attr('src');
		var name = $(".medelone>div:first-of-type>input").eq(0).val();
		var pwd = $(".medelone>div:first-of-type>input").eq(1).val();
		var disc = $(".medelone>div:first-of-type>input").eq(2).val();
		 U.updateInformation(name, disc, ico,pwd);
//		console.log(ico + "-" + name + '-' + pwd + '-' + disc);
		$(this).hide();
		$('div.me-bg').hide();
		return false;
	})

	// 写文章相关
	$('li#towriter').click(function() {
		$('div.me-bg').show();
		$('.model2').show();
	})
	$('.model2>span').eq(0).click(function() {
		$(this).parent().hide();
		$('div.me-bg').hide();
	})
	$('.model2').submit(
			function() { // 提交文章信息
				if (U.state == false) {
					alert("请先登录账号");
					return false;
				}
				uploadArcticle($(this).children('div').children('input').val(),
						$(this).children('div').children('textarea').val());
				DownloadAllArcticle();
				$(this).hide();
				$('div.me-bg').hide();
				return false;
			});

	// 提问相关
	$('li#torequest').click(function() {
		$('div.me-bg').show();
		$('.model3').show();
	})
	$('.model3>span').eq(0).click(function() {
		$(this).parent().hide();
		$('div.me-bg').hide();
	})
	$('.model3').submit(
			function() { // 提交提问的信息
				if (U.state == false) {
					alert("请先登录账号");
					return false;
				}
				uploadArcticle('一条提问', $(this).children('div').children(
						'textarea').val());
				DownloadAllArcticle();
				$(this).hide();
				$('div.me-bg').hide();
				return false;
			});

	// 登录相关
	$('dl.top-drop>dd').eq(1).click(function() {
		$('div.me-bg').show();
		$('.model4').show();
	})
	$('.model4>span').eq(0).click(function() {
		$(this).parent().hide();
		$('div.me-bg').hide();
	})
			  

	$('.model4').submit(
			function() { // 提交登录的信息
				if (U.state) {
					U.toLoginOther($(this).children('div').children('input')
							.eq(0).val(), $(this).children('div').children(
							'input').eq(1).val());
				} else {
					U = new user($(this).children('div').children('input')
							.eq(0).val(), $(this).children('div').children(
							'input').eq(1).val());
				}
				U.toLogin();
				if (U.state) {
					// alert("登录成功");
			         setCookie("uid",$(this).children('div').children('input')
								.eq(0).val(),24,"/");
			         setCookie("pwd",$(this).children('div').children(
						'input').eq(1).val(),24,"/");
					$(this).hide();
					$('div.me-bg').hide();
				} else {
					alert("账号或密码错误");
				}
				$(this).children('div').children('input').eq(0).val("");
				$(this).children('div').children('input').eq(1).val("");
				return false;
			});

	// 注册相关
	$('.model4>input').eq(0).click(function() {
		console.log('ch')
		$(this).parent().hide();
		$('div.me-bg').show();
		$('.model5').show();
	})
	$('.model5>span').eq(0).click(function() {
		$(this).parent().hide();
		$('div.me-bg').hide();
	})
	$('.model5').submit(
			function() { // 提交注册的信息
				U = new user($(this).children('div').children('input').eq(0)
						.val(), $(this).children('div').children('input').eq(1)
						.val());
				U.toRegister();
				// console.log("submit:"+U.state);
				if (U.state) {
					$(this).hide();
					$('div.me-bg').hide();
				} else {
					alert("该账号已被注册！请换一个账号");
				}
				$(this).children('div').children('input').eq(0).val("");
				$(this).children('div').children('input').eq(1).val("");
				return false;
			});

	// 文章、提问的区域
	$('div.list>span').eq(0).click(function() {
		$(this).parent().slideUp(); // 隐藏该提问或文章
	})

	$('ul.discuss>li').eq(1)
			.click(
					function() { // 切换显示评论
						$(this).parent().next().slideToggle();
						if ($(this).children('span').text() != '收起评论') {
							$('div.tonext').show();
							DownloadAllAnswer($(this).parent().parent()
									.attr('id'));
//							console.log(anss.answers);
							var c=$(this).parent().next().children('.c-list').eq(0);
							$(this).parent().next().children('.c-list').remove();
							$(this).parent().next().children('div').eq(0).after(c);
							
							for(var i=0;i<anss.answers.length;i++){
//								console.log(ele.content);
//								console.log("cc");
								var ele=anss.answers[i];
								var a = $(this).parent().next().children('.c-list').eq(0).clone(true);
								a.css('display','block');
								a.attr('id', ele.anid);
								a.children('img').attr('src', ele.ico);
								a.children('span').eq(0).text(ele.name);
								a.children('div').eq(0).text(ele.content);
								a.children('span').eq(2).text(ele.time);
								$(this).parent().next().children('div.c-list').eq(0).after(a);								
//								console.log(a.html());
							}
							$('div.tonext').hide();
							$(this).children('span').text('收起评论')
						} else {
							$(this).children('span').text(
									$(this).children('span').attr('name')
											+ '条评论')
						}
					})

	$('button.btn-com').click(
			function() { // 提交评论
				if ($(this).prev().val() != undefined
						&& $(this).prev().val() != '') {
//					console.log('chi')
					uploadAnswer($(this).prev().val(), $(this).parent()
							.parent().parent().attr('id'));
					$(this).prev().val(""); // 清空输入框
					$(this).parent().parent().slideUp();
					var num=$(this).parent().parent().parent().children('.discuss').children('li').eq(1).children('span').attr('name');
					num++;
					$(this).parent().parent().parent().children('.discuss').children('li').eq(1).children('span').attr('name',num);
					$(this).parent().parent().children('h4').children('span').text(num);
					$(this).parent().parent().parent().children('.discuss').children('li').eq(1).children('span').text(num+"条评论");
				}
			})
			//新建cookie。
			//hours为空字符串时,cookie的生存期至浏览器会话结束。hours为数字0时,建立的是一个失效的cookie,这个cookie会覆盖已经建立过的同名、同path的cookie（如果这个cookie存在）。
			function setCookie(name,value,hours,path){
			  var name = escape(name);
			  var value = escape(value);
			  var expires = new Date();
			   expires.setTime(expires.getTime() + hours*3600000);
			   path = path == "" ? "" : ";path=" + path;
			   _expires = (typeof hours) == "string" ? "" : ";expires=" + expires.toUTCString();
			   document.cookie = name + "=" + value + _expires + path;
			}
			//获取cookie值
			function getCookieValue(name){
			  var name = escape(name);
			  //读cookie属性，这将返回文档的所有cookie
			  var allcookies = document.cookie;    
			  //查找名为name的cookie的开始位置
			   name += "=";
			  var pos = allcookies.indexOf(name);  
			  //如果找到了具有该名字的cookie，那么提取并使用它的值
			  if (pos != -1){                       //如果pos值为-1则说明搜索"version="失败
			    var start = pos + name.length;         //cookie值开始的位置
			    var end = allcookies.indexOf(";",start);    //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置
			    if (end == -1) end = allcookies.length;    //如果end值为-1说明cookie列表里只有一个cookie
			    var value = allcookies.substring(start,end); //提取cookie的值
			    return (value);              //对它解码   
			     }  
			  else return "";                //搜索失败，返回空字符串
			}
			//删除cookie
			function deleteCookie(name,path){
			  var name = escape(name);
			  var expires = new Date(0);
			   path = path == "" ? "" : ";path=" + path;
			   document.cookie = name + "="+ ";expires=" + expires.toUTCString() + path;
			}
			 
})