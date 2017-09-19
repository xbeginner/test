package com.xx.test.IService;

import java.util.List;

import com.xx.test.Model.Menu;
import com.xx.test.Model.Message;
import com.xx.test.Model.Org;
import com.xx.test.Model.Question;
import com.xx.test.Model.QuestionBank;

public interface IQuestionService {
	

	void deleteQuestion(Long id);

	void alterQuestion(Question question);

	Question findQuestionById(Long id);

	void saveQuestion(Question question);
}
