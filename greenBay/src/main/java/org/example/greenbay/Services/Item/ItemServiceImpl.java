package org.example.greenbay.Services.Item;

import org.example.greenbay.Models.Item;
import org.example.greenbay.Models.User;
import org.example.greenbay.Repositories.ItemRepository;
import org.example.greenbay.Repositories.UserRepository;
import org.example.greenbay.Services.JWT.JwtService;
import org.example.greenbay.dtos.ItemCreateRequest;
import org.example.greenbay.dtos.NewItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ItemServiceImpl implements ItemService {
  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final ItemRepository itemRepository;

  @Autowired
  public ItemServiceImpl(
      JwtService jwtService, UserRepository userRepository, ItemRepository itemRepository) {
    this.jwtService = jwtService;
    this.userRepository = userRepository;
    this.itemRepository = itemRepository;
  }

  @Override
  public ResponseEntity<Object> createItem(String token, ItemCreateRequest itemCreateRequest) {
    String jwtToken = token.substring(7);
    String username = jwtService.extractUsername(jwtToken);
    User user = userRepository.findByUsername(username);
    if (user == null) {
      return ResponseEntity.status(404).body("User not found!");
    } else if (itemCreateRequest == null) {
      return ResponseEntity.status(400).body("No item provided!");
    } else if (itemCreateRequest.getName() == null || itemCreateRequest.getName().isEmpty()) {
      return ResponseEntity.status(400).body("Name is required!");
    } else if (itemCreateRequest.getDescription() == null
        || itemCreateRequest.getDescription().isEmpty()) {
      return ResponseEntity.status(400).body("Description is required!");
    } else if (itemCreateRequest.getPhotoUrl() == null
        || itemCreateRequest.getPhotoUrl().isEmpty()) {
      return ResponseEntity.status(400).body("Photo url is required!");
    } else if (!isValidPhotoURL(itemCreateRequest.getPhotoUrl())) {
      return ResponseEntity.status(400).body("Photo url is not valid");
    } else if (itemCreateRequest.getStartingPrice() == null) {
      return ResponseEntity.status(400).body("Starting price is required!");
    } else if (itemCreateRequest.getStartingPrice() < 0) {
      return ResponseEntity.status(400).body("Price can not be less than 0!");
    } else if (itemCreateRequest.getStartingPrice() % 1 != 0) {
      return ResponseEntity.status(400).body("Starting price must be whole number!");
    }
    Item item =
        new Item(
            itemCreateRequest.getName(),
            itemCreateRequest.getDescription(),
            itemCreateRequest.getPhotoUrl(),
            itemCreateRequest.getStartingPrice().intValue(),
            user);
    userRepository.save(user);
    itemRepository.save(item);
    NewItemResponse newItemResponse =
        new NewItemResponse(
            itemCreateRequest.getName(),
            itemCreateRequest.getDescription(),
            itemCreateRequest.getPhotoUrl(),
            itemCreateRequest.getStartingPrice().intValue(),
            user);
    return ResponseEntity.status(200).body(newItemResponse);
  }

  @Override
  public boolean isValidPhotoURL(String photoUrl) {
    try {
      // Create a URL object from the given URL string
      URL url = new URL(photoUrl);
      // Open a connection to the URL
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      // Set the request method to "HEAD" to make a lightweight request
      connection.setRequestMethod("HEAD");
      // Get the HTTP response code
      int responseCode = connection.getResponseCode();
      // Check if the response code is HTTP_OK (200)
      return responseCode == HttpURLConnection.HTTP_OK;
    } catch (Exception e) {
      // If an exception occurs (e.g., invalid URL or connection error), return false
      return false;
    }
  }
}
