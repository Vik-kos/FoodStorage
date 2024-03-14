package com.Vikkos.FoodStorage.service;

import com.Vikkos.FoodStorage.model.Food;
import com.Vikkos.FoodStorage.model.Nutrient;
import com.Vikkos.FoodStorage.repo.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class FoodService {

    @Autowired
    private FoodRepository repository;


    public List<Food> findAllFood(){
        return repository.findAll();
    }

    public Optional<Food> findFoodById(UUID food_id){
        return repository.findById(food_id);
    }

    public Optional<Food> findByBarcode(Integer barcode){
        return repository.findByBarcode(barcode);
    }

    public Optional<Food> findByName(String name){
        return repository.findByName(name);
    }

    public void createFood(String name, Integer barcode, List<String> ingredients, List<Nutrient> nutrients){
        Food food = new Food();
        food.setName(name);
        food.setBarcode(barcode);
        food.setIngredients(ingredients);
        food.setNutrients(nutrients);
        repository.save(food);

    }
}

