package com.xx.test.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_question")
public class Question implements Serializable{
     
	/**
	 * 
	 */

	private static final long serialVersionUID =9L;
	
	
	  @Id
	  @GeneratedValue
	private Long id;
	
	//题目
	private String title;
	
	//备选项
	private String content;
	
	/**
	 * 0判断，1单选，2多选，3问答
	 */
	private int type;
	
	private  String  answer;
	
	@ManyToMany(mappedBy="questions")
	private Set<QuestionBank> questionBanks = new HashSet<QuestionBank>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Set<QuestionBank> getQuestionBanks() {
		return questionBanks;
	}

	public void setQuestionBanks(Set<QuestionBank> questionBanks) {
		this.questionBanks = questionBanks;
	}
	
	

}
