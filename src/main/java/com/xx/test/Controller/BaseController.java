package com.xx.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.xx.test.IService.IOrgService;
import com.xx.test.IService.IUserInfoService;

public abstract class BaseController {
	
	  @Autowired
	  protected IUserInfoService userInfoService;
	  
	  @Autowired
	  protected IOrgService orgService;

}
