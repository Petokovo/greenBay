package org.example.greenbay.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
  @Test
  void test_create_user() {
    User user = new User("Jack", "password", "token", 0L);
    assertNotNull(user.getUsername());
    assertNotNull(user.getPassword());
    assertNotNull(user.getToken());
    assertNotNull(user.getExpiresAt());

    assertEquals("Jack", user.getUsername());
    assertEquals("password", user.getPassword());
    assertEquals("token", user.getToken());
    assertEquals(0, user.getDollars());
    assertEquals(0L, user.getExpiresAt());
  }
}
