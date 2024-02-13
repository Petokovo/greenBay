package org.example.greenbay.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "items")
public class Item {
  @Id
  @Column(name = "id", nullable = false, updatable = false, unique = true)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "photo_url", nullable = false)
  private String photoUrl;

  @Column(name = "starting_price", nullable = false)
  private double startingPrice;

  @Column(name = "last_bid")
  private double lastBid;

  @Column(name = "purchase_price")
  private double purchasePrice;

  @Column(name = "sellable", nullable = false)
  private boolean sellable;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  public Item(String name, String description, String photoUrl, double startingPrice, User user) {
    this.name = name;
    this.description = description;
    this.photoUrl = photoUrl;
    this.startingPrice = startingPrice;
    this.sellable = true;
    this.user = user;
  }

  public Item() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public double getStartingPrice() {
    return startingPrice;
  }

  public void setStartingPrice(double startingPrice) {
    this.startingPrice = startingPrice;
  }

  public double getLastBid() {
    return lastBid;
  }

  public void setLastBid(double lastBid) {
    this.lastBid = lastBid;
  }

  public double getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(double purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  public boolean isSellable() {
    return sellable;
  }

  public void setSellable(boolean sellable) {
    this.sellable = sellable;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
