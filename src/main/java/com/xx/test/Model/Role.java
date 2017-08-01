package com.xx.test.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_role")
public class Role {
	
	  @Id
	  @GeneratedValue
	  private int id;
	  
	  private String name;
	  
	  private String info;
	  
	  /**
	   * 管理权限分类，0为普通用户，1为管理员
	   */
	  private int manageLog;
	  
	  @OneToMany(mappedBy="role")
	  private List<UserInfo> userinfoList;
	  
	  @OneToMany(mappedBy="role")
	  private List<Action> actionList;
	  
	  @OneToMany(mappedBy="role",fetch=FetchType.EAGER)
	  private List<Menu> menuList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
 
	public List<UserInfo> getUserinfoList() {
		return userinfoList;
	}

	public void setUserinfoList(List<UserInfo> userinfoList) {
		this.userinfoList = userinfoList;
	}

	public List<Action> getActionList() {
		return actionList;
	}

	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public int getManageLog() {
		return manageLog;
	}

	public void setManageLog(int manageLog) {
		this.manageLog = manageLog;
	}

    

	  

}
