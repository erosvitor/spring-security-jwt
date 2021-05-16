package com.ctseducare.springsecurityjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ctseducare.springsecurityjwt.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  
  User findByUserName(@Param("userName") String userName);

}
