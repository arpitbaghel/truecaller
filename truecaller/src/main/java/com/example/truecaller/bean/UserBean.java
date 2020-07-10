package com.example.truecaller.bean;

import java.io.Serializable;

public class UserBean implements Serializable {

	private static final long serialVersionUID = -6233345533987789918L;

	private Long id;

	private String name;

	private String mobile;

	private String email;

	private Integer isRegistered;

	private Long spams;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIsRegistered() {
		return isRegistered;
	}

	public void setIsRegistered(Integer isRegistered) {
		this.isRegistered = isRegistered;
	}

	public Long getSpams() {
		return spams;
	}

	public void setSpams(Long spams) {
		this.spams = spams;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
