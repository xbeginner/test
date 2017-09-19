function initQuestionBank(){
	$.getJSON("/index/initQuestionBank", function(data) {
		  $("#questionbank_tbody").html("");//清空info内容
		  var questionBodyInfo = "";
	        $.each(data, function(i, item) {
	        	questionBodyInfo += "<tr>";
	        	questionBodyInfo += "<td><span>"+item.name+"</span></td>";
	        	questionBodyInfo += "<td><span>"+item.info+"</span></td>";
	        	questionBodyInfo += "<td><a onclick='alterQuestionBank("+item.id+");'>修改</a>" +
	        			"&nbsp;&nbsp;&nbsp;<a onclick='deleteQuestionBank("+item.id+");'>删除</a>";
	        	questionBodyInfo+="&nbsp;&nbsp;&nbsp;<a  href='/index/manageQuestion?id="+item.id+"' target='_blank'>查看题目</a></td>";
	        	questionBodyInfo += "</tr>";
	        });
	        $("#questionbank_tbody").html(questionBodyInfo);
  });
};




function addQuestionBank(){
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
	
	
	function  alterQuestionBank(bankId){
		 $("#addQuestionBankModal").modal('show');
		 $('#addQuestionBankForm')[0].reset();
		 $('#addQuestionBankForm').attr('action','/index/alterQuestionBank?id='+bankId);
		    $.getJSON("/index/showQuestionBankInfo?id="+bankId, function(data) {
		          $('#questionBank_name').val(data.name);
		          $('#questionBank_info').val(data.info);
		     });
		 
		 $("#addQuestionBankForm").validate({
				rules:{
					name:{
						required:true
					} 
				},
				messages:{
					name:{
						required:'请输入名称'
					} 
				},
		    	submitHandler:function(form){
		    		$(form).ajaxSubmit({
				  			resetForm:true,
				  			dataType:'text',
				  			success:function(data){
				  				$("#addQuestionBankModal").modal('hide');
				  				initQuestionBank();
								alert(data);
				  		    }
				  	     });
		    	    }    
		        });
	};
	
	
	
	 function deleteQuestionBank(bankId){
		  var delete_questionBank_options = {
					url:"/index/deleteQuestionBank?id="+bankId,
					dataType:'text',	
					success:function(data){
						initQuestionBank();
							alert(data);
					}
				 }
				  $.ajax(delete_questionBank_options);
	 };
	 
	 
	 function initQuestionByBank(){
		var bankId = $("#bankid_text").val();
		$.getJSON("/index/initQuestionByBank?bankId="+bankId, function(data) {
			  $("#question_tbody").html("");//清空info内容
			  var questionBodyInfo = "";
		        $.each(data, function(i, item) {
		        	questionBodyInfo += "<tr>";
		        	questionBodyInfo += "<td><span>"+item.title+"</span></td>";
		        	questionBodyInfo += "<td><span>"+item.type+"</span></td>";
		        	questionBodyInfo += "<td><span>"+item.banks+"</span></td>";
		        	questionBodyInfo += "<td><a onclick='alterQuestion("+item.id+");'>修改</a>" +
		        			"&nbsp;&nbsp;&nbsp;<a onclick='deleteQuestion("+item.id+");'>删除</a>";
		        	questionBodyInfo += "</tr>";
		        });
		        $("#question_tbody").html(questionBodyInfo);
	     });
	 };
	 
	 
	 function addQuestion(){
		   $("#addQuestionModal").modal('show');
		   $('#addQuestionForm')[0].reset();
		   $('#addQuestionForm').attr('action','/index/addQuestion');
		   
		   
			$.getJSON("/index/initQuestionBank", function(data) {
				  $("#bank_labels").html("");//清空info内容
				  var questionLabelInfo = "";
			        $.each(data, function(i, item) {
			        	questionLabelInfo += "<input name='questionLabels' type='checkbox' id='checkbox"+item.id+"'  value='"+item.id+"' >"+item.name ;
			        });
			        $("#bank_labels").html(questionLabelInfo);
		     });
		   
		   
		   $("#addQuestionForm").validate({
		    	rules:{
		    		name:{
		    			required:true
		    		},
		    		answer:{
		    			required:true
		    		}
		    	},
		    	messages:{
		    		name:{
		    			required:'请输入题干'
		    		},
		    		answer:{
		    			required:'请输入答案'
		    		}
		    	},
		    	submitHandler:function(form){
		    		$(form).ajaxSubmit({
				  			dataType:'text',
				  			success:function(data){
				  				$('#addQuestionForm')[0].reset();
				  				$("#addQuestionForm").modal('hide');
			                    alert(data);
			                    initQuestionByBank();
				  		    }
				  	     });
		    	     }    
		        });
	 };
	
	 
	 