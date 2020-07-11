package com.example.truecaller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.example.truecaller.bean.UserSignupRequestBean;
import com.example.truecaller.service.UserService;

@Component
public class UserValidator {

	@Autowired
	private UserService userService;

	public void validateOnSave(UserSignupRequestBean bean, Errors errors) {
		if (!errors.hasErrors()) {
			if (bean.getUsername() == null || bean.getPassword() == null || bean.getMobile() == null || bean.getName() == null) {
				errors.reject("Mandatory Fields Missing");
			}
			if (userService.findUserByUsername(bean.getUsername()) != null) {
				errors.reject("Username Already Taken");
			}
			if (userService.findRegisteredUserByMobile(bean.getMobile()) != null) {
				errors.reject("Mobile No Already Registered");
			}
		}
	}

	public void validateOnSpamMark(String mobile, Errors errors) {
		if (!errors.hasErrors()) {
			if (mobile.length() != 10) {
				errors.reject("Invalid Mobile No");
			}
		}
	}
}
