package com.xx.test.IService;

import java.util.List;

import com.xx.test.Model.Org;

public interface IOrgService {
	
	Org saveOrg(Org org);
	
	public List<Org> findOrgListByParentId(Long long1);

}
