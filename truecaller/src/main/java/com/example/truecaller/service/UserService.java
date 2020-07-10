package com.example.truecaller.service;

import java.util.List;

import com.example.truecaller.bean.UserBean;
import com.example.truecaller.entity.UserEntity;

public interface UserService {

	UserEntity registerUser(UserEntity entity);

	UserEntity update(UserEntity entity);

	UserEntity findUserByUsername(String username);

	UserEntity findRegisteredUserByMobile(String mobile);

	UserEntity findUserByMobile(String mobile);

	List<UserEntity> autocompleteByName(String name);

	void markSpam(String mobile);

	List<UserBean> search(String name, String mobile);

	UserBean showUserDetails(String mobile);

}
