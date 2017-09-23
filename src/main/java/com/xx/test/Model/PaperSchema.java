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
	  
	  //0初建，1参数，2选题
	  private int step;
	  
	  @OneToMany(mappedBy="paperSchema")
	  private List<UserPaper> userPapers;
	  
	  //0为机考，1为生成试卷考试
	  private int type;
	  
	  //0为随机，1为选择题目
	  private int log;
	  
	  
	  private Long orgId;
	  
	  private Long creatorId;
	  
		//0个人,1企业,2通用
		private int fitUserLog;
		
		//企业类别，0人行机构，1金融机构，2征信机构，3评级机构
		private int fitOrgLog;
		
		private Integer panduanNum;
		
		private Float panduanGrade;
		
		private Integer danxuanNum;
		
		private Float danxuanGrade;
		
		private Integer duoxuanNum;
		
		private Float duoxuanGrade;
		
		private Integer wendaNum;
		
		private Float wendaGrade;
		
		private Float grade;

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
	

	
	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
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

	
	
	public int getFitUserLog() {
		return fitUserLog;
	}

	public void setFitUserLog(int fitUserLog) {
		this.fitUserLog = fitUserLog;
	}

	public int getFitOrgLog() {
		return fitOrgLog;
	}

	public void setFitOrgLog(int fitOrgLog) {
		this.fitOrgLog = fitOrgLog;
	}

	private Map<String,String> getPaperSchemaMap(){
		   String[] types = {"机考","卷考"};
		   String[] fitUserLogs = {"个人","企业","通用"};
		   String[] fitOrgLogs = {"人行机构","金融机构","征信机构","评级机构"};
		   Map<String,String> map = new HashMap<String, String>();
		   map.put("id", String.valueOf(id));
		   map.put("paperName", paperName);
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   map.put("fitUserLog", fitUserLogs[fitUserLog]);
		   map.put("fitOrgLog", fitOrgLogs[fitOrgLog]);
		   map.put("type", types[type]);
		   map.put("typeLog", String.valueOf(type));
		   map.put("doTime", String.valueOf(doTime));
		   map.put("step", String.valueOf(step));
		   map.put("log", String.valueOf(log));
		   return  map;
	}
	
	
	
	public String getPaperSchemaJson(){
		return JsonUtils.getJsonString(getPaperSchemaMap());
	}

	public Integer getPanduanNum() {
		return panduanNum;
	}

	public void setPanduanNum(Integer panduanNum) {
		this.panduanNum = panduanNum;
	}

	public Float getPanduanGrade() {
		return panduanGrade;
	}

	public void setPanduanGrade(Float panduanGrade) {
		this.panduanGrade = panduanGrade;
	}

	public Integer getDanxuanNum() {
		return danxuanNum;
	}

	public void setDanxuanNum(Integer danxuanNum) {
		this.danxuanNum = danxuanNum;
	}

	public Float getDanxuanGrade() {
		return danxuanGrade;
	}

	public void setDanxuanGrade(Float danxuanGrade) {
		this.danxuanGrade = danxuanGrade;
	}

	public Integer getDuoxuanNum() {
		return duoxuanNum;
	}

	public void setDuoxuanNum(Integer duoxuanNum) {
		this.duoxuanNum = duoxuanNum;
	}

	public Float getDuoxuanGrade() {
		return duoxuanGrade;
	}

	public void setDuoxuanGrade(Float duoxuanGrade) {
		this.duoxuanGrade = duoxuanGrade;
	}

	public Integer getWendaNum() {
		return wendaNum;
	}

	public void setWendaNum(Integer wendaNum) {
		this.wendaNum = wendaNum;
	}

	public Float getWendaGrade() {
		return wendaGrade;
	}

	public void setWendaGrade(Float wendaGrade) {
		this.wendaGrade = wendaGrade;
	}

	public Float getGrade() {
		return grade;
	}

	public void setGrade(Float grade) {
		this.grade = grade;
	}
	
	

}
