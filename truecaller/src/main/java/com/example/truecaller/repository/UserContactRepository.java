package com.example.truecaller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.truecaller.entity.UserContactEntity;

@Repository
public interface UserContactRepository extends CrudRepository<UserContactEntity, Long> {

	@Query(UserContactEntity.Query.FIND_ALL_BY_CONTACT_USER_ID)
	List<UserContactEntity> findAllByContactUserId(@Param("contactId") Long contactId);

	@Query(UserContactEntity.Query.FIND_USER_IN_CONTACT)
	UserContactEntity findUserInContact(@Param("userId") Long userId, @Param("contactId") Long contactId);

}
