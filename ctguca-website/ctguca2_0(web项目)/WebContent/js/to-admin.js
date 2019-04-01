/**
 * 
 */
$(function(){
	console.log("js");
	$('div.me-con>lable').click(function(){
		$(this).parent().hide();
		$(this).parent().prev().hide();
	})
	
	$('a#admin-login').click(function(){
		$('div.me-con').show();
		$('div.me-bg').show();
	})
	
	
	$('div.me-con>form').submit(function(){
		var name=$(this).children('input').eq(0).val();
		var pwd=name+$(this).children('input').eq(1).val();
		var datas={
			'pwd':pwd
		}
		console.log(pwd);
		//使用ajax提交注册信息
		$.ajax({
			type:"post",
			url:"/ctguca/admins/sure",
			async:false,//使用同步传输数据
			data:datas,
			success:function(data){
				if(data=='no'){
					alert('用户名或密码错误！');
				}else{
					location.href="/ctguca/admins/login?pwd="+data;
				}
				console.log(data);
			},
			error:function(){
				alert("网络故障，请稍后重试");
			}
		});
		/* console.log(datas); */
		return false;
	})
	
	$('div.me-con2>lable').click(function(){
		$(this).parent().hide();
		$('div.me-bg').hide();
	})
	
	$('a#a-requery').click(function(){
		$('div.me-con2').show();
		$('div.me-bg').show();
	})
	
	$('div.me-con2>form').submit(function(){
		var content=$(this).children('textarea').val();
		var datas={
			'content':content
		}
		console.log(content);
		//使用ajax提交注册信息
		$.ajax({
			type:"post",
			url:"/ctguca/index/requery",
			async:true,//使用同步传输数据
			data:datas,
			success:function(data){
				alert('提交成功，我们汇尽快处理。');
				console.log(data);
			},
			error:function(){
				alert("网络故障，请稍后重试");
			}
		});
		/* console.log(datas); */
		$('div.me-con2').hide();
		$('div.me-bg').hide();
		return false;
	})
	
})