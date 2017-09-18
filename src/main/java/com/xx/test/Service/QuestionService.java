package com.xx.test.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.test.Dao.MenuDao;
import com.xx.test.Dao.MessageDao;
import com.xx.test.Dao.OrgDao;
import com.xx.test.Dao.QuestionBankDao;
import com.xx.test.Dao.QuestionDao;
import com.xx.test.IService.IMenuService;
import com.xx.test.IService.IMessageService;
import com.xx.test.IService.IOrgService;
import com.xx.test.IService.IQuestionBankService;
import com.xx.test.IService.IQuestionService;
import com.xx.test.Model.Menu;
import com.xx.test.Model.Message;
import com.xx.test.Model.Org;
import com.xx.test.Model.Question;
import com.xx.test.Model.QuestionBank;


@Service
public class QuestionService implements IQuestionService{
	
	@Autowired
    QuestionDao questionDao;

	@Override
	public List<Question> findQuestionByOrg(Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteQuestion(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterQuestion(Question question) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public QuestionBank findQuestionById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveQuestion(Question question) {
		// TODO Auto-generated method stub
		
	}

     

	 
	 
	
	 
}
