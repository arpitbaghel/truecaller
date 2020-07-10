package com.example.truecaller.service;

import java.util.List;

import com.example.truecaller.entity.UserContactEntity;

public interface UserContactService {

	List<UserContactEntity> findAllByContact(Long contactUserID);

	UserContactEntity findIfUserIsInContact(Long userId, Long userContactId);
}
