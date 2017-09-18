package com.xx.test.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.xx.test.Model.Menu;
import com.xx.test.Model.Message;
import com.xx.test.Model.Org;
import com.xx.test.Model.QuestionBank;

public interface QuestionBankDao extends CrudRepository<QuestionBank, Long>{
	
	List<QuestionBank> findByOrgId(Long orgId);

//    @Modifying
//    @Transactional  
//    @Query("update Message m set m.name = ?1,m.content=?2 where m.id = ?3")
//	void updateMessage(String name, String content, Long id);
}
