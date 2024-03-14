package com.Vikkos.FoodStorage.repo;

import com.Vikkos.FoodStorage.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {
    Optional<Food> findByName(String name);
    Optional<Food> findByBarcode(Integer barcode);

}