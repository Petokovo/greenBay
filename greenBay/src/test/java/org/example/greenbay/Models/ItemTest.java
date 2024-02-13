package org.example.greenbay.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
  User user;

  @BeforeEach
  void setup() {
    user = new User("Jack", "password", "token", 0L);
  }

  @Test
  void test_create_item() {
    Item item = new Item("Glasses", "New glasses", "https//:", 10, user);
    assertNotNull(item.getName());
    assertNotNull(item.getDescription());
    assertNotNull(item.getPhotoUrl());
    assertNotNull(item.getUser());
  }
}
