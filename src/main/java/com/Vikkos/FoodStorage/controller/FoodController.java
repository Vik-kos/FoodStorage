package com.Vikkos.FoodStorage.controller;

import com.Vikkos.FoodStorage.model.Food;
import com.Vikkos.FoodStorage.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/food")
public class FoodController {

    @Autowired
    private FoodService service;

    @GetMapping
    public ResponseEntity<List<Food>> getFoodList() {
        return new ResponseEntity<List<Food>>(service.findAllFood(), HttpStatus.OK);
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<Optional<Food>> getSingleFoodItem(@PathVariable String barcode) {
        return new ResponseEntity<Optional<Food>>(service.findByBarcode(barcode), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Food> addFood(@RequestBody Food food){
        return new ResponseEntity<Food>(service.createFood(food), HttpStatus.CREATED);
    }

    //@PostMapping("/add")
    //public ResponseEntity<Food> addFoodWithOpenFood(@RequestBody Food food){return new ResponseEntity<Food>(service.createFood(food), HttpStatus.CREATED);}
}
