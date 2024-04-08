package com.Vikkos.FoodStorage.service;

import com.Vikkos.FoodStorage.model.Food;
import com.Vikkos.FoodStorage.repo.FoodRepository;
import com.Vikkos.FoodStorage.utils.CustomExceptions.BarcodeNullException;
import com.Vikkos.FoodStorage.utils.CustomExceptions.FoodNotFoundException;
import com.Vikkos.FoodStorage.utils.OpenFoodFactsApiHandler;
import com.Vikkos.FoodStorage.utils.UpdateFieldChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository repository;

    private final OpenFoodFactsApiHandler apiHandler = new OpenFoodFactsApiHandler();

    private final UpdateFieldChecker updateFieldChecker = new UpdateFieldChecker();

    public List<Food> findAllFood(){
        return repository.findAll();
    }

    public Food findByBarcode(String barcode) throws FoodNotFoundException {
        Optional<Food> searchedFoodOpt = repository.findByBarcode(barcode);
        if (searchedFoodOpt.isEmpty()){
            throw new FoodNotFoundException("Food not found inside database!");
        }
        return searchedFoodOpt.get();
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
    public Food createFoodByAPI(String barcode) throws FoodNotFoundException, BarcodeNullException {
        //todo maybe combine with normal create
        if (barcode.isBlank()){
            throw new BarcodeNullException("Pls use 'barcode' in your json format { barcode : 123456789 }");
        }
        Optional<Food> searchedFoodOpt = apiHandler.getFoodByBarcode(barcode);
        if (searchedFoodOpt.isEmpty()){
            throw new FoodNotFoundException("Food not found inside OPENFOODFACTS database for barcode : " + barcode);
        }
        Food searchedFood = searchedFoodOpt.get();
        repository.save(searchedFood);
        return searchedFood;

    }

    public Food updateFood(String barcode, Food updatedFood) throws FoodNotFoundException {
        Optional<Food> searchedFoodOpt = repository.findByBarcode(barcode);
        if (searchedFoodOpt.isEmpty()){
            throw new FoodNotFoundException("Food not found inside database!");
        }
        Food searchedFood = searchedFoodOpt.get();

        updateFieldChecker.updateFieldIfNotNull(searchedFood::setName, updatedFood.getName());
        updateFieldChecker.updateFieldIfNotNull(searchedFood::setNutrients, updatedFood.getNutrients());
        updateFieldChecker.updateFieldIfNotNull(searchedFood::setIngredients, updatedFood.getIngredients());

        repository.save(searchedFood);
        return searchedFood;
    }



    public List<Food> removeFoodByBarcode(String barcode) throws FoodNotFoundException {
        List<Food> removedFood = repository.removeByBarcode(barcode);
        if (removedFood.isEmpty()){
            throw new FoodNotFoundException("There is nothing to be deleted");
        }
        return removedFood;
    }






}

