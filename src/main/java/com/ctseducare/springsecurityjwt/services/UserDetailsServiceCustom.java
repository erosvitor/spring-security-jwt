package com.ctseducare.springsecurityjwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ctseducare.springsecurityjwt.entities.User;
import com.ctseducare.springsecurityjwt.repositories.UserRepository;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
  
  @Autowired
  private UserRepository repository;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repository.findByUserName(username);
    if (user == null) {
      throw new UsernameNotFoundException("Username " + username + " not found");
    }
    return new UserDetailsCustom(user);
  }
  
}
