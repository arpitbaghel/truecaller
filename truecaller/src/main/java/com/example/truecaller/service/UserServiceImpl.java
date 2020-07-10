package com.example.truecaller.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.truecaller.bean.UserBean;
import com.example.truecaller.constant.UserContactEntityConstant;
import com.example.truecaller.constant.UserEntityConstant;
import com.example.truecaller.entity.UserContactEntity;
import com.example.truecaller.entity.UserEntity;
import com.example.truecaller.repository.UserContactRepository;
import com.example.truecaller.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserContactService userContactService;

	@Autowired
	private UserContactRepository contactRepository;

	@Override
	public UserEntity registerUser(UserEntity entity) {
		return repository.save(entity);
	}

	@Override
	public UserEntity update(UserEntity entity) {
		return repository.save(entity);
	}

	@Override
	public UserEntity findUserByUsername(String username) {
		return repository.findRegisteredUserByUsername(username);
	}

	@Override
	public UserEntity findRegisteredUserByMobile(String mobile) {
		return repository.findRegisteredUserByMobileNumber(mobile);
	}

	@Override
	public UserEntity findUserByMobile(String mobile) {
		return repository.findUserByMobileNumber(mobile);
	}

	@Override
	public List<UserBean> search(String name, String mobile) {
		List<UserBean> userBean = new ArrayList<>();
		if (name != null) {
			List<UserEntity> userNames = userService.autocompleteByName(name);
			convertUserEntityToBeanList(userBean, userNames);
		} else if (mobile != null) {
			UserEntity user = repository.findUserByMobileNumber(mobile);
			if (user != null) {
				if (user.getIsRegistered().equals(1)) {
					UserBean bean = new UserBean();
					BeanUtils.copyProperties(user, bean);
					userBean.add(bean);
				} else {
					List<UserContactEntity> userContacts = userContactService.findAllByContact(user.getId());
					saveUserContactNameToUserBean(userBean, userContacts, mobile);
				}
			}
		}
		return userBean;
	}

	@Override
	public List<UserEntity> autocompleteByName(String name) {
		try {
			name = URLDecoder.decode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		final List<UserEntity> exactNameList = repository.searchExactNames(name.concat("%"));
		final List<UserEntity> matchNameList = repository.searchMatchedNames(name.concat("%"), "%".concat(name).concat("%"));
		exactNameList.addAll(matchNameList);
		return exactNameList;
	}

	@Override
	public void markSpam(String mobile) {
		UserEntity userEntity = repository.findUserByMobileNumber(mobile);
		UserEntity loggedInUser = getAuthUserDetails();
		UserContactEntity contact = new UserContactEntity();
		if (userEntity != null) {
			Long spams = userEntity.getSpams();
			spams++;
			userEntity.setSpams(spams);
			repository.save(userEntity);
			contact.setContactUserId(userEntity.getId());
			contact.setContactName(userEntity.getName());
		} else {
			UserEntity spamUser = new UserEntity();
			spamUser.setMobile(mobile);
			spamUser.setIsRegistered(UserEntityConstant.RegisteredUser.NOT_REGISTERED_USER);
			spamUser.setSpams(UserEntityConstant.SpamedUser.SPAMED_USER);
			UserEntity spammedUser = repository.save(spamUser);
			contact.setContactUserId(spammedUser.getId());
		}
		contact.setIsContact(UserContactEntityConstant.Contact.NOT_CONTACT);
		contact.setIsSpam(UserEntityConstant.SpamedUser.SPAMED_USER.intValue());
		contact.setUserId(loggedInUser.getId());
		contactRepository.save(contact);
	}

	@Override
	public UserBean showUserDetails(String mobile) {
		UserEntity user = userService.findUserByMobile(mobile);
		UserBean userBean = new UserBean();
		BeanUtils.copyProperties(user, userBean);
		UserEntity loggedInUser = getAuthUserDetails();
		Long loggedInUserId = loggedInUser.getId();
		if (userBean != null) {
			UserContactEntity userContact = userContactService.findIfUserIsInContact(userBean.getId(), loggedInUserId);
			if (userBean.getIsRegistered().equals(0) || userContact == null) {
				userBean.setEmail(null);
			}
		}
		return userBean;
	}

	private void convertUserEntityToBeanList(List<UserBean> userBean, List<UserEntity> userEntity) {
		userEntity.stream().forEach(entity -> {
			UserBean bean = new UserBean();
			BeanUtils.copyProperties(entity, bean);
			userBean.add(bean);
		});
	}

	private void saveUserContactNameToUserBean(List<UserBean> userBean, List<UserContactEntity> userContacts, String mobile) {
		if (!userContacts.isEmpty()) {
			userContacts.stream().forEach(userContact -> {
				UserBean userName = new UserBean();
				userName.setName(userContact.getContactName());
				userName.setMobile(mobile);
				userBean.add(userName);
			});
		}
	}

	private UserEntity getAuthUserDetails() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		UserEntity userEntity = userService.findUserByUsername(username);
		return userEntity;
	}
}
