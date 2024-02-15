package org.example.greenbay.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
  @Test
  void test_create_user() {
    User user = new User("Jack", "password");
    assertNotNull(user.getUsername());
    assertNotNull(user.getPassword());
    assertEquals("Jack", user.getUsername());
    assertEquals("password", user.getPassword());
    assertEquals(0, user.getDollars());
  }
}
