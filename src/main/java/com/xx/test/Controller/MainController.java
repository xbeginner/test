package com.xx.test.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xx.test.Model.UserInfo;

import redis.clients.jedis.Jedis;

@RestController
public class MainController extends BaseController {

	  @RequestMapping(value="/index/initMainMenu",method=RequestMethod.POST)
	  @ResponseBody
	  public String initMainMenu(){
		      StringBuffer json = new StringBuffer();
		      
		      return json.toString();
	  }
	
}
