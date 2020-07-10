package com.example.truecaller.constant;

public interface UserEntityConstant {

	String TABLE_NAME = "user";

	String ID = "id";

	String USERNAME = "username";

	String PASSWORD = "password";

	String NAME = "name";

	String MOBILE = "mobile";

	String EMAIL = "email";

	String IS_REGISTERED = "is_registered";

	String SPAMS = "spams";

	String FLAG = "flag";

	interface RegisteredUser {
		Integer REGISTERED_USER = 1;
		Integer NOT_REGISTERED_USER = 0;
	}

	interface SpamedUser {
		Long SPAMED_USER = 1l;
		Long NOT_SPAMED_USER = 0l;
	}

	interface Query {
		String FIND_REGISTERED_USER_WITH_MOBILE = "select ue from UserEntity ue where ue.mobile = :mobile and ue.isRegistered = 1 and ue.flag = 1";
		String FIND_USER_WITH_MOBILE = "select ue from UserEntity ue where ue.mobile = :mobile and ue.flag = 1";
		String FIND_USER_WITH_USERNAME = "select ue from UserEntity ue where ue.username = :username and ue.isRegistered=1 and ue.flag=1";
		String FIND_USER_WITH_EXACT_NAME = "select ue from UserEntity ue where ue.name like :name and ue.flag = 1";
		String FIND_USER_WITH_MATCHED_NAME = "select ue from UserEntity ue where ue.name not like :name and ue.name like :part and ue.flag = 1";
	}

}
