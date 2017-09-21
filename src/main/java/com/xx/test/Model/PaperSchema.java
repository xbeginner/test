package com.xx.test.Model;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xx.test.Utils.JsonUtils;

@Entity
@Table(name="t_paperSchema")
public class PaperSchema implements Serializable{
	
	private static final long serialVersionUID =11L;
	
	  @Id
	  @GeneratedValue
	  private Long id;
	  
	  private String  paperName;
	  
	  //开始时间
	  private Date startTime;
	  
	  //结束时间
	  private Date endTime;
	  
	  //考试时长
	  private Integer doTime;
	  
	  @OneToMany(mappedBy="paperSchema")
	  private List<UserPaper> userPapers;
	  
	  //0为机考，1为生成试卷考试
	  private int type;
	  
	  //0为随机，1为选择题目
	  private int log;
	  
	  private Long orgId;
	  
	  private Long creatorId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getDoTime() {
		return doTime;
	}

	public void setDoTime(Integer doTime) {
		this.doTime = doTime;
	}

	public List<UserPaper> getUserPapers() {
		return userPapers;
	}

	public void setUserPapers(List<UserPaper> userPapers) {
		this.userPapers = userPapers;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	

	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	  
	public int getLog() {
		return log;
	}

	public void setLog(int log) {
		this.log = log;
	}

	private Map<String,String> getPaperSchemaMap(){
		   String[] types = {"机考","卷考"};
		   Map<String,String> map = new HashMap<String, String>();
		   map.put("id", String.valueOf(id));
		   map.put("paperName", paperName);
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   map.put("startTime", format.format(startTime));
		   map.put("endTime", format.format(endTime));
		   map.put("type", types[type]);
		   map.put("doTime", String.valueOf(doTime));
		   map.put("log", String.valueOf(log));
		   return  map;
	}
	
	
	
	public String getPaperSchemaJson(){
		return JsonUtils.getJsonString(getPaperSchemaMap());
	}

}
