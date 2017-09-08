package com.xx.test.IService;

import java.util.List;

import com.xx.test.Model.Org;
import com.xx.test.Model.RegisterUser;

public interface IRegisteUserService {
	
  public void saveRegisteUser(RegisterUser registeUser);
  
  public List<RegisterUser> findRegisteUserByOrgId(Long orgId);

}
