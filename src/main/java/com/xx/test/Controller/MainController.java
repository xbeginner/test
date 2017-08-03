package com.xx.test.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xx.test.Model.Menu;
import com.xx.test.Model.Role;
import com.xx.test.Model.UserInfo;
import com.xx.test.Utils.JsonUtils;

import redis.clients.jedis.Jedis;

@RestController
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
		      System.out.println(json.toString());
		      return json.toString();
	  }
	
	  
	  
	  @RequestMapping(value="/index/initExamInfo",method=RequestMethod.GET)
	  @ResponseBody
	  public String initExamInfo(HttpServletRequest request , HttpServletResponse response){
              System.out.println(request.getParameter("page"));
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
	  public ModelAndView openMainPage(HttpServletRequest request , HttpServletResponse response){
		  UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
		  ModelAndView modelAndView = new ModelAndView("manageUserInfo");
		  modelAndView.addObject("userInfo",userInfo);
		  return modelAndView;
	  }
}
