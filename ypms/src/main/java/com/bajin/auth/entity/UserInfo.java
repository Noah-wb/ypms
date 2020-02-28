package com.bajin.auth.entity;


/**
 * @author bajin
 * @date 2018/9/30
 */

public class UserInfo {
    private Long id;
    private String name;
    
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
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + "]";
	}
	public UserInfo(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    
}
