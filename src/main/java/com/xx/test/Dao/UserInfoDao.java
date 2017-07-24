package com.xx.test.Dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xx.test.Model.UserInfo;

@Repository
public interface UserInfoDao extends PagingAndSortingRepository<UserInfo, Long>{

	UserInfo findByUserNameAndPassword(String name, String password);

	UserInfo findByUserName(String userName);

	UserInfo findById(Long userId);
 

}
