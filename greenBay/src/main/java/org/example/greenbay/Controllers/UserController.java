package org.example.greenbay.Controllers;

import org.example.greenbay.Services.User.UserService;
import org.example.greenbay.dtos.LoginRequest;
import org.example.greenbay.dtos.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
    return userService.registerUser(request);
  }

  @PostMapping("/login")
  public ResponseEntity<Object> register(@RequestBody LoginRequest request) {
    return userService.loginUser(request);
  }
}
