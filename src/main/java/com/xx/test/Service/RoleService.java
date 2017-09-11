package com.xx.test.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.test.Dao.OrgDao;
import com.xx.test.Dao.RoleDao;
import com.xx.test.IService.IOrgService;
import com.xx.test.IService.IRoleService;
import com.xx.test.Model.Org;
import com.xx.test.Model.Role;


@Service
public class RoleService implements IRoleService{
	
	@Autowired
    RoleDao roleDao;

	@Override
	public List<Role> findAllRole() {
		return (List<Role>) roleDao.findAll();
	}
	
	 
}
