function initWaitForRegistUserInfo(){
	$.getJSON("/index/showNoRegistUserInfo", function(data) {
		  $("#org_tbody").html("");//清空info内容
		  var orgBodyInfo = "";
	        $.each(data, function(i, item) {
	        	orgBodyInfo += "<tr>";
	        	orgBodyInfo += "<td><span>"+item.username+"</span></td>";
	        	orgBodyInfo += "<td><span>"+item.org+"</span></td>";
	        	orgBodyInfo += "<td><span>"+item.tel+"</span></td>";
	        	orgBodyInfo += "<td><a class='btn_success' onclick='#'>通过</a>&nbsp;&nbsp;&nbsp;<a class='btn_danger' onclick='#'>拒绝</a></td>";
	        	orgBodyInfo += "</tr>";
	        });
	    
	        $("#not_regist_tbody").html(orgBodyInfo );
  });
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
	        		orgBodyInfo += "<td><a onclick='toAlterOrg("+item.id+");'>修改</a></td>";
	        	}else{
	        		orgBodyInfo += "<td><a onclick='toAlterOrg("+item.id+");'>修改</a>&nbsp;&nbsp;&nbsp;<a onclick='showOwnOrg("+item.id+");'>查看辖区机构</a></td>";
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
 
 /**
  * 修改org
  * @param orgId
  * @returns
  */
 function toAlterOrg(orgId){
	    $("#addOwnOrgModal").modal('show');
	    
	    $.getJSON("/index/showOrgInfo?orgId="+orgId, function(data) {
	    	  $('#orgId').val(data.id);
	          $('#orgName').val(data.orgName);
	          $('#tel').val(data.tel);
	          $('#address').val(data.address);
	          $('#master').val(data.master);
	          $('#masterTel').val(data.masterTel);
	     });
		 

	    
 };
 
 
 function alterOrg(){
	 
	 $('#orgForm').ajaxSubmit({
  			url:'/index/alterOrg',
  			dataType:'text',
  			success:function(data){
  				$("#addOwnOrgModal").modal('hide');
  				$('#orgForm')[0].reset();
               alert(data);
               initOrg(0);
  		    }
  	     });
	 return false;
 };
 
 
 function showOwnOrg(orgId){
		$.getJSON("/index/showOwnOrgList?parentId="+orgId, function(data) {
			  $("#org_tbody").html("");//清空info内容
			  var orgBodyInfo = "";
		        $.each(data, function(i, item) {
		        	orgBodyInfo += "<tr>";
		        	orgBodyInfo += "<td><span>"+item.orgName+"</span></td>";
		        	orgBodyInfo += "<td><span>"+item.tel+"</span></td>";
		        	orgBodyInfo += "<td><span>"+item.master+"</span></td>";
	                orgBodyInfo += "<td><a onclick='toAlterOrg("+item.id+");'>修改</a>&nbsp;&nbsp;&nbsp;<a onclick='delOrg("+item.id+","+orgId+");'>删除</a></td>";
		        	orgBodyInfo += "</tr>";
		        });
		    
		        $("#org_tbody").html(orgBodyInfo );
	    });

 }
 
 function addOwnOrg(){
	 $("#addOwnOrgModal").modal('show');
	 
	    $('#orgForm').submit(function() { 
			 $('#orgForm').ajaxSubmit({
		  			url:'/index/addOwnOrg',
		  			dataType:'text',
		  			success:function(data){
		  				$("#addOwnOrgModal").modal('hide');
		  				$('#orgForm')[0].reset();
                    alert(data);
                    initOrg(0);
		  		    }
		  	     });
			 return false;
	    }); 
	
 };
 
 
 function delOrg(orgId,parentId){
	  var delete_org_options = {
				url:"/index/deleteOrg?id="+orgId,
				dataType:'text',	
				success:function(data){
					alert(data);
					showOwnOrg(parentId);
				}
			 }
			  $.ajax(delete_org_options);
 }
 