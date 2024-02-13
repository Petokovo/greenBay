package org.example.greenbay.Repositories;

import org.example.greenbay.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {}
