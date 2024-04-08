package com.Vikkos.FoodStorage.controller;

import com.Vikkos.FoodStorage.model.Food;
import com.Vikkos.FoodStorage.service.FoodService;
import com.Vikkos.FoodStorage.utils.CustomExceptions.BarcodeNullException;
import com.Vikkos.FoodStorage.utils.CustomExceptions.FoodNotFoundException;
import com.Vikkos.FoodStorage.utils.ResponseReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/food")
public class FoodController {

    @Autowired
    private FoodService service;

    @GetMapping
    public ResponseEntity<List<Food>> getFoodList() {
        return new ResponseEntity<>(service.findAllFood(), HttpStatus.OK);
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<?> getSingleFoodItem(@PathVariable String barcode) {
        try {
            return new ResponseEntity<>(service.findByBarcode(barcode), HttpStatus.OK);
        }
        catch (FoodNotFoundException e){
            return new ResponseEntity<>("Food not found for barcode: " + barcode, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<?> addFood(@RequestBody String barcode) {
        String readable_barcode;
        try {
            readable_barcode = new ResponseReader(barcode).getBarcode();
            Food createdFood = service.createFoodByAPI(readable_barcode);
            return new ResponseEntity<>(createdFood, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Couldnt read json body", HttpStatus.EXPECTATION_FAILED);
        } catch (BarcodeNullException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (FoodNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Alternative Way to add Food?

    @DeleteMapping("/{barcode}")
    public ResponseEntity<String> removeFoodItem(@PathVariable String barcode) {
        try{
            List<Food> removedFood = service.removeFoodByBarcode(barcode);
            return new ResponseEntity<>("Item removed", HttpStatus.OK);
        }catch (FoodNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{barcode}")
    public ResponseEntity<?> updateFooditem(@PathVariable String barcode, @RequestBody Food food) {
        try{
            Food updatedFood = service.updateFood(barcode, food);
            return new ResponseEntity<>(updatedFood, HttpStatus.OK);
        }catch (FoodNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Create or Update FoodLeft for FoodItem










}
