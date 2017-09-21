function initPaperSchema(){
	$.getJSON("/index/initPaperSchema", function(data) {
		  $("#paperSchema_tbody").html("");//清空info内容
		  var paperBodyInfo = "";
	        $.each(data, function(i, item) {
	        	paperBodyInfo += "<tr>";
	        	paperBodyInfo += "<td><span>"+item.paperName+"</span></td>";
	        	paperBodyInfo += "<td><span>"+item.doTime+"</span></td>";
	        	paperBodyInfo += "<td><span>"+item.type+"</span></td>";
	        	paperBodyInfo += "<td><a onclick='alterPaperSchema("+item.id+");'>修改</a>" +
	        			"&nbsp;&nbsp;&nbsp;<a onclick='deletePaperSchema("+item.id+");'>删除</a>";
	        	paperBodyInfo+="&nbsp;&nbsp;&nbsp;<a  onclick='setPaperQuestionInfo("+item.id+")' >设定试题参数</a></td>";
	        	paperBodyInfo += "</tr>";
	        });
	        $("#paperSchema_tbody").html(paperBodyInfo);
  });
};


function addPaperSchema(){
	  $("#addPaperSchemaModal").modal('show');
	   $('#addPaperSchemaForm')[0].reset();
	   $('#addPaperSchemaForm').attr('action','/index/addPaperSchema');
	   $("#addPaperSchemaForm").validate({
	    	rules:{
	    		paperName:{
	    			required:true
	    		},
	    		doTime:{
	    			required:true
	    		}
	    	},
	    	messages:{
	    		paperName:{
	    			required:'请输入试卷方案名称'
	    		},
	    		doTime:{
	    			required:'请输入考试时长'
	    		}
	    	},
	    	submitHandler:function(form){
	    		$(form).ajaxSubmit({
			  			dataType:'text',
			  			success:function(data){
			  				$('#addPaperSchemaForm')[0].reset();
			  				$("#addPaperSchemaModal").modal('hide');
		                    alert(data);
		                    initPaperSchema();
			  		    }
			  	     });
	    	     }    
	        });
};


/**
 * 设定试卷试题参数
 * @param schemaId
 * @returns
 */
function setPaperQuestionInfo(schemaId){
	
}