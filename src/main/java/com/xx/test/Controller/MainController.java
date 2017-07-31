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
			    	    	json.append(JsonUtils.getJsonString(map));
			    	    	json.append(",");
			    	    }
		    	    }
		    	    json.deleteCharAt(json.length()-1);
		      }
		      json.append( "]" );
		      return json.toString();
	  }
	
}
