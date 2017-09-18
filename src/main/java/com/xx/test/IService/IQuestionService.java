package com.xx.test.IService;

import java.util.List;

import com.xx.test.Model.Menu;
import com.xx.test.Model.Message;
import com.xx.test.Model.Org;
import com.xx.test.Model.Question;
import com.xx.test.Model.QuestionBank;

public interface IQuestionService {
	
	List<Question> findQuestionByOrg(Long orgId);

	void deleteQuestion(Long id);

	void alterQuestion(Question question);

	QuestionBank findQuestionById(Long id);

	void saveQuestion(Question question);
}
