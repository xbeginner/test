package com.xx.test.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
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
import com.xx.test.Model.Org;
import com.xx.test.Model.PaperSchema;
import com.xx.test.Model.Question;
import com.xx.test.Model.QuestionBank;
import com.xx.test.Model.UserInfo;
import com.xx.test.Model.UserPaper;

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
		         String fitUserLog = request.getParameter("fitUserLog");
		         question.setFitUserLog(Integer.valueOf(fitUserLog));
		         String fitOrgLog = request.getParameter("fitOrgLog");
		         question.setFitOrgLog(Integer.valueOf(fitOrgLog));
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
	  
	    @RequestMapping(value="/index/importQuestion")
		@ResponseBody
		public String importQuestion(HttpServletRequest request , HttpServletResponse response,@RequestParam(value = "uploadFile", required = false) MultipartFile questionFile) throws Exception {  
		     UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
		     String msg = dealWithTheQuestionFile(questionFile,userInfo);
		     return msg;
	  } 
	  
	  
	  
	  private String dealWithTheQuestionFile(MultipartFile file,UserInfo userInfo) {
			try {
				    Map<String,Integer> maps = new HashMap<String,Integer>(){{
				    	     put("判断题", 0);
				    	     put("单选题", 1);
				    	     put("多选题", 2);
				    	     put("问答题", 3);
				    	};
				    };
				    Map<String,Integer> fitUserMaps = new HashMap<String,Integer>(){{
			    	     put("个人", 0);
			    	     put("企业", 1);
			    	     put("通用", 2);
			    	};
			    };
			    Map<String,Integer> fitOrgMaps = new HashMap<String,Integer>(){{
		    	     put("人行机构", 0);
		    	     put("金融机构", 1);
		    	     put("征信机构", 2);
		    	     put("评级机构", 3);
		    	};
		    };
				    HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());	
					HSSFSheet aSheet = workbook.getSheetAt(0);
					String result = "";
					 for (int rowNumOfSheet = 1; rowNumOfSheet <= aSheet
			    		       .getLastRowNum(); rowNumOfSheet++) {
						 if(aSheet.getRow(rowNumOfSheet)!=null&&aSheet.getRow(rowNumOfSheet).getCell(0)!=null){
							 HSSFRow row = aSheet.getRow(rowNumOfSheet);
							 if(row.getCell(0)!=null&&!row.getCell(0).getStringCellValue().equals("") ){
								     String title = row.getCell(0).getStringCellValue().trim();
								     String type= row.getCell(3).getStringCellValue().trim();
								   
								     String answer = row.getCell(2).getStringCellValue().trim();
								     String lableStr = row.getCell(4).getStringCellValue().trim();
								     String fitUserLog= row.getCell(6).getStringCellValue().trim();
								     String fitOrgLog= row.getCell(5).getStringCellValue().trim();
								     Question question = new Question();
								     question.setAnswer(answer);
								     question.setTitle(title);
								     question.setType(maps.get(type));
								     question.setFitOrgLog(fitOrgMaps.get(fitOrgLog));
								     question.setFitUserLog(fitUserMaps.get(fitUserLog));
								     if(type.equals("单选题")||type.equals("多选题")){
								    	  String content = row.getCell(1).getStringCellValue().trim();
								    	    question.setContent(content);
								     }
								     Set<QuestionBank> banks = new HashSet<QuestionBank>();
								     if(lableStr.contains("#")){
								    	  String[] labels = lableStr.split("#");
								    	  for(String s:labels){
								    		    QuestionBank bank = questionBankService.findQuestionBankByName(s);
								    		    if(bank!=null&&bank.getOrgId()==userInfo.getOrg().getId()){
								    		    	  banks.add(bank);
								    		    }
								    	  }
								     }else{
								    	    QuestionBank bank = questionBankService.findQuestionBankByName(lableStr);
								    	    if(bank!=null&&bank.getOrgId()==userInfo.getOrg().getId()){
							    		    	  banks.add(bank);
							    		    }
								     }
								     question.setQuestionBanks(banks);
								     questionService.saveQuestion(question);
							 }
						 }
					 }
				 return "操作成功";
			} catch (Exception e) {
				
				e.printStackTrace();
				return "操作失败，请重新检查题库模板";
			}
		}
	  
	  
	  
	  @RequestMapping(value="/index/managePaper",method=RequestMethod.GET)
	  public ModelAndView managePaper(HttpServletRequest request , HttpServletResponse response){
		  UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
		  ModelAndView modelAndView = new ModelAndView("managePaperSchema");
		  modelAndView.addObject("userInfo",userInfo);
		  return modelAndView;
	  }

	  
	  
	  @RequestMapping(value="/index/initPaperSchema",method=RequestMethod.GET)
	  @ResponseBody
	  public String initPaperSchema(HttpServletRequest request , HttpServletResponse response){
		      StringBuffer json = new StringBuffer();
		      json.append("[");
		      UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
		      Long orgId = userInfo.getOrg().getId();
		      List<PaperSchema> paperSchemas = this.paperSchemaService.findPaperSchemaByOrg(orgId);
		      if(paperSchemas.size()==0){
		    	  return "[]";
		      }
		      for(PaperSchema p:paperSchemas){
		    	    json.append(p.getPaperSchemaJson());
		    	    json.append(",");
		      }
		      json.deleteCharAt(json.length()-1);
		      json.append( "]" );
		      return json.toString();
	  }
	  
	  
	   @PostMapping(value="/index/addPaperSchema")
	    @ResponseBody
	    public String addPaperSchema(HttpServletRequest request , HttpServletResponse response) {
	    	     PaperSchema paperSchema = new PaperSchema();
	    	     UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
	    	     paperSchema.setCreatorId(userInfo.getId());
	    	     paperSchema.setDoTime(Integer.valueOf(request.getParameter("doTime")));
	    	     paperSchema.setLog(Integer.valueOf(request.getParameter("log")));
	    	     paperSchema.setOrgId(userInfo.getOrg().getId());
	    	     paperSchema.setPaperName(request.getParameter("paperName"));
	    	     paperSchema.setType(Integer.valueOf(request.getParameter("type")));
	    	     paperSchema.setFitOrgLog(Integer.valueOf(request.getParameter("fitOrgLog")));
	    	     paperSchema.setFitUserLog(Integer.valueOf(request.getParameter("fitUserLog")));
	    	     paperSchema.setStep(0);
		         paperSchemaService.savePaperSchema(paperSchema);
	             return SUCCESS;
	    }
	   
	   
	   
	   
	      @RequestMapping(value="/index/getOrgUsers",method=RequestMethod.GET)
		  @ResponseBody
		  public String getOrgUsers(HttpServletRequest request , HttpServletResponse response){
			      StringBuffer json = new StringBuffer();
			      json.append("[");
			      UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
			      List<Org> orgList = orgService.findOrgListByParentId(userInfo.getOrg().getId());
			      if(orgList.size()>0){
//			    	  json.append("\"orgs\":[" );
			    	   for(Org o:orgList){
			    		       json.append(o.getOrgJson());
			    			   json.deleteCharAt(json.length()-1);
					    	   json.append(",\"users\":[");
					    	   if(o.getUserInfoList().size()>0){
					    		      for(UserInfo u:o.getUserInfoList()){
					    		    	  json.append(u.getUserJson());
					    		    	  json.append(",");
					    		      }
					    	   }
					    	   json.deleteCharAt(json.length()-1);
					    	   json.append("]}");
			    	   }
			    	  
			      }
			           json.append("]");
//			      json.append("}");
			      return json.toString();
		  }
	      
	      
	      
	      
	        @PostMapping(value="/index/setPaperQuestionInfo")
		    @ResponseBody
		    public String setPaperQuestionInfo(HttpServletRequest request , HttpServletResponse response) {
			         Long paperId = Long.valueOf(request.getParameter("paperId"));
			         PaperSchema paperSchema = paperSchemaService.findPaperSchemaById(paperId);
	        	     String questionPanduanIds = "";
	        	     String questionDanxuanIds = "";
	        	     String questionDuoxuanIds = "";
	        	     String questionWendaIds = "";
	        	     String[] bankIds = request.getParameterValues("questionLabels");
			         String panduanNum = request.getParameter("panduanNum");
	        	     paperSchema.setPanduanNum(Integer.valueOf(panduanNum));
			         String panduanGrade = request.getParameter("panduanGrade");
			         paperSchema.setPanduanGrade(Float.valueOf(panduanGrade));
			         String danxuanNum = request.getParameter("danxuanNum");
			         paperSchema.setDanxuanNum(Integer.valueOf(danxuanNum));
			         String danxuanGrade = request.getParameter("danxuanGrade");
			         paperSchema.setDanxuanGrade(Float.valueOf(danxuanGrade));
			         String duoxuanNum = request.getParameter("duoxuanNum");
			         paperSchema.setDuoxuanNum(Integer.valueOf(duoxuanNum));
			         String duoxuanGrade = request.getParameter("duoxuanGrade");
			         paperSchema.setDuoxuanGrade(Float.valueOf(duoxuanGrade));
			         if(paperSchema.getType()==1){
				         String wendaNum = request.getParameter("wendaNum");
				         paperSchema.setWendaNum(Integer.valueOf(wendaNum));
				         String wendaGrade = request.getParameter("wendaGrade");
				         paperSchema.setWendaGrade(Float.valueOf(wendaGrade));
			         }
			         if(paperSchema.getLog()==0){
			        	 	questionPanduanIds = getRandomQuestionsByLabel(bankIds,Integer.valueOf(panduanNum),paperSchema.getFitOrgLog(),paperSchema.getFitUserLog(),0);
			        		questionDanxuanIds = getRandomQuestionsByLabel(bankIds,Integer.valueOf(danxuanNum),paperSchema.getFitOrgLog(),paperSchema.getFitUserLog(),1);
			        		questionDuoxuanIds = getRandomQuestionsByLabel(bankIds,Integer.valueOf(duoxuanNum),paperSchema.getFitOrgLog(),paperSchema.getFitUserLog(),2);
			         }
			         paperSchema.setStep(1);
			         String userIds = request.getParameter("chooseUserIds");
			         if(!userIds.equals("")&&userIds!=null){
			             String[] ids = userIds.split(",");
				         List<UserPaper>  userPaperList = new ArrayList<UserPaper>();
				         for(String userId:ids){
				        	  UserPaper userPaper = new UserPaper();
				        	  userPaper.setDoLog(0);
				        	  userPaper.setPaperId(paperId);
				        	  userPaper.setPaperSchema(paperSchema);
				        	  userPaper.setUserId(Long.valueOf(userId));
				        	  userPaperList.add(userPaper);
				         }
			         }
			  
			         
			        
			        
		             return SUCCESS;
		    }

            /**
             * 
             * @param bankIds
             * @param fitOrgLog
             * @param fitUserLog
             * @param type  0判断，1单选，2多选，3问答
             *   * @return
             */
			private String getRandomQuestionsByLabel(String[] bankIds,int questionNum, int fitOrgLog, int fitUserLog, int type) {
				return null;
			}
}
