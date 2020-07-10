package com.example.truecaller.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.truecaller.constant.UserEntityConstant;

@Entity
@Table(name = UserEntityConstant.TABLE_NAME)
public class UserEntity implements Serializable, UserEntityConstant {

	private static final long serialVersionUID = -4006967794149979643L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = UserEntityConstant.ID)
	private Long id;

	@Column(name = UserEntityConstant.USERNAME)
	private String username;

	@Column(name = UserEntityConstant.PASSWORD)
	private String password;

	@Column(name = UserEntityConstant.NAME)
	private String name;

	@Column(name = UserEntityConstant.MOBILE)
	private String mobile;

	@Column(name = UserEntityConstant.EMAIL)
	private String email;

	@Column(name = UserEntityConstant.IS_REGISTERED)
	private Integer isRegistered;

	@Column(name = UserEntityConstant.SPAMS)
	private Long spams;

	@Column(name = UserEntityConstant.FLAG)
	private Integer flag;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
