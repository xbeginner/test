package com.xx.test.Dao;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.xx.test.Model.Menu;
import com.xx.test.Model.Message;
import com.xx.test.Model.Org;
import com.xx.test.Model.Question;
import com.xx.test.Model.QuestionBank;

public interface QuestionDao extends CrudRepository<Question, Long>{
	

    @Modifying
    @Transactional  
    @Query("update Question q set q.title = ?1,q.content=?2,q.answer=?3,q.questionBanks= ?4 where q.id = ?5")
	void updateQuestion(String title, String content, String answer, Set<QuestionBank> banks,Long id);

    @Modifying
    @Transactional  
    @Query("update Question q set q.questionBanks=null where q.id = ?1")
	void updateQuestionLabels(Long id);
}
