function initMainMenu(){
	$.ajax({
				cache: false,
				type: "POST",
				url:"/initMainMenu", 
				error: function(request) {
					 alert("发送请求失败！");
				},
				success: function(data) {
					  alert(data);
				}
    });
}