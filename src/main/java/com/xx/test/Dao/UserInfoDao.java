package com.xx.test.Dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xx.test.Model.Org;
import com.xx.test.Model.UserInfo;

@Repository
public interface UserInfoDao extends PagingAndSortingRepository<UserInfo, Long>{

	UserInfo findByUserNameAndPassword(String name, String password);

	UserInfo findByUserName(String userName);

	UserInfo findById(Long userId);
 
	@Transactional
	@Modifying 
	@Query("update UserInfo u set u.org = ?1 where u.id = ?2") 
	public int update(Org org, Long userId);
}
