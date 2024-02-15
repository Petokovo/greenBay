package org.example.greenbay.Services.User;

import org.example.greenbay.Models.User;
import org.example.greenbay.Repositories.UserRepository;
import org.example.greenbay.Services.JWT.JwtService;
import org.example.greenbay.dtos.AuthenticationResponse;
import org.example.greenbay.dtos.LoginRequest;
import org.example.greenbay.dtos.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Autowired
  public UserServiceImpl(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      JwtService jwtService,
      AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public ResponseEntity<Object> registerUser(RegisterRequest request) {
    if (request.getUsername() == null || request.getUsername().isEmpty()) {
      return ResponseEntity.status(400).body("Username is required");
    } else if (request.getPassword() == null || request.getPassword().isEmpty()) {
      return ResponseEntity.status(400).body("Password is required");
    } else if (userRepository.existsByUsername(request.getUsername())) {
      return ResponseEntity.status(400).body("Username is already taken");
    } else if (request.getUsername().length() < 4) {
      return ResponseEntity.status(400).body("Username must be at least 4 characters long");
    } else if (request.getPassword().length() < 8) {
      return ResponseEntity.status(400).body("Password must be at least 8 characters long");
    }
    User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()));
    if (user == null) {
      return ResponseEntity.status(400).body("Unknown error");
    }
    userRepository.save(user);
    return ResponseEntity.ok().body("Successful creation");
  }

  @Override
  public ResponseEntity<Object> loginUser(LoginRequest request) throws CredentialException {
    if (request.getUsername() == null || request.getUsername().isEmpty()) {
      return ResponseEntity.status(400).body("Username is required");
    } else if (request.getPassword() == null || request.getPassword().isEmpty()) {
      return ResponseEntity.status(400).body("Password is required");
    } else if (!userRepository.existsByUsername(request.getUsername())) {
      return ResponseEntity.status(400).body("User not found");
    }
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    } catch (AuthenticationException e) {
      throw new CredentialException("Invalid password");
    }
    User user = userRepository.findByUsername(request.getUsername());

    String jwtToken = jwtService.generateToken(user);
    AuthenticationResponse authenticationResponse =
        new AuthenticationResponse(jwtToken, user.getDollars());
    return ResponseEntity.ok().body(authenticationResponse);
  }
}
