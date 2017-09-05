package com.xx.test.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xx.test.Form.OrgAddForm;
import com.xx.test.Form.PersonForm;
import com.xx.test.Model.Menu;
import com.xx.test.Model.Org;
import com.xx.test.Model.Role;
import com.xx.test.Model.UserInfo;
import com.xx.test.Utils.JsonUtils;

import redis.clients.jedis.Jedis;

@Controller
public class MainController extends BaseController {

	  @RequestMapping(value="/index/initMainMenu",method=RequestMethod.GET)
	  @ResponseBody
	  public String initMainMenu(HttpServletRequest request , HttpServletResponse response){
  
		      StringBuffer json = new StringBuffer();
		      json.append("[");
		      UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
		      Role role = userInfo.getRole();
		      if(role!=null){
		    	    List<Menu> menuList = role.getMenuList();
		    	    if(menuList.size()>0){
			    	    Map<String,String> map = new HashMap<String,String>();
			    	    for(Menu m:menuList){
			    	    	map  =  m.getMap();
			    	    	json.append("{");
			    	    	json.append(JsonUtils.getAddableJsonString(map));
			    	    	json.append("},");
			    	    }
		    	    }
		    	    json.deleteCharAt(json.length()-1);
		      }
		      json.append( "]" );
		 
		      return json.toString();
	  }
	
	  
	  
	  @RequestMapping(value="/index/initExamInfo",method=RequestMethod.GET)
	  @ResponseBody
	  public String initExamInfo(HttpServletRequest request , HttpServletResponse response){
  
		       String json = "{\"info\":[{\"name\":\"测试1\",\"time\":\"2017-09-08\"},{\"name\":\"测试2\",\"time\":\"2017-09-09\"}],\"page\":1}";
		      return json;
	  }
	
	  
	  
	  @RequestMapping(value="/index/initMessageInfo",method=RequestMethod.GET)
	  @ResponseBody
	  public String initMessageInfo(HttpServletRequest request , HttpServletResponse response){
		      String json = "[{\"name\":\"测试1\",\"time\":\"2017-09-08\"},{\"name\":\"测试2\",\"time\":\"2017-09-09\"}]";
		      return json;
	  }
	  
	  
	  @RequestMapping(value="/index/manageUserInfo",method=RequestMethod.GET)
	  public ModelAndView manageUserInfo(HttpServletRequest request , HttpServletResponse response){
		  UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
		  ModelAndView modelAndView = new ModelAndView("manageUserInfo");
		  modelAndView.addObject("userInfo",userInfo);
		  return modelAndView;
	  }
	  
	  
	  @RequestMapping(value="/index/manageOrg",method=RequestMethod.GET)
	  public ModelAndView manageOrg(HttpServletRequest request , HttpServletResponse response){
		  UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
		  ModelAndView modelAndView = new ModelAndView("manageOrg");
		  modelAndView.addObject("userInfo",userInfo);
		  modelAndView.addObject("org",userInfo.getOrg());
		  return modelAndView;
	  }
	  
	    @GetMapping("/manage/toAddOrg")
	    public String toAddOrg(OrgAddForm orgAddForm) {
	        return "addOrg";
	    }
	  
	  
		 @PostMapping("/manage/addOrg")
	    public String addOrg(@Valid OrgAddForm orgAddForm, BindingResult bindingResult,HttpServletRequest request) {
		 
	        if (bindingResult.hasErrors()) {
	               return "addOrg";
	        }
	        Org org = new Org();
	         org.setAddress(orgAddForm.getAddress());
	         org.setMaster(orgAddForm.getMaster());
	         org.setMasterTel(orgAddForm.getMasterTel());
	         org.setOrgName(orgAddForm.getOrgName());
	         org.setTel(orgAddForm.getTel());
	        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
	        if(userInfo.getOrg()==null){
	        	 userInfo.setOrg(org);
	 	         this.userInfoService.alterUserInfoOrg(org, userInfo.getId());
	        }else{
	        	 org.setParentOrgId(userInfo.getOrg().getId());
	        }
	         this.orgService.saveOrg(org);
 
	         return "redirect:/success";
	    }
		 
		 
		 
		    @GetMapping("/index/initOrgList")
		    @ResponseBody
		    public String initOrgList(HttpServletRequest request , HttpServletResponse response) {
		    	String json = "[";
		    	UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
		    	int parentId = Integer.valueOf(request.getParameter("parentId"));
		    	if(parentId==0){
		    		  json += userInfo.getOrg().getOrgJson();
		    		  List<Org> orgList = this.orgService.findOrgListByParentId(userInfo.getOrg().getId());
                      if(!orgList.isEmpty()){
                    	  json = json.replace("}",",\"childCount\":\""+orgList.size()+"\"}");
                      }else{
                    	  json = json.replace("}",",\"childCount\":\"0\"}");
                      }
		    	}else{
		    		
		    	}
		    	json += "]";
		    	 
		        return json;
		    }
		    
		    
		    @GetMapping("/index/showOwnOrgList")
		    @ResponseBody
		    public String showOwnOrgList(HttpServletRequest request , HttpServletResponse response) {
		    	String json = "[";
		    	Long parentId = Long.valueOf(request.getParameter("parentId"));
		    	List<Org> orgList = this.orgService.findOrgListByParentId(parentId);
		    	for(Org org:orgList){
		    		 json += org.getOrgJson();
		    		 json += ",";
		    	}
                json = json.substring(0, json.length()-1);	    	
		    	json += "]";
		        return json;
		    }
		    
		    
		    @PostMapping(value="/index/addOwnOrg")
		    @ResponseBody
		    public String addOwnOrg(HttpServletRequest request , HttpServletResponse response) {
		    	     UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
			         Org org = new Org();
			         org.setAddress(request.getParameter("address"));
			         org.setMaster(request.getParameter("master"));
			         org.setMasterTel(request.getParameter("tel"));
			         org.setOrgName(request.getParameter("orgName"));
			         org.setTel(request.getParameter("tel"));
			         org.setParentOrgId(userInfo.getOrg().getId());
			         this.orgService.saveOrg(org);
		             return SUCCESS;
		    }
		    
		    
		    @PostMapping(value="/index/alterOrg")
		    @ResponseBody
		    public String alterOrg(HttpServletRequest request , HttpServletResponse response) {
		    	     Long id = Long.valueOf(request.getParameter("orgId"));
			         Org org = orgService.findOrgById(id);
			         org.setAddress(request.getParameter("address"));
			         org.setMaster(request.getParameter("master"));
			         org.setMasterTel(request.getParameter("tel"));
			         org.setOrgName(request.getParameter("orgName"));
			         org.setTel(request.getParameter("tel"));
			         this.orgService.alterOrg(org);
		             return SUCCESS;
		    }
		 
		    @GetMapping("/index/showOrgInfo")
		    @ResponseBody
		    public String showOrgInfo(HttpServletRequest request , HttpServletResponse response) {
		    	Long orgId = Long.valueOf(request.getParameter("orgId"));
		    	Org org = orgService.findOrgById(orgId);
		    	String json = org.getOrgJson();
		    	System.out.println(json);
		        return json;
		    }
		    
}
