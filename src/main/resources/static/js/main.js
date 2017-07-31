function initMainMenu(){
   $.getJSON("/index/initMainMenu", function(data) {
  
	        $("#menuContentUl").html("");//清空info内容
	        $.each(data, function(i, item) {
	            $("#menuContentUl").html( "" +
	            		"<li ><a href='"+item.url+"'><span>"+item.name+"</span></a></li>"
	            );
	        });
	  });
	  }