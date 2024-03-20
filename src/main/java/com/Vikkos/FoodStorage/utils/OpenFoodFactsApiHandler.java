package com.Vikkos.FoodStorage.utils;

import com.Vikkos.FoodStorage.model.Food;
import lombok.NoArgsConstructor;


import java.util.Optional;

@NoArgsConstructor
public class OpenFoodFactsApiHandler {

    private final ApiRequester apiRequester = new ApiRequester(
            "https://world.openfoodfacts.org/api/v2/product/",
            "?fields=product_name,nutriments,ingredients");



    public Optional<Food> getFoodByBarcode(String barcode) {
        Food food = new Food();
        food.setBarcode(barcode);
        try{
            String response = apiRequester.requestFoodInfo(barcode);
            ResponseReader responseReader = new ResponseReader(response);

            food.setIngredients(responseReader.getIngredients());
            food.setNutrients(responseReader.getNutrients());
            food.setName(responseReader.getName());
        }
        catch (Exception e){
            return Optional.of(food);
        }


        return Optional.of(food);
    }
}
