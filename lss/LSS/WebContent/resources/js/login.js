//全局变量
var preloaderBar = $(".preloader-bar");

//when document ready
$(function () {
    //设置 preloader bar 预加载渐进效果
    preloaderBar.children(".status").animate({ width: "20%"});

    //全屏背景插件（IE8下可能不兼容）
    if(jQuery.backstretch){
        var bgs = [
            'resources/images/login/bg_1.jpg',
            'resources/images/login/bg_3.jpg'
        ];
        $("body").backstretch(bgs, { fade: 1000, duration: 8000 });
    }

    //完成预加载渐进效果，放在 document ready 最后
    window.setTimeout(function () {
        preloaderBar.addClass("active");
        preloaderBar.children(".status").animate({ width: "100%"});
    }, 400);
});

//验证码刷新处理
function f_onChangeGraph(objId) {
    var obj = document.getElementById(objId);
    if (obj) {
        var src = ['/Public/GetValidateCode'];
        src.push("?time=");
        src.push((new Date()).getTime());
        obj.src = src.join("");

    }
}

//获取指定名称的cookie的值
function getcookie(objname){
	var arrstr = document.cookie.split("; ");
	for(var i = 0;i < arrstr.length;i ++){
	var temp = arrstr[i].split("=");
	if(temp[0] == objname) return unescape(temp[1]);
	}
	}

//登录处理
function f_onLogin() {
    var formObj = $("#loginForm");
    formObj.on("submit", function(e){
    	e.preventDefault();
    	return false;
    });
    var username = $("#user_name");
    var password = $("#password");
    var validateCode = $("#validateCode");
    var checktip = $("#alertSummary .Validform_checktip");
    var btnLogin = $("#btnLogin");
    if (!username || !username.val()) {
        username.addClass("Validform_error");
        checktip.text("用户名不能为空").addClass("Validform_wrong");
        return false;
    } else {
        username.removeClass("Validform_error");
    }
    if (!password || !password.val()) {
        password.addClass("Validform_error");
        checktip.text("密码不能为空").addClass("Validform_wrong");
        return false;
    } else {
        password.removeClass("Validform_error");
    }
    if (!validateCode || !validateCode.val()) {
        validateCode.addClass("Validform_error");
        checktip.text("验证码不能为空").addClass("Validform_wrong");
        //$("#validCodeImg").trigger("click");
        return false;
    } else{
        username.removeClass("Validform_error");
    }
    
    //前端校验通过，开始处理登录
    checktip.text("请稍后...").removeClass("Validform_wrong").addClass("Validform_loading");
    btnLogin.text('登录中...');   
    //开始 ajax 登录
    $.ajax({
		dataType : "json",
		url : "logon",
		type : "post",
		data : {
			user_name : $('input[name="user_name"]').val(),
			password : $('input[name="password"]').val(),
			validateCode:$('input[name="validateCode"]').val(),
			timestamp:new Date().getTime()
		},
		success : function(data) {
			if(data.success){
				window.location.href = getContextPath() + "/toMainPage";
			}else{
				checktip.text(data.message).addClass("Validform_wrong");
				btnLogin.text('登录'); 
			}
		},
		error:function(data){
			console.log(data);
		}
	});
    return false;
}

//获取项目根路径
function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}
