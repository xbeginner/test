package com.xx.test.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_menu")
public class Menu {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private String info;
	
	private String url;
	
	@ManyToOne
	private Role role;
	
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY ,optional = true)
    @JoinColumn(name = "parentMenu")     
    private Menu parentMenu;
	
	@OneToMany(
			   cascade=CascadeType.ALL,
			   fetch=FetchType.EAGER,
			   mappedBy="parentMenu"
	)
	private List<Menu> childMenuList = new ArrayList<Menu>();	

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	
	

	public List<Menu> getChildMenuList() {
		return childMenuList;
	}

	public void setChildMenuList(List<Menu> childMenuList) {
		this.childMenuList = childMenuList;
	}

	public Map<String,String> getMap(){
		   Map<String,String> map = new HashMap<String, String>();
		   
		   map.put("id", String.valueOf(id));
		   map.put("name", name);
		   map.put("info", info);
		   map.put("url", url==null?"#":"/"+url);
		   
		   
		   return  map;
	}
	
}
