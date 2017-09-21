function initPaperSchema(){
	$.getJSON("/index/initPaperSchema", function(data) {
		  $("#paperSchema_tbody").html("");//清空info内容
		  var paperBodyInfo = "";
	        $.each(data, function(i, item) {
	        	paperBodyInfo += "<tr>";
	        	paperBodyInfo += "<td><span>"+item.paperName+"</span></td>";
	        	paperBodyInfo += "<td><span>"+item.startTime+"</span></td>";
	        	paperBodyInfo += "<td><span>"+item.endTime+"</span></td>";
	        	paperBodyInfo += "<td><span>"+item.type+"</span></td>";
	        	paperBodyInfo += "<td><a onclick='alterPaperSchema("+item.id+");'>修改</a>" +
	        			"&nbsp;&nbsp;&nbsp;<a onclick='deletePaperSchema("+item.id+");'>删除</a>";
	        	if(item.log=='1'){
	        		paperBodyInfo+="&nbsp;&nbsp;&nbsp;<a  onclick='#' >选择题目</a></td>";
	        	}
	        	paperBodyInfo += "</tr>";
	        });
	        $("#paperSchema_tbody").html(paperBodyInfo);
  });
};
