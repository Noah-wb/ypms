		$.ajax({
			url:"http://api.bajin.com/user/getUserinfo",
			type:"get",
			xhrFields: {withCredentials: true},
			crossDomain: true,
			success:function(data){
				if(data.status == 0){//获取用户信息成功
					$("#id_login_btn").text(data.data.name).css("color","red");
					$("#id_register_btn").text("");
				}else{//获取用户信息失败
					$("#id_loginout_btn").text("");
				}
			},
			error:function(){
				
			},
			dataType:"json"
		});
		
		//注销登录
		$("#loginOut").click(function(){
			$.ajax({
				url:"http://api.bajin.com/user/loginOut",
				type:"get",
				xhrFields: {withCredentials: true},
				crossDomain: true,
				success:function(data){
					window.location.href = "index.html";
				},
				error:function(){
					
				},
				dataType:"json"
			});
		});