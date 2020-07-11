package com.example.truecaller.constant;

public interface UserContactEntityConstant {

	String TABLE_NAME = "user_contact";

	String ID = "id";

	String USER_ID = "user_id";

	String CONTACT_USER_ID = "contact_user_id";

	String CONTACT_NAME = "contact_name";

	String IS_SPAM = "is_spam";

	String IS_CONTACT = "is_contact";

	String FLAG = "flag";

	interface Contact {
		Integer NOT_CONTACT = 0;
		Integer IS_CONTACT = 1;
	}

	interface Query {
		String FIND_ALL_BY_CONTACT_USER_ID = "select uce from UserContactEntity uce where uce.contactUserId = :contactId and uce.flag = 1";
		String FIND_USER_IN_CONTACT = "select uce from UserContactEntity uce where uce.userId = :userId and uce.contactUserId = :contactId and uce.isContact = 1 and uce.flag = 1";
	}
}
