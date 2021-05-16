package com.ctseducare.springsecurityjwt.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctseducare.springsecurityjwt.jwt.JwtTokenProvider;
import com.ctseducare.springsecurityjwt.services.UserDetailsServiceCustom;
import com.ctseducare.springsecurityjwt.valueobjects.AccountCredentialsVO;

@RestController
public class AuthController {
  
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtTokenProvider tokenProvider;
  
  @Autowired
  UserDetailsServiceCustom userDetailsServiceCustom;
  
  @PostMapping(value = "/auth/signin")
  public ResponseEntity<Map<Object, Object>> signin(@RequestBody AccountCredentialsVO data) {
    String username = data.getUsername();
    String pasword = data.getPassword();
      
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, pasword));

    UserDetails userDetails = userDetailsServiceCustom.loadUserByUsername(username);
    if (userDetails == null) {
      throw new UsernameNotFoundException("Username " + username + " not found!");
    }

    String token = tokenProvider.createToken(username, userDetails.getAuthorities());
      
    Map<Object, Object> model = new HashMap<>();
    model.put("username", username);
    model.put("token", token);

    return ResponseEntity.ok(model);
  }
  
}
