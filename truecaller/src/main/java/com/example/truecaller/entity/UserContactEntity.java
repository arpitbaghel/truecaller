package com.example.truecaller.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.truecaller.constant.UserContactEntityConstant;

@Entity
@Table(name = UserContactEntityConstant.TABLE_NAME)
public class UserContactEntity implements Serializable, UserContactEntityConstant {

	private static final long serialVersionUID = -5614430550516799476L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = UserContactEntityConstant.ID)
	private Long id;

	@Column(name = UserContactEntityConstant.USER_ID)
	private Long userId;

	@Column(name = UserContactEntityConstant.CONTACT_USER_ID)
	private Long contactUserId;

	@Column(name = UserContactEntityConstant.CONTACT_NAME)
	private String contactName;

	@Column(name = UserContactEntityConstant.IS_SPAM)
	private Integer isSpam;

	@Column(name = UserContactEntityConstant.IS_CONTACT)
	private Integer isContact;

	@Column(name = UserContactEntityConstant.FLAG)
	private Integer flag = 1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getContactUserId() {
		return contactUserId;
	}

	public void setContactUserId(Long contactUserId) {
		this.contactUserId = contactUserId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Integer getIsSpam() {
		return isSpam;
	}

	public void setIsSpam(Integer isSpam) {
		this.isSpam = isSpam;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getIsContact() {
		return isContact;
	}

	public void setIsContact(Integer isContact) {
		this.isContact = isContact;
	}
}
