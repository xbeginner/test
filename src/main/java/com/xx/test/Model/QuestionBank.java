package com.xx.test.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 题库
 * @author xikai
 *
 */
@Entity
@Table(name="t_questionBank")
public class QuestionBank implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8L;
	
	
	  @Id
	  @GeneratedValue
	private Long id;
	
	private String  name;
	
	  @ManyToMany(fetch=FetchType.EAGER)
	  @JoinTable(name="t_questionTypes" ,
	          joinColumns = { @JoinColumn(name = "questionBank_id") },
			  inverseJoinColumns = { @JoinColumn(name = "question_Id") })
	private Set<Question> questions = new HashSet<Question>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
	
    
}
