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
	        	if(item.step=='0'){
	        		paperBodyInfo+="&nbsp;&nbsp;&nbsp;<a  onclick='setPaperQuestionInfo("+item.id+","+item.typeLog+")' >设定试题参数</a></td>";
	        	}
	        	if(item.step=='1'&&item.log=='1'){
	        		paperBodyInfo+="&nbsp;&nbsp;&nbsp;<a  onclick='choosePaperQuestion("+item.id+")' >抽取题目</a></td>";
	        	}
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
function setPaperQuestionInfo(schemaId,type){
	  $("#setPaperQuestionInfoModal").modal('show');
	   $('#setPaperQuestionInfoForm')[0].reset();
	   $('#setPaperQuestionInfoForm').attr('action','/index/setPaperQuestionInfo?paperId='+schemaId);
	   $("#paper_id_text").val(schemaId);
	   
		if(type=='1'){
			$('#wenda_num_div').show();
			$('#wenda_grade_div').show();
		}else{
			$('#wenda_num_div').hide();
			$('#wenda_grade_div').hide();
		}
	   
		$.getJSON("/index/initQuestionBank", function(data) {
			  $("#paper_bank_labels").html("");//清空info内容
			  var questionLabelInfo = "";
		        $.each(data, function(i, item) {
		        	questionLabelInfo += "<input name='questionLabels' type='checkbox' id='checkbox"+item.id+"'  value='"+item.id+"'>" ;
		            questionLabelInfo += " <label for='checkbox"+item.id+"'>"+item.name+"</label>"
		        });
		        $("#paper_bank_labels").html(questionLabelInfo);
	     });
		
	   
	   $("#setPaperQuestionInfoForm").validate({
	    	rules:{
	    		panduanNum:{
	    			required:true
	    		},
	    		panduanGrade:{
	    			required:true
	    		},
	    		danxuanNum:{
	    			required:true
	    		},
	    		danxuanGrade:{
	    			required:true
	    		},
	    		duoxuanNum:{
	    			required:true
	    		},
	    		duoxuanGrade:{
	    			required:true
	    		}
	    	},
	    	messages:{
	    		panduanNum:{
	    			required:'请输入题目数量'
	    		},
	    		panduanGrade:{
	    			required:'请输入题目分数'
	    		},
	    		danxuanNum:{
	    			required:'请输入题目数量'
	    		},
	    		danxuanGrade:{
	    			required:'请输入题目分数'
	    		},
	    		duoxuanNum:{
	    			required:'请输入题目数量'
	    		},
	    		duoxuanGrade:{
	    			required:'请输入题目分数'
	    		}
	    	},
	    	submitHandler:function(form){
	    		$(form).ajaxSubmit({
			  			dataType:'text',
			  			success:function(data){
			  				$('#setPaperQuestionInfoForm')[0].reset();
			  				$("#setPaperQuestionInfoModal").modal('hide');
		                    alert(data);
		                    initPaperSchema();
			  		    }
			  	     });
	    	     }    
	        });
};


function choosePaperUser(){
	  $("#choosePaperUserModal").modal('show');
	  
		$.getJSON("/index/getOrgUsers", function(data) {
			  $("#choose_paper_user_tbody").html("");//清空info内容
			    var orgUserInfo = "";
		        $.each(data, function(i, item) {
		        	orgUserInfo += "<tr>";
		        	orgUserInfo += "<td> <input type='checkbox' id='org"+item.id+"'  value='"+item.id+"' onClick='changeUserCheckBox("+item.id+")'>"+item.orgName+"</td>";
		        	 orgUserInfo += "<td>";
		        	 $.each(item.users,function(j,user){
		        		 orgUserInfo += "<input type='checkbox'  id='user"+user.id+"' name='orgPaperUser' class='org_user_choose_"+item.id+"'  value='"+user.id+"^"+user.userName+"'>"+user.userName+"&nbsp;&nbsp;";
		        	 });
		        	 orgUserInfo += "</td>";
		        	orgUserInfo += "</tr>";
		        });
		        $("#choose_paper_user_tbody").html(orgUserInfo);
	     });
	  
};


function changeUserCheckBox(orgId){
	if($("#org"+orgId).is(':checked')){
		$(".org_user_choose_"+orgId).prop("checked",true);
	}else{
		$(".org_user_choose_"+orgId).prop("checked",false);
	}
};


function setPaperUserText(){
	  var ids = "";
	  var names = "";
	  $('input[name="orgPaperUser"]:checked').each(function(){
		        var str = $(this).val();
				ids += str.split("^")[0]+",";
				names += str.split("^")[1]+",";
	  }); 
	  ids = ids.substr(0, ids.length - 1);  
	  names = names.substr(0, names.length - 1);  
	   $("#choose_user_id").val(ids);
	   $("#choose_user_name").val(names);
	   $('#choosePaperUserForm')[0].reset();
	   $("#choosePaperUserModal").modal('hide');
};



