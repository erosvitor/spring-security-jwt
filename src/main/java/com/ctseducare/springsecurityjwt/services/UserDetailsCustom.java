package com.ctseducare.springsecurityjwt.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ctseducare.springsecurityjwt.entities.Permission;
import com.ctseducare.springsecurityjwt.entities.User;

public class UserDetailsCustom implements UserDetails {

  private static final long serialVersionUID = 7979568419364461050L;
  
  private User user;
  
  public UserDetailsCustom(User user) {
    this.user = user;  
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // Attention! The prefix 'ROLE_' is required by Spring Security
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    for (Permission permission : user.getPermissions()) {
      GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + permission.getDescription());
      grantedAuthorities.add(grantedAuthority);
    }
    return grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUserName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return user.getAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return user.getAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return user.getCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return user.getEnabled();
  }

}
