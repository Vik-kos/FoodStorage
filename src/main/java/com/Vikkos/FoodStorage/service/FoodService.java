package com.Vikkos.FoodStorage.service;

import com.Vikkos.FoodStorage.model.Food;
import com.Vikkos.FoodStorage.repo.FoodRepository;
import com.Vikkos.FoodStorage.utils.OpenFoodFactsApiHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository repository;

    private final OpenFoodFactsApiHandler apiHandler = new OpenFoodFactsApiHandler();

    public List<Food> findAllFood(){
        return repository.findAll();
    }

    //public Optional<Food> findFoodById(UUID food_id){return repository.findById(food_id);}

    public Optional<Food> findByBarcode(String barcode){
        Optional<Food> searchedFood = repository.findByBarcode(barcode);
        if (searchedFood.isEmpty()){
            return apiHandler.getFoodByBarcode(barcode);
        }else return searchedFood;
    }

    public Optional<Food> findByName(String name){
        return repository.findByName(name);
    }

    /*
    public void createFood(String name, Integer barcode, List<String> ingredients, List<Nutrient> nutrients){
        Food food = new Food();
        food.setName(name);
        food.setBarcode(barcode);
        food.setIngredients(ingredients);
        food.setNutrients(nutrients);
        repository.save(food);

    }
     */
    public Food createFood(Food food){
        return repository.save(food);
    }
}

