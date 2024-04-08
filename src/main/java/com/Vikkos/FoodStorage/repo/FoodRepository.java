package com.Vikkos.FoodStorage.repo;

import com.Vikkos.FoodStorage.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface FoodRepository extends JpaRepository<Food, String> {
    Optional<Food> findByName(String name);
    //if barcode is ID no need for these
    Optional<Food> findByBarcode(String barcode);
    @Transactional
    List<Food> removeByBarcode(String barcode);
    //
}