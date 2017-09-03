package com.xx.test.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.test.Dao.OrgDao;
import com.xx.test.IService.IOrgService;
import com.xx.test.Model.Org;


@Service
public class OrgService implements IOrgService{
	
	@Autowired
    OrgDao orgDao;
	
	public Org saveOrg(Org org){
		return orgDao.save(org);
	}
	
	public List<Org> findOrgListByParentId(int paentId){
		 
	}

}
