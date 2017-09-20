package com.xx.test.Controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xx.test.Model.Message;
import com.xx.test.Model.Question;
import com.xx.test.Model.QuestionBank;
import com.xx.test.Model.UserInfo;

@Controller
public class QuestionController extends BaseController {
	
	  @RequestMapping(value="/index/manageQuestionBank",method=RequestMethod.GET)
	  public ModelAndView manageQuestionBank(HttpServletRequest request , HttpServletResponse response){
		  UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
		  ModelAndView modelAndView = new ModelAndView("manageQuestionBank");
		  modelAndView.addObject("userInfo",userInfo);
		  return modelAndView;
	  }
	  
	  
	  @RequestMapping(value="/index/initQuestionBank",method=RequestMethod.GET)
	  @ResponseBody
	  public String initQuestionBank(HttpServletRequest request , HttpServletResponse response){
		      StringBuffer json = new StringBuffer();
		      json.append("[");
		      UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
		      Long orgId = userInfo.getOrg().getId();
		      List<QuestionBank> questionBanks = this.questionBankService.findQuestionBankByOrg(orgId);
		      if(questionBanks.size()==0){
		    	  return "[]";
		      }
		      for(QuestionBank q:questionBanks){
		    	    json.append(q.getQuestionBankJson());
		    	    json.append(",");
		      }
		      json.deleteCharAt(json.length()-1);
		      json.append( "]" );
		      return json.toString();
	  }
	  
	  
	    @PostMapping(value="/index/addQuestionBank")
	    @ResponseBody
	    public String addQuestionBank(HttpServletRequest request , HttpServletResponse response) {
		         UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
		         QuestionBank questionBank = new QuestionBank();
		         questionBank.setName(request.getParameter("name"));
		         questionBank.setInfo(request.getParameter("info"));
		         questionBank.setOrgId(userInfo.getOrg().getId());
		         this.questionBankService.saveQuestionBank(questionBank);
	             return SUCCESS;
	    }
	  
	  
	  
	    @GetMapping("/index/showQuestionBankInfo")
	    @ResponseBody
	    public String showQuestionBankInfo(HttpServletRequest request , HttpServletResponse response) {
	    	Long id = Long.valueOf(request.getParameter("id"));
	    	QuestionBank questionBank = questionBankService.findQuestionBankById(id);
	    	String json = questionBank.getQuestionBankJson();
	        return json;
	    }
	    
	    
	    
	    @PostMapping(value="/index/alterQuestionBank")
	    @ResponseBody
	    public String alterQuestionBank(HttpServletRequest request , HttpServletResponse response) {
	    	     Long id = Long.valueOf(request.getParameter("id"));
	    	     QuestionBank questionBank = questionBankService.findQuestionBankById(id);
	    	     questionBank.setName(request.getParameter("name"));
	    	     questionBank.setInfo(request.getParameter("info"));
	    	     questionBankService.alterQuestionBank(questionBank);
	             return SUCCESS;
	    }
    
	  
	  
	  @GetMapping(value="/index/deleteQuestionBank")
	    @ResponseBody
	    public String deleteQuestionBank(HttpServletRequest request , HttpServletResponse response) {
	    	     Long id = Long.valueOf(request.getParameter("id"));
	    	     questionBankService.deleteQuestionBank(id);
	             return SUCCESS;
	    }
	  
	  
	  @RequestMapping(value="/index/manageQuestion",method=RequestMethod.GET)
	  public ModelAndView manageQuestion(HttpServletRequest request , HttpServletResponse response){
		  UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
		  ModelAndView modelAndView = new ModelAndView("manageQuestion");
		  String bankId = request.getParameter("id");
		  QuestionBank questionBank = questionBankService.findQuestionBankById(Long.valueOf(bankId));
		  modelAndView.addObject("userInfo",userInfo);
		  modelAndView.addObject("questionBank",questionBank);
		  return modelAndView;
	  }
	  
	  
	  
	    @PostMapping(value="/index/addQuestion")
	    @ResponseBody
	    public String addQuestion(HttpServletRequest request , HttpServletResponse response) {
	    	     Question question = new Question();
		         String title = request.getParameter("title");
		         question.setTitle(title);
		         String answer = request.getParameter("answer");
		         question.setAnswer(answer);
		         String questionType = request.getParameter("questionType");
		         question.setType(Integer.valueOf(questionType));
		         String[] questionLabels = request.getParameterValues("questionLabels");
		         if(questionLabels.length>0){
		        	 Set<QuestionBank> banks = new HashSet<QuestionBank>();
		        	  for(String s:questionLabels){
				        	QuestionBank bank = questionBankService.findQuestionBankById(Long.valueOf(s));
				        	banks.add(bank);
				         }
		        	  question.setQuestionBanks(banks);
		         }
		         if(questionType.equals("1")||questionType.equals("2")){
		        	 question.setContent(request.getParameter("content"));
		         }
		         questionService.saveQuestion(question);
	             return SUCCESS;
	    }
	  
	    
	    
