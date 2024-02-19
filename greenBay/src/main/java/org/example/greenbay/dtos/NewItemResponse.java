package org.example.greenbay.dtos;

import org.example.greenbay.Models.User;

public class NewItemResponse {
  private String name;
  private String description;
  private String photoUrl;
  private Integer startingPrice;
  private boolean sellable;
  private User user;

  public NewItemResponse(
      String name, String description, String photoUrl, Integer startingPrice, User user) {
    this.name = name;
    this.description = description;
    this.photoUrl = photoUrl;
    this.startingPrice = startingPrice;
    this.sellable = true;
    this.user = user;
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

  public Integer getStartingPrice() {
    return startingPrice;
  }

  public void setStartingPrice(Integer startingPrice) {
    this.startingPrice = startingPrice;
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
