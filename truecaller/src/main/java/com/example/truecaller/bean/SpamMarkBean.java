package com.example.truecaller.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class SpamMarkBean implements Serializable {

	private static final long serialVersionUID = -3034996001883322886L;

	@NotNull
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
