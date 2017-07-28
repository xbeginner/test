 
 function login(){
	 if($("#name").val()==null||$("#name").val()==""){
		 $("#username_log").html(" <font color='red'>请输入用户名</font>");
	 }else if($("#password").val()==null||$("#password").val()==""){
		 $("#password_log").html(" <font color='red'>请输入密码</font>");
	 }else{
			$.ajax({
					cache: false,
					type: "POST",
					url:"/login/checkLogin", 
                    data:$('#loginForm').serialize(), 
					async: false,
					error: function(request) {
						 alert("发送请求失败！");
					},
					success: function(data) {
				         if(data=='username'){
				        	 $("#username_log").html(" <font color='red'>用户名错误</font>");
				         }else if(data=='password'){
				        	 $("#password_log").html(" <font color='red'>密码错误</font>");
				         }else if(data=='tomuch'){
				        	 alert("您输入错误次数过多，请10分钟后尝试");
				         } else{
                              StandardPost('/login/openMainPage',{userId:data});
				         } 
					}
			});
	}
 };
 
 
 function changeLog(type){
	 
  switch (type) {
	case 0:
	   $("#username_log").html("");
		break;
	case 1:
	   $("#password_log").html("");
		break;
	default:
		break;
	}
	 
 };
 
 

 function StandardPost(url,args){
     var form = $("<form method='post'></form>");
     var  input;
     form.attr({"action":url});
     $.each(args,function(key,value){
         input = $("<input type='hidden'>");
         input.attr({"name":key});
         input.val(value);
         form.append(input);
     });
     form.appendTo("body").submit();
     form.remove();  
 };

 
 