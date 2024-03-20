package com.Vikkos.FoodStorage.repo;

import com.Vikkos.FoodStorage.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FoodRepository extends JpaRepository<Food, String> {
    Optional<Food> findByName(String name);
    Optional<Food> findByBarcode(String barcode);

}