/**
 * 抽取题目
 * @param paperId
 * @param paperType
 * @returns
 */
function choosePaperQuestion(paperId){
	    $("#choosePaperQuestionModal").modal('show');
	  
		$.getJSON("/index/getPaperFitQuestions?paperId="+paperId, function(data) {
			  $("#choose_paper_question_tbody").html("");//清空info内容
			    var orgQuestionInfo = "";
		        $.each(data, function(i, item) {
		        	orgQuestionInfo += "<tr>";
		        	orgQuestionInfo += "<td> <input type='checkbox'  name='targetQuestions'  value='"+item.id+"'>";
		        	orgQuestionInfo += item.title+"</td>";
		        	orgQuestionInfo +="<td>"+ item.type+"</td>";
		        	orgQuestionInfo += "</tr>";
		        });
		        $("#choose_paper_question_tbody").html(orgQuestionInfo);
	     });
		
		 $('#choosePaperQuestionForm').attr('action','/index/setPaperQuestionsByChoose?paperId='+paperId);
		 $("#choosePaperQuestionForm").validate({
				submitHandler:function(form){
				    		$(form).ajaxSubmit({
					  			dataType:'text',
					  			success:function(data){
					  				$('#choosePaperQuestionForm')[0].reset();
					  				$("#choosePaperQuestionModal").modal('hide');
				                    alert(data);
				                    initPaperSchema();
					  		    }
					  	     });
				}
		 });
};

/**
 * 生成答题试卷
 * @param userPaperId
 * @returns
 */
function  initQuestions(userPaperId){
	
	var answers = new Array()
	answers[0]="A";
	answers[1]="B";
	answers[2]="C";
   answers[3]="D";
   answers[4]="E";
   answers[5]="F";
	
	$.getJSON("/index/showPaperQuestions?userPaperId="+userPaperId, function(data) {
		  $("#panduan_question_div").html(""); 
		  $("#danxuan_question_div").html(""); 
		  $("#duoxuan_question_div").html(""); 
		  $("#wenda_question_div").html(""); 
		  var panduanInfo = "";
		  var danxuanInfo = "";
		  var duoxuanInfo = "";
		  var wendaInfo = "";
		  
		  if(data.panduan.length>0){
			    panduanInfo+="";
		        $.each(data.panduan, function(i, item) {
		        	panduanInfo += "<div class='exam-padding-top'><span>"+(i+1)+"、"+item.title+"</span></div>";
	        		panduanInfo +=  "<div class='exam-padding-top'><input  type='checkbox' name='panduan"+item.id+"'  value='0'><span>对</span></div>";
	        		panduanInfo +=  "<div class='exam-padding-top'><input  type='checkbox' name='panduan"+item.id+"'  value='1'><span>错</span></div>";
	        		panduanInfo += "<hr>";
		        });
		        $("#panduan_question_div").html(panduanInfo);
		  }
		  if(data.danxuan.length>0){
			    danxuanInfo+="";
		        $.each(data.danxuan, function(i, item) {
		        	danxuanInfo += "<div class='exam-padding-top'><span>"+(i+1)+"、"+item.title+"</span></div>";
		        	var contents = item.content.split("#");
		        	for(var i = 0;i < contents.length; i++) {
		        		danxuanInfo += "<div class='exam-padding-top'><input  type='radio' name='danxuan"+item.id+"'  value='"+answers[i]+"'><span>";
		        		danxuanInfo += answers[i]+"、"+contents[i]+"</span></div>";
		            }
		        	danxuanInfo += "<hr>";
		        });
		        $("#danxuan_question_div").html(danxuanInfo);
		  }
		  if(data.duoxuan.length>0){
			    duoxuanInfo+="";
		        $.each(data.duoxuan, function(i, item) {
		        	duoxuanInfo += "<div class='exam-padding-top'><span>"+(i+1)+"、"+item.title+"</span></div>";
		        	var contents = item.content.split("#");
		        	for(var i = 0;i < contents.length; i++) {
		        		duoxuanInfo += "<div class='exam-padding-top'><input  type='checkbox' name='duoxuan"+item.id+"'  value='"+answers[i]+"'><span>";
		        		duoxuanInfo += answers[i]+"、"+contents[i]+"</span></div>";
		            }
		        	duoxuanInfo += "<hr>";
		        });
		        $("#duoxuan_question_div").html(duoxuanInfo);
		  }
		  if(data.wenda.length>0){
			   wendaInfo+="";
		        $.each(data.wenda, function(i, item) {
		        	wendaInfo += "<div class='exam-padding-top'><span>"+(i+1)+"、"+item.title+"</span></div>";
		        	//wendaInfo +=  "<br><br><br><br><br><br><br><br>";
		        	wendaInfo += "<textarea name='wenda"+item.id+"'  rows='8' cols='100' style='border:none;'></textarea>";
	        		wendaInfo += "<hr>";
		        });
		        $("#wenda_question_div").html(wendaInfo);
		  }
 
		  

});
};