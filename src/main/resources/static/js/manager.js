function initWaitForRegistUserInfo(){
	
};


function initRegistedUserInfo(){
	 
}

/**
 * 初始化机构列表
 * @returns
 */
function initOrg(parentId){
	$.getJSON("/index/initOrgList?parentId="+parentId, function(data) {
		  $("#org_tbody").html("");//清空info内容
		  var orgBodyInfo = "";
	        $.each(data, function(i, item) {
	        	orgBodyInfo += "<tr>";
	        	orgBodyInfo += "<td><span>"+item.orgName+"</span></td>";
	        	orgBodyInfo += "<td><span>"+item.tel+"</span></td>";
	        	orgBodyInfo += "<td><span>"+item.master+"</span></td>";
	        	if(item.childCount=="0"){
	        		orgBodyInfo += "<td><a>修改</a></td>";
	        	}else{
	        		orgBodyInfo += "<td><a>修改</a><a>查看辖区机构</a></td>";
	        	}
	        	
	        	orgBodyInfo += "</tr>";
	        });
	        $("#org_tbody").html(orgBodyInfo );
    });
}

/**
 * 打开添加机构界面
 * @returns
 */
 function openAddOrgPage(){
	 var $browser=$(window);
	 
	    var winWidth;
	    var winHeight;
	   
	    var scrollLeft;
	    var scrollTop;
	   
	    var left=0,top=0;
	    var $currentWin=$("div.win");//弹出窗口
	    var clientWidth;
	    var clientHeight;
	    
	 winWidth=$browser.width();
     winHeight=$browser.height();
    
     scrollLeft=$browser.scrollLeft();
     scrollTop=$browser.scrollTop();
    
     clientWidth=$currentWin.outerWidth();
     clientHeight=$currentWin.outerHeight();

     left=scrollLeft+(winWidth-clientWidth)/2;
     top=scrollTop+(winHeight-clientHeight)/2;   
     
	 window.open("/manage/toAddOrg","","top="+top+"px,left="+left+"px,width=700,height=500");
 }
 
 