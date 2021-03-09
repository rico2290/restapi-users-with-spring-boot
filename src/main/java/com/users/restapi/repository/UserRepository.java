package com.users.restapi.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.users.restapi.models.User;


public interface UserRepository extends JpaRepository<User, Long> {

	User findById(long id);
}
