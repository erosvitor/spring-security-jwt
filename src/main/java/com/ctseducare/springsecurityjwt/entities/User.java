package com.ctseducare.springsecurityjwt.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  
  private Integer id;
  private String userName;
  private String fullName;
  private String password;
  private Boolean accountNonExpired;
  private Boolean accountNonLocked;
  private Boolean credentialsNonExpired;
  private Boolean enabled;
  private Set<Permission> permissions;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "user_name", unique = true)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Column(name = "full_name")
  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Column(name = "password")
  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "account_non_expired")
  public boolean getAccountNonExpired() {
    return this.accountNonExpired;
  }
  
  public void setAccountNonExpired(Boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  @Column(name = "account_non_locked")
  public boolean getAccountNonLocked() {
    return this.accountNonLocked;
  }
  
  public void setAccountNonLocked(Boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  @Column(name = "credentials_non_expired")
  public boolean getCredentialsNonExpired() {
    return this.credentialsNonExpired;
  }
  
  public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  @Column(name = "enabled")
  public boolean getEnabled() {
    return this.enabled;
  }
  
  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_permission",
      joinColumns = { @JoinColumn (name = "id_user") },
      inverseJoinColumns = { @JoinColumn (name = "id_permission")}
  )
  public Set<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(Set<Permission> permissions) {
    this.permissions = permissions;
  }

}