	    @RequestMapping(value="/index/initQuestionByBank",method=RequestMethod.GET)
		  @ResponseBody
		  public String initQuestionByBank(HttpServletRequest request , HttpServletResponse response){
			      StringBuffer json = new StringBuffer();
			      json.append("[");
			      Long bankId = Long.valueOf(request.getParameter("bankId"));
			      QuestionBank questionBank = this.questionBankService.findQuestionBankById(bankId);
			      if(questionBank.getQuestions().size()==0){
			    	  return "[]";
			      }
			      for(Question q:questionBank.getQuestions()){
			    	    json.append(q.getQuestionJson());
			    	    json.append(",");
			      }
			      json.deleteCharAt(json.length()-1);
			      json.append( "]" );
			      return json.toString();
		  }
	    
	    
	    
	    
	    @PostMapping(value="/index/alterQuestion")
	    @ResponseBody
	    public String alterQuestion(HttpServletRequest request , HttpServletResponse response) {
	    	 Long id = Long.valueOf(request.getParameter("id"));
    	     Question question = questionService.findQuestionById(id);
    	     question.setTitle(request.getParameter("title"));
    	     question.setAnswer(request.getParameter("answer"));
    	     if(question.getType()==1||question.getType()==2){
    	    	 question.setContent(request.getParameter("content")); 
    	     }
    	     String[] questionLabels = request.getParameterValues("questionLabels");
	         if(questionLabels!=null&&questionLabels.length>0){
	        	 Set<QuestionBank> banks = new HashSet<QuestionBank>();
	        	  for(String s:questionLabels){
			        	QuestionBank bank = questionBankService.findQuestionBankById(Long.valueOf(s));
			        	banks.add(bank);
			         }
	        	  question.setQuestionBanks(banks);
	         }
    	     questionService.alterQuestion(question);
             return SUCCESS;
	    }
    
	  
	  
	  @GetMapping(value="/index/deleteQuestion")
	    @ResponseBody
	    public String deleteQuestion(HttpServletRequest request , HttpServletResponse response) {
	    	     Long id = Long.valueOf(request.getParameter("id"));
	    	     questionService.deleteQuestion(id);
	             return SUCCESS;
	    }
	  
	    
	  @GetMapping("/index/showQuestionInfo")
	    @ResponseBody
	    public String showQuestionInfo(HttpServletRequest request , HttpServletResponse response) {
	    	Long id = Long.valueOf(request.getParameter("id"));
	    	Question question = questionService.findQuestionById(id);
	    	String json = question.getQuestionJson();
	        return json;
	    }
	  
	  @RequestMapping(value="/index/importQuestion.do")
		@ResponseBody
		public String importQuestion(HttpServletRequest request , HttpServletResponse response,@RequestParam(value = "uploadFile", required = false) MultipartFile questionFile) throws Exception {  
		     UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
		     String msg = dealWithTheQuestionFile(questionFile,userInfo);
		     return msg;
	  } 
	  
	  
	  
	  private String dealWithTheQuestionFile(MultipartFile file,UserInfo userInfo) {
			try {
				    HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());	
					HSSFSheet aSheet = workbook.getSheetAt(0);
					String result = "";
					 for (int rowNumOfSheet = 1; rowNumOfSheet <= aSheet
			    		       .getLastRowNum(); rowNumOfSheet++) {
						 if(aSheet.getRow(rowNumOfSheet)!=null&&aSheet.getRow(rowNumOfSheet).getCell(0)!=null){
							 HSSFRow row = aSheet.getRow(rowNumOfSheet);
							 if(row.getCell(0)!=null&&!row.getCell(0).getStringCellValue().equals("")
									 &&row.getCell(1)!=null&&!row.getCell(1).getStringCellValue().equals("")
									 &&row.getCell(2)!=null&&!row.getCell(2).getStringCellValue().equals("")){
								     String idCard = row.getCell(1).getStringCellValue();
								     UserInfo user = this.userService.find("from UserInfo where orgId="+currentUser.getOrgId()+" and  idCard='"+idCard+"'");
								     if(user==null){
								    	 notExist += idCard + ",";
								     }else{
								    	  //保存信息
								    	 AnonyCommentTarget target = new AnonyCommentTarget();
								         target.setAnonyComment(anonyComment);
								         target.setTargetId(user.getId());
								         target.setTargetName(user.getName());
								    	 this.anonyCommentTargetService.save(target);
								     }
							 }else{
								 result += rowNumOfSheet+",";
							 }
						 }
					 }
					 if(result.equals("")&&notExist.equals("")){
				         return JSONSUCCESS;
					 }else{
						 String message = "";
						 if(!result.equals("")){
							   result = result.substring(0, result.length()-1);
							   message+="第"+result+"行有空数据，没有导入;";
						 }
						 if(!notExist.equals("")){
							 notExist = notExist.substring(0, notExist.length()-1);
							 message+="系统不存在身份证号"+notExist+"用户信息";
						 }
						 return "{success:true,msg:'"+message;
					 }
				
			} catch (Exception e) {
				this.anonyCommentService.delete(anonyComment);
				e.printStackTrace();
				return null;
			}
		}

}
