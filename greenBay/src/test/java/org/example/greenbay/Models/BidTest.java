package org.example.greenbay.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BidTest {
  User user;
  Item item;

  @BeforeEach
  void setup() {
    user = new User("Jack", "password");
    item = new Item("Glasses", "New glasses", "https//:", 10, user);
  }

  @Test
  void test_create_Bid() {
    Bid bid = new Bid(user.getDollars(), user.getUsername(), item);

    assertNotNull(bid.getUsername());
    assertNotNull(bid.getItem());
    assertEquals("Jack", bid.getUsername());
  }
}
