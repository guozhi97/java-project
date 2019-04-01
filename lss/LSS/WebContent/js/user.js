
var flag1 = false;
var flag2 = false;
var flag3 = false;
var flag4 = false;
var flag5 = false;
var msg1 = false;
var msg2 = false;
var msg3 = false;
var msg4 = false;
var msg5 = false;

$(document).ready(function(){  
  if (${message} == 'error') {
	 $("#flag").css("color","red");
  	 $("#flag").html("用户名或密码错误");
}
	
	
	
    $("#user").blur(function(){  
        if($("#user").val() == null || $("#user").val() == ""){
        	$("#flag").css("color","red");
        	$("#flag").html("用户名不能为空！");
        	$("#user").focus();
        }
        $.ajax({
			type:"POST",
			dataType:"json",
			url:"../doName",
			data:{'email':$("#user").val()},
			error:function(data){
//				alert("出错啦！");
			},
			success:function(data){
				var msg = data.success;
				if(msg == "yes"){
					$("#flag").css("color","#00ff00");
					$("#flag").html("您是合格的登录用户");
				}else{
					$("#flag").css("color","#ff0000");
					$("#flag").html("您是不存在用户，请重新输入！");
					input1.focus();
				}
			}
		});
    }); 
    
    $("#password").blur(function(){
    	 if($("#password").val() == null || $("#password").val() == ""){
         	$("#flag").css("color","red");
         	$("#flag").html("密码不能为空！");
         	$("#user").focus();
         }
    		$.ajax({
    			type:'POST',
    			url:'../doLogin',
    			data:{'email':input1.val(),'pwd':input2.val()},
    			dataType:'json',
    			error:function(data){
//    				alert("出错啦！");
    			},
    			success:function(data){
    				var msg = data.success;
    				if(msg == "yes"){
    					flag1 = true;
    					flag2 = true;
    				}else{
    					input2.select();
    					$("#warn").css("color","#ff0000");
    					$("#warn").html("您的密码有误，请重新输入!");
    					
    				}
    			}
    		});
    }); 
    
    
    $("#email").blur(function(){  
    	var reg = /\w+[@]{1}\w+[.]\w+/;//abc@qq.com
        if($("#email").val() == null || $("#email").val() == ""){
        	$("#flag").css("color","red");
        	$("#flag").html("用户名不能为空！");
        	$("#email").focus();
        }
        $.ajax({
        	url:"../LoginServlet",
        	data:{"method":"ifExit","nemail":$("#email").val()},
        	dataType:"json",
        	type:"post",
        	success:function(msg){
        	if(msg){
        	if(reg.test($("#email").val())){
        		$("#flag").css("color", "green");
        		$("#flag").html("email可以被注册！");
        		flag3 = true;
        	}else{
        	$("#flag").html("email格式不正确！");
        	$("#flag").css("color", "red");
        	flag3 = false;
        	}
        	}else{
        	$("#flag").html("email已经被注册！");
        	$("#flag").css("color", "red");
        	flag3 = false;
        	}
        	}
        });
        });
    
    	$("#pwd").blur(function(){
    	if($("#pwd").val() == ""){
    	$("#flag").html("密码不能为空!");
    	$("#flag").css("color", "red");
    	flag4 = false;
    	$("#pwd").focus();
    	return;
    	}
    	if($("#pwd").val().length < 6){
    	$("#flag").html("密码不能小于六位!");
    	$("#flag").css("color", "red");
    	$("#pwd").focus();
    	flag4 = false;
    	}else{
    	$("#flag").css("color", "green");
    	$("#flag").html("密码是正确格式");
    	flag4 = true;
    	}
    	});


    	$("#passwordRepeat").blur(function(){
    	if($("#passwordRepeat").val() == ""){
    	$("#flag").html("密码不能为空!");
    	$("#flag").css("color", "red");
    	flag5 = false;
    	$("#passwordRepeat").focus();
    	}
    	if($("#passwordRepeat").val() == $("#pwd").val()){
    	$("#flag").css("color", "green");
    	$("#flag").html("密码一致，请注册！");
    	flag5 = true;
    	}else{
    	$("#flag").html("请确保输入密码一致!");
    	$("#flag").css("color", "red");
    	flag5 = false;
    	}
    	});
    	
    	$("#user-name2").blur(function(){
    		if($("#user-name2").val() == ""){
    			$("#flag1").css("color","red");
    			$("#flag1").html("姓名不能为空！");
    			$("#user-name2").focus();
    			msg1 = false;
    		}else{
    			$("#flag1").html(" ");
    			msg1 = true;
    		}
    	});
    	
    	$("#user-qq").blur(function(){
    		if($("#user-qq").val() == ""){
    			$("#flag2").css("color","red");
    			$("#flag2").html("QQ号不能为空！");
    			$("#user-qq").focus();
    			msg2 = false;
    		}else{
    			$("#flag2").html(" ");
    			msg2 = true;
    		}
    	});
    	
    	$("#user-number").blur(function(){
    		if($("#user-number").val() == ""){
    			$("#flag3").css("color","red");
    			$("#flag3").html("借阅编号不能为空！");
    			$("#user-number").focus();
    			msg3 = false;
    		}else{
    			$("#flag3").html(" ");
    			msg3 = true;
    		}
    	});
    	
    	$("#user-place").blur(function(){
    		if($("#user-place").val() == ""){
    			$("#flag4").css("color","red");
    			$("#flag4").html("所属单位不能为空！");
    			$("#user-place").focus();
    			msg4 = false;
    		}else{
    			$("#flag4").html(" ");
    			msg4 = true;
    		}
    	});
    	$("#user-phone").blur(function(){
    		if($("#user-phone").val() == ""){
    			$("#flag5").css("color","red");
    			$("#flag5").html("办公电话不能为空！");
    			$("#user-phone").focus();
    			msg5 = false;
    		}else{
    			$("#flag5").html(" ");
    			msg5 = true;
    		}
    		
    	});
    	
    	$("#user-old-password").blur(function(){
    		 if($("#user-old-password").val() == null || $("#user-old-password").val() == ""){
    	         	$("#wrongmess").css("color","red");
    	         	$("#wrongmess").html("密码不能为空！");
    	         	$("#user-old-password").focus();
    	         }
    	    		$.ajax({
    	    			type:'POST',
    	    			url:'../doChangePass',
    	    			data:{'user':'${user.username}','pwd':$("#user-old-password").val()},
    	    			dataType:'json',
    	    			error:function(data){
//    	    				alert("出错啦！");
    	    			},
    	    			success:function(data){
    	    				var msg = data.success;
    	    				if(msg == "yes"){
    	    					$("#wrongmess").css("color","#00ff00");
    	    					$("#wrongmess").html("您的原始密码正确！");
    	    				}else{
    	    					$("#wrongmess").css("color","#ff0000");
    	    					$("#wrongmess").html("您的密码有误，请重新输入!");
    	    					$("#user-old-password").focus();
    	    				}
    	    			}
    	    		});
    	});
    	
    	
    	
    	
    	$("#user-new-password").blur(function(){
   		 if($("#user-new-password").val() == null || $("#user-new-password").val() == ""){
   	         	$("#wrongmess").css("color","red");
   	         	$("#wrongmess").html("密码不能为空！");
   	         	$("#user-new-password").focus();
   	         }else{
   	        	$(".bgcomplete").css("opacity",1);
   	        	$("#wrongmess").html(" ");
   	         }
    	});
    	
    	$("#user-confirm-password").blur(function(){
      		 if($("#user-confirm-password").val() == null || $("user-confirm-password").val() == ""){
      	         	$("#wrongmess").css("color","red");
      	         	$("#wrongmess").html("密码不能为空！");
      	         	$("#user-confirm-password").focus();
      	         }
      		 if($("#user-confirm-password").val() != $("#user-new-password").val()){
      			$("#wrongmess").css("color","red");
  	         	$("#wrongmess").html("密码不相同，重新输入！");
  	         	$("#user-confirm-password").focus();
      		 }else{
      			$("#wrongmess").html(" ");
      		 }
       	});
    	
    	
    });   

function checkAll(){
	if(flag1 && flag2){
		return true;
	}else{
		return false;
	}
}

function checkAllRegister(){
	if(flag3 && flag4 && flag5){
		return true;
	}else{
		return false;
	}
}

function checkAllInfo(){
	if($("#user-name2").val() != "" && $("#user-qq").val() != "" && $("#user-number").val() != "" && $("#user-phone").val() != "" && $("#user-place").val() != ""){
		return true;
	}else{
		return false;
	}
}

function checkAllPass(){
	if($("#user-old-password").val() != "" && $("#user-new-password").val() != "" && $("#user-confirm-password").val()!=""){
		$(".bgcomplete").css("opacity","1");
		return true;
	}else{
		return false;
	}
}

