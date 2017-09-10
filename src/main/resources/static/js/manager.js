function initWaitForRegistUserInfo(){
	$.getJSON("/index/showNoRegistUserInfo", function(data) {
		  $("#not_regist_tbody").html("");//清空info内容
		  var orgBodyInfo = "";
	        $.each(data, function(i, item) {
	        	orgBodyInfo += "<tr>";
	        	orgBodyInfo += "<td><span>"+item.userName+"</span></td>";
	        	orgBodyInfo += "<td><span>"+item.orgName+"</span></td>";
	        	orgBodyInfo += "<td><span>"+item.tel+"</span></td>";
	        	orgBodyInfo += "<td><a class='btn_success' onclick='passRegister("+item.id+");'>通过</a>&nbsp;&nbsp;&nbsp;<a class='btn_danger' onclick='noPassRegister("+item.id+");'>拒绝</a></td>";
	        	orgBodyInfo += "</tr>";
	        });
	    
	        $("#not_regist_tbody").html(orgBodyInfo );
  });
};


function initRegistedUserInfo(){
	$.getJSON("/index/showOwnUserInfo", function(data) {
		  $("#has_regist_tbody").html("");//清空info内容
		  var orgBodyInfo = "";
	        $.each(data, function(i, item) {
	        	orgBodyInfo += "<tr>";
	        	orgBodyInfo += "<td><span>"+item.userName+"</span></td>";
	        	orgBodyInfo += "<td><span>"+item.orgName+"</span></td>";
	        	orgBodyInfo += "<td><span>"+item.tel+"</span></td>";
	        	orgBodyInfo += "<td><a class='btn_success' onclick='toAlterUserInfo("+item.id+")'>修改</a>&nbsp;&nbsp;&nbsp;<a class='btn_danger' onclick='deleteUserInfo("+item.id+")'>删除</a></td>";
	        	orgBodyInfo += "</tr>";
	        });
	    
	        $("#has_regist_tbody").html(orgBodyInfo );
});
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
	        		orgBodyInfo += "<td><a onclick='alterOrg("+item.id+");'>修改</a></td>";
	        	}else{
	        		orgBodyInfo += "<td><a onclick='alterOrg("+item.id+");'>修改</a>&nbsp;&nbsp;&nbsp;<a onclick='showOwnOrg("+item.id+");'>查看辖区机构</a></td>";
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
 

 
 
 function alterOrg(orgId){
	 
       $("#addOwnOrgModal").modal('show');
	    
	    $.getJSON("/index/showOrgInfo?orgId="+orgId, function(data) {
	    	  $('#orgId').val(data.id);
	          $('#orgName').val(data.orgName);
	          $('#tel').val(data.tel);
	          $('#address').val(data.address);
	          $('#master').val(data.master);
	          $('#masterTel').val(data.masterTel);
	     });
	    
	    
	    $("#orgForm").validate({
	    	rules:{
	    		orgName:{
	    			required:true
	    		},
	    		tel:{
	    			required:true
	    		},
	    		address:{
	    			required:true
	    		},
	    		master:{
	    			required:true
	    		}
	    	},
	    	messages:{
	    		orgName:{
	    			required:'请输入名称'
	    		},
	    		tel:{
	    			required:'请输入电话号码'
	    		},
	    		address:{
	    			required:'请输入地址'
	    		},
	    		master:{
	    			required:'请输入联系人'
	    		}
	    	},
	    	submitHandler:function(form){
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
	             }    
	        });
 
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
	                orgBodyInfo += "<td><a onclick='alterOrg("+item.id+");'>修改</a>&nbsp;&nbsp;&nbsp;<a onclick='delOrg("+item.id+","+orgId+");'>删除</a></td>";
		        	orgBodyInfo += "</tr>";
		        });
		    
		        $("#org_tbody").html(orgBodyInfo );
	    });

 }
 
 function addOwnOrg(){
	   $("#addOwnOrgModal").modal('show');
	   $('#orgForm')[0].reset();
	   
	   $("#orgForm").validate({
	    	rules:{
	    		orgName:{
	    			required:true
	    		},
	    		tel:{
	    			required:true
	    		},
	    		address:{
	    			required:true
	    		},
	    		master:{
	    			required:true
	    		}
	    	},
	    	messages:{
	    		orgName:{
	    			required:'请输入名称'
	    		},
	    		tel:{
	    			required:'请输入电话号码'
	    		},
	    		address:{
	    			required:'请输入地址'
	    		},
	    		master:{
	    			required:'请输入联系人'
	    		}
	    	},
	    	submitHandler:function(form){
	    		 $('#orgForm').ajaxSubmit({
			  			url:'/index/addOwnOrg',
			  			dataType:'text',
			  			success:function(data){
			  				$("#addOwnOrgModal").modal('hide');
		                    alert(data);
		                    initOrg(0);
			  		    }
			  	     });
	    	   }    
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
 };
 
 
 function initRegisterOrgs(){
	 $.getJSON("/manage/getAllRegisteOrgs", function(data) {
		  $("#org_select").html("");//清空info内容
		  var orgBodyInfo = "";
	        $.each(data, function(i, item) {
	        	orgBodyInfo += "<option value="+item.id+">"+item.orgName+"</option>";
	        });
	    
	        $("#org_select").html(orgBodyInfo );
   });
 };
 
 
 
 function passRegister(userId){
	  var pass_register_options = {
				url:"/index/passRegister?id="+userId,
				dataType:'text',	
				success:function(data){
					initWaitForRegistUserInfo();
					initRegistedUserInfo();
					alert(data);
				}
			 }
			  $.ajax(pass_register_options);
 };
 
 
 function noPassRegister(userId){
	 var nopass_register_options = {
				url:"/index/noPassRegister?id="+userId,
				dataType:'text',	
				success:function(data){
					initWaitForRegistUserInfo();
					initRegistedUserInfo();
					alert(data);
				}
			 }
			  $.ajax(nopass_register_options);
 };
 
 
 
 function alterUserInfo(userId){
	 
	 $("#alterUserInfoModal").modal('show');
	    
	    $.getJSON("/index/showUserInfo?id="+userId, function(data) {
	    	  $('#userId').val(data.id);
	          $('#userName').val(data.userName);
	          $('#userTel').val(data.tel);
	          $('#idcar').val(data.idcard);
	     });
	    
	 
	 $("#alterUserInfoForm").validate({
			rules:{
				userName:{
					required:true
				},
				tel:{
					required:true
				},
				idcard:{
					required:true
				}
			},
			messages:{
				userName:{
					required:'请输入姓名'
				},
				tel:{
					required:'请输入电话号码'
				},
				idcard:{
					required:'请输入身份证号'
				}
			},
	    	submitHandler:function(form){
	    		 $('#alterUserInfoForm').ajaxSubmit({
			  			url:'/index/alterUserInfo',
			  			dataType:'text',
			  			success:function(data){
			  				$("#alterUserInfoModal").modal('hide');
							initRegistedUserInfo();
							alert(data);
			  		    }
			  	     });
	    	   }    
	        });
	 
 };
 
 
 function deleteUserInfo(userId){
	 
 }