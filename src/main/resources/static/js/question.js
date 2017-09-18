function initQuestionBank(){
	$.getJSON("/index/initQuestionBank", function(data) {
		  $("#question_tbody").html("");//清空info内容
		  var questionBodyInfo = "";
	        $.each(data, function(i, item) {
	        	questionBodyInfo += "<tr>";
	        	questionBodyInfo += "<td><span>"+item.name+"</span></td>";
	        	questionBodyInfo += "<td><span>"+item.info+"</span></td>";
	        	questionBodyInfo += "<td><a onclick='alterQuestionBank("+item.id+");'>修改</a>" +
	        			"&nbsp;&nbsp;&nbsp;<a onclick='deleteQuestionBank("+item.id+");'>删除</a>";
	        	questionBodyInfo+="&nbsp;&nbsp;&nbsp;<a onclick='checkQuestionByBank("+item.id+")'>查看题目</a></td>";
	        	questionBodyInfo += "</tr>";
	        });
	        $("#question_tbody").html(questionBodyInfo);
  });
};




function addQuestion(){
		 $("#addQuestionBankModal").modal('show');
		   $('#addQuestionBankForm')[0].reset();
		   $('#addQuestionBankForm').attr('action','/index/addQuestionBank');
		   $("#addQuestionBankForm").validate({
		    	rules:{
		    		name:{
		    			required:true
		    		}
		    	},
		    	messages:{
		    		name:{
		    			required:'请输入题库名称'
		    		}
		    	},
		    	submitHandler:function(form){
		    		$(form).ajaxSubmit({
				  			dataType:'text',
				  			success:function(data){
				  				$('#addQuestionBankForm')[0].reset();
				  				$("#addQuestionBankModal").modal('hide');
			                    alert(data);
			                    initQuestionBank();
				  		    }
				  	     });
		    	     }    
		        });
	};