package com.example.truecaller.bean;

import java.io.Serializable;

public class UserSignupResponseBean implements Serializable {

	private static final long serialVersionUID = -7404835925979047375L;

	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
