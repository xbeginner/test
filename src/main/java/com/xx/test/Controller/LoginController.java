package com.xx.test.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.support.IsNewStrategyFactorySupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xx.test.IService.IUserInfoService;
import com.xx.test.Model.UserInfo;
import com.xx.test.Service.UserInfoService;

import redis.clients.jedis.Jedis;

@RestController
public class LoginController extends BaseController{
 
	  @RequestMapping(value="/checkLogin",method=RequestMethod.POST)
	  @ResponseBody
	  public String login(UserInfo userInfo,Model model){
		  
		     String result="";
		     int log = userInfoService.getErrorLog(userInfo);
		     Jedis jedis = new Jedis("127.0.0.1",6379);
		     
		     if(log!=0){
				     String time = jedis.get(userInfo.getUserName()+"errorTime");
				    if(time==null){
					     jedis.set(userInfo.getUserName()+"errorTime","0");
					     jedis.expire(userInfo.getUserName()+"errorTime", 1800);
				    }else{
					      int t = Integer.valueOf(time);
					      if(t>=2){
					    	    result="tomuch";
					    	    return result;
					      }else{
					    	    t = t+1;
					    	    jedis.set(userInfo.getUserName()+"errorTime",String.valueOf(t));
					      }
				   }
		     }
 
		     switch (log) {
			 case 0:
			    UserInfo user = userInfoService.findByNameAndPassword(userInfo.getUserName(),userInfo.getPassword());
			    result =  String.valueOf(user.getId());
				break;
			case 1:
				result =  "username";
				break;
			case 2:
				result = "password";
				break;
			default:
				break;
			}
		     return result;
	  }
	  
	  
	  @RequestMapping(value="/add",method=RequestMethod.GET)
	  public void add(HttpServletRequest request , HttpServletResponse response){
		     UserInfo userInfo = new UserInfo();
		     userInfo.setUserName("john");
		     userInfo.setPassword("123456");
		     userInfo.setIdcard("123456789987654321");
		     userInfoService.add(userInfo);
	  }
	  
	  
	  
	  @RequestMapping(value="/login",method=RequestMethod.GET)
	  public ModelAndView toLogin(){
		    UserInfo userInfo = new UserInfo();
		    ModelAndView modelAndView = new ModelAndView("login");
		    modelAndView.addObject(userInfo);
		    return modelAndView;
	  }
	  
	  
	  @RequestMapping(value="/openMainPage",method=RequestMethod.POST)
	  public ModelAndView openMainPage(HttpServletRequest request , HttpServletResponse response){
	   	String userId = request.getParameter("userId");
		 UserInfo userInfo = userInfoService.findById(Long.valueOf(userId));
		  ModelAndView modelAndView = new ModelAndView("main");
		  modelAndView.addObject("userInfo",userInfo);
		  return modelAndView;
	  }
	  
	  
	  
	
}
