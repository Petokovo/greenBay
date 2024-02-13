package org.example.greenbay.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, updatable = false)
  private Long id;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "dollars", nullable = false)
  private double dollars;

  @Column(name = "token", unique = true)
  private String token;

  @Column(name = "expires_at")
  private Long expiresAt;

  public User(String username, String password, String token, Long expiresAt) {
    this.username = username;
    this.password = password;
    this.dollars = 0;
    this.token = token;
    this.expiresAt = expiresAt;
  }

  public User() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public double getDollars() {
    return dollars;
  }

  public void setDollars(double dollars) {
    this.dollars = dollars;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Long getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(Long expiresAt) {
    this.expiresAt = expiresAt;
  }
}
