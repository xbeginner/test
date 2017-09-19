package com.xx.test.Controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xx.test.Model.Message;
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
	  
	  
	  
	  

}
