package com.xx.test.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xx.test.IService.IUserInfoService;
import com.xx.test.Model.UserInfo;
import com.xx.test.Service.UserInfoService;

@RestController
public class LoginController extends BaseController{
 
	  @RequestMapping(value="/checkLogin",method=RequestMethod.GET)
	  public void login(HttpServletRequest request , HttpServletResponse response){
		     UserInfo userInfo = userInfoService.findByNameAndPassword("john", "123456");
		     System.out.println(userInfo.getIdcard());
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
		    ModelAndView modelAndView = new ModelAndView("login");
		    return modelAndView;
	  }
	  
	  
	  
	
}
