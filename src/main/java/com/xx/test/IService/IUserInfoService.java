package com.xx.test.IService;

import com.xx.test.Model.UserInfo;

public interface IUserInfoService {
  
	   UserInfo findByNameAndPassword(String name,String password);
	   
	   UserInfo add(UserInfo userInfo);
	
}
