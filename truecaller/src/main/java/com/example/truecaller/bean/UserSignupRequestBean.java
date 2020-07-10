package com.example.truecaller.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.example.truecaller.constant.UserEntityConstant;

public class UserSignupRequestBean implements Serializable, UserEntityConstant {

	private static final long serialVersionUID = 4887980763763114169L;

	@Null
	private Long id;

	@NotNull
	private String username;

	@NotNull
	private String password;

	@NotNull
	private String name;

	@NotNull
	@Size(min = 10, max = 10)
	private String mobile;

	private String email;

	private Integer isRegistered = RegisteredUser.REGISTERED_USER;

	private Long spams = SpamedUser.NOT_SPAMED_USER;

	private Integer flag = 1;

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

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
