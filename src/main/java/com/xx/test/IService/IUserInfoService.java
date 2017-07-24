package com.xx.test.IService;

import com.xx.test.Model.UserInfo;

public interface IUserInfoService {
  
	   UserInfo findByNameAndPassword(String name,String password);
	   
	   UserInfo add(UserInfo userInfo);

	  /**
	   * 获取用户名和密码错误类型
	   * 1为用户名
	   * 2为密码
	   * 0为正常
	   * @param userInfo
	   * @return
	   */
	  int getErrorLog(UserInfo userInfo);
    
	UserInfo findById(Long userId);
	
}
