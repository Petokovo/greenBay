package org.example.greenbay.Controllers;

import org.example.greenbay.Services.Item.ItemService;
import org.example.greenbay.Services.User.UserService;
import org.example.greenbay.dtos.ItemCreateRequest;
import org.example.greenbay.dtos.LoginRequest;
import org.example.greenbay.dtos.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;

@RestController
public class UserController {
  private final UserService userService;
  private final ItemService itemService;

  @Autowired
  public UserController(UserService userService, ItemService itemService) {
    this.userService = userService;
    this.itemService = itemService;
  }

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
    return userService.registerUser(request);
  }

  @PostMapping("/login")
  public ResponseEntity<Object> register(@RequestBody LoginRequest request)
      throws CredentialException {
    return userService.loginUser(request);
  }

  @PostMapping("/add/item")
  public ResponseEntity<Object> addItem(
      @RequestHeader("Authorization") String authorizationHeader,
      @RequestBody(required = false) ItemCreateRequest itemCreateRequest) {
    return itemService.createItem(authorizationHeader, itemCreateRequest);
  }
}
