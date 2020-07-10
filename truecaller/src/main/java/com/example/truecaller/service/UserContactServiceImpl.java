package com.example.truecaller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.truecaller.entity.UserContactEntity;
import com.example.truecaller.repository.UserContactRepository;

@Service
public class UserContactServiceImpl implements UserContactService {

	@Autowired
	private UserContactRepository repository;

	@Override
	public List<UserContactEntity> findAllByContact(Long contactUserID) {
		return repository.findAllByContactUserId(contactUserID);
	}

	@Override
	public UserContactEntity findIfUserIsInContact(Long userId, Long userContactId) {
		return repository.findUserInContact(userId, userContactId);
	}

}
