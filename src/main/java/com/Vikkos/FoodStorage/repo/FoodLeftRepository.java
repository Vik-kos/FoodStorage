package com.Vikkos.FoodStorage.repo;

import com.Vikkos.FoodStorage.model.FoodLeft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface FoodLeftRepository extends JpaRepository<FoodLeft, UUID> {

    @Query("SELECT fl FROM FoodLeft fl ORDER BY fl.bestBeforeDate")
    List<FoodLeft> findAllSorted();

    @Query("SELECT fl FROM FoodLeft fl WHERE fl.food.barcode = :barcode ORDER BY fl.bestBeforeDate")
    List<FoodLeft> findAllByBarcode(String barcode);
}
