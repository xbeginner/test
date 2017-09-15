package com.xx.test.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.test.Dao.MenuDao;
import com.xx.test.Dao.MessageDao;
import com.xx.test.Dao.OrgDao;
import com.xx.test.IService.IMenuService;
import com.xx.test.IService.IMessageService;
import com.xx.test.IService.IOrgService;
import com.xx.test.Model.Menu;
import com.xx.test.Model.Message;
import com.xx.test.Model.Org;


@Service
public class MessageService implements IMessageService{
	
	@Autowired
    MessageDao messageDao;

	@Override
	public List<Message> findAllMessagesByOrg(Long orgId) {
		// TODO Auto-generated method stub
		return messageDao.findByOrgId(orgId);
	}

	 
	
	 
}
