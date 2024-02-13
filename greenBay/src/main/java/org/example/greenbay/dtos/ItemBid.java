package org.example.greenbay.dtos;

public class ItemBid {
  private double bid;
  private String username;

  public ItemBid(double bid, String username) {
    this.bid = bid;
    this.username = username;
  }

  public double getBid() {
    return bid;
  }

  public void setBid(double bid) {
    this.bid = bid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
