function initMainMenu(){
 
   $.getJSON("/index/initMainMenu", function(data) {
	        $("#menuContentUl").html("");//清空info内容
	        var menuInfo ;
	        $.each(data, function(i, item) {
	        	menuInfo+="<li ><a href='"+item.url+"'><span>"+item.name+"</span></a></li>";
	        });
	        $("#menuContentUl").html(menuInfo);
	     });
 };
 
 
 function   initExamInfo(pageNum){
	  
	 $.getJSON("/index/initExamInfo?page="+pageNum, function(data) {
	        $("#exam_tbody").html("");//清空info内容
	        $("#exam_page").html("");
	        var examBodyInfo ;
	        var examPageInfo;
	        $.each(data.info, function(i, item) {
	        	examBodyInfo += "<tr>";
	        	examBodyInfo += "<td>"+item.name+"</td>";
	        	examBodyInfo += "<td>"+item.time+"</td>";
	        	examBodyInfo += "<td><a>参加</a></td>";
	        	examBodyInfo += "</tr>";
	        });
	        $("#exam_tbody").html(examBodyInfo );
	        
     	   if(pageNum==0||pageNum==1){
    		   examPageInfo += "<a class='page_prev' href='initExamInfo("+before+")'><span>‹</span></a>";   
    	   }else{
    		   examPageInfo += "<a class='page_prev' href='initExamInfo(1)'><span>‹</span></a>";   
    	   }
           for(var index=1;index<data.page+1;index++){
        	   examPageInfo += "<a href='initExamInfo("+index+")' class='page-numbers'>2</a>";
           }
           if(pageNum>=data.page){
        	   examPageInfo += "<a class='page_next' href='initExamInfo("+data.page+")'><span>›</span></a>";
           }else{
        	   examPageInfo += "<a class='page_next' href='initExamInfo("+(pageNum+1)+")'><span>›</span></a>";
           }

        	   $("#exam_page").html(examPageInfo );
 
	     });
 };