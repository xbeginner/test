package com.xx.test.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.test.Dao.OrgDao;
import com.xx.test.Dao.RegisteUserDao;
import com.xx.test.IService.IOrgService;
import com.xx.test.IService.IRegisteUserService;
import com.xx.test.Model.Org;
import com.xx.test.Model.RegisterUser;


@Service
public class RegisteUserService implements IRegisteUserService{
	
	@Autowired
    RegisteUserDao registeUSerDao;
	
	 public void saveRegisteUser(RegisterUser registeUser){
		 registeUSerDao.save(registeUser);
	 }
}
