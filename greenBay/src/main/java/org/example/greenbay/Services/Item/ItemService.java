package org.example.greenbay.Services.Item;

import org.example.greenbay.dtos.ItemCreateRequest;
import org.springframework.http.ResponseEntity;

public interface ItemService {
  ResponseEntity<Object> createItem(String token, ItemCreateRequest itemCreateRequest);

  boolean isValidPhotoURL(String photoUrl);
}
