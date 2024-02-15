package org.example.greenbay.dtos;

public class AuthenticationResponse {
  private String token;
  private double dollars;

  public AuthenticationResponse(String token, double dollars) {
    this.token = token;
    this.dollars = dollars;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public double getDollars() {
    return dollars;
  }

  public void setDollars(double dollars) {
    this.dollars = dollars;
  }
}
