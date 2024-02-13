package org.example.greenbay.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "bids")
public class Bid {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, updatable = false)
  private Long id;

  @Column(name = "bid_value", nullable = false)
  private double bidValue;

  @Column(name = "username", nullable = false)
  private String username;

  public Bid(double bidValue, String username) {
    this.bidValue = bidValue;
    this.username = username;
  }

  public Bid() {}

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public double getBidValue() {
    return bidValue;
  }

  public void setBidValue(double bidValue) {
    this.bidValue = bidValue;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
