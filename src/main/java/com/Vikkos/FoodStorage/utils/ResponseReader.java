package com.Vikkos.FoodStorage.utils;

import com.Vikkos.FoodStorage.model.Nutrients;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ResponseReader {

    private final ObjectMapper objectMapper;
    private final JsonNode jsonNode;


    public ResponseReader(String response) throws JsonProcessingException {
        this.objectMapper = new ObjectMapper();
        this.jsonNode = objectMapper.readTree(response);
    }

    public List<String> getIngredients(){
        List<String> ingredients = new ArrayList<>();
        JsonNode ingredientsNode = jsonNode.path("product").path("ingredients");
        for (JsonNode ingredient : ingredientsNode) {
            ingredients.add(ingredient.path("text").asText());
        }
        return ingredients;
    }

    public Nutrients getNutrients() throws JsonProcessingException {
        String nutrientsString = jsonNode.path("product").path("nutriments").toString();
        return objectMapper.readValue(nutrientsString, Nutrients.class);
    }

    public String getName(){
        return jsonNode.path("product").path("product_name").asText();
    }

    public String getBarcode() {return jsonNode.path("barcode").asText();}




}
