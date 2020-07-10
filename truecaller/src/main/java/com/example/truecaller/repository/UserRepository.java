package com.example.truecaller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.truecaller.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	@Query(UserEntity.Query.FIND_REGISTERED_USER_WITH_MOBILE)
	UserEntity findRegisteredUserByMobileNumber(@Param("mobile") String mobile);

	@Query(UserEntity.Query.FIND_USER_WITH_MOBILE)
	UserEntity findUserByMobileNumber(@Param("mobile") String mobile);

	@Query(UserEntity.Query.FIND_USER_WITH_USERNAME)
	UserEntity findRegisteredUserByUsername(@Param("username") String username);

	@Query(UserEntity.Query.FIND_USER_WITH_EXACT_NAME)
	List<UserEntity> searchExactNames(@Param("name") String name);

	@Query(UserEntity.Query.FIND_USER_WITH_MATCHED_NAME)
	List<UserEntity> searchMatchedNames(@Param("name") String name, @Param("part") String part);

}
