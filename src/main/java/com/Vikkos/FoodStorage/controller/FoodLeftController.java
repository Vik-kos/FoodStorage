package com.Vikkos.FoodStorage.controller;

import com.Vikkos.FoodStorage.model.Food;
import com.Vikkos.FoodStorage.model.FoodLeft;
import com.Vikkos.FoodStorage.service.FoodLeftService;
import com.Vikkos.FoodStorage.service.FoodService;
import com.Vikkos.FoodStorage.utils.CustomExceptions.FoodNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/foodleft")
public class FoodLeftController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodLeftService foodLeftService;

    @GetMapping
    public ResponseEntity<List<FoodLeft>> getFoodLeftList() {
        return new ResponseEntity<>(foodLeftService.findAllFoodLeft(), HttpStatus.OK);
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<?> getSingleFoodItem(@PathVariable String barcode) {
        try {
            return new ResponseEntity<>(foodLeftService.findAllByBarcode(barcode), HttpStatus.OK);
        }
        catch (FoodNotFoundException e){
            return new ResponseEntity<>(e.getMessage() + " : " + barcode, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/{barcode}")
    public ResponseEntity<?> addFoodLeft(@PathVariable String barcode, @RequestBody FoodLeft foodleft){
        try{
            FoodLeft foodLeft = foodLeftService.createFoodleft(barcode, foodleft);
            return new ResponseEntity<>(foodleft, HttpStatus.CREATED);
        }
        catch (FoodNotFoundException e){
            return new ResponseEntity<>("Food not found for barcode: " + barcode, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{barcode}/{id}")
    public ResponseEntity<?> removeFoodLeft(@PathVariable String barcode, @PathVariable UUID id) {
        try{
            Food removedFood = foodLeftService.removeFoodLeft(barcode, id);
            return new ResponseEntity<>(removedFood, HttpStatus.OK);
        }catch (FoodNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{barcode}/{id}")
    public  ResponseEntity<?> updateFoodLeft(@PathVariable String barcode, @PathVariable UUID id, @RequestBody FoodLeft foodLeftToUpdate){
        try{
            FoodLeft foodLeft = foodLeftService.updateFoodLeft(barcode, id, foodLeftToUpdate);
            return new ResponseEntity<>(foodLeft, HttpStatus.OK);
        }catch (FoodNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
