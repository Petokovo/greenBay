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

  @ManyToOne
  @JoinColumn(name = "item_id", nullable = false)
  private Item item;

  public Bid(double bidValue, String username) {
    this.bidValue = bidValue;
    this.username = username;
  }

  public Bid() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }
}
