package com.xx.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.test.Dao.UserInfoDao;
import com.xx.test.IService.IUserInfoService;
import com.xx.test.Model.UserInfo;

@Service
public class UserInfoService implements IUserInfoService{
	
	@Autowired
	UserInfoDao userInfoDao;

	@Override
	public UserInfo findByNameAndPassword(String name, String password) {
		return userInfoDao.findByUserNameAndPassword(name,password);
	}

	@Override
	public UserInfo add(UserInfo userInfo) {
		return userInfoDao.save(userInfo);
	}
	

}
