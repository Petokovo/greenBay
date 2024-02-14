package org.example.greenbay.Repositories;

import org.example.greenbay.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);

  boolean existsByUsername(String username);

  @Query("SELECT user.password FROM User user WHERE user.username = :username")
  String getPasswordFromUser(String username);
}
