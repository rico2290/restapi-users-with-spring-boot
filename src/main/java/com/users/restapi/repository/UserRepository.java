package com.users.restapi.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import com.users.restapi.models.User;


public interface UserRepository extends JpaRepository<User, Long> {


	User findById(long id);
	
	//List<User> findByNameContaining(String nome);
	User findByEmail(String email);
	User findOneByNome(String nome);
	

	
	 @Query( value="SELECT * FROM TB_USER u where u.id = :id", nativeQuery= true)
	 User findByIdDetails(@PathVariable("id") long id );
	 
	 default User findByIUser(long id) {
		  return findByIdDetails(id);
		}

}
