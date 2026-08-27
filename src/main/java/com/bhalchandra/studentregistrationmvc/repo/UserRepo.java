package com.bhalchandra.studentregistrationmvc.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhalchandra.studentregistrationmvc.model.Users;


@Repository
public interface UserRepo extends JpaRepository<Users,Integer>{

	 Optional<Users> findByUsername(String username);
	 
}
