package org.example.greenbay.Services.User;

import org.example.greenbay.dtos.LoginRequest;
import org.example.greenbay.dtos.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<Object> registerUser(RegisterRequest request);

  ResponseEntity<Object> loginUser(LoginRequest request);
}
