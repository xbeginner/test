package com.xx.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.test.Dao.OrgDao;
import com.xx.test.IService.IOrgService;


@Service
public class OrgService implements IOrgService{
	
	@Autowired
    OrgDao orgDao;
	

}
