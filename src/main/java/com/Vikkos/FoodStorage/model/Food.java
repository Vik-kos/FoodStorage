package com.Vikkos.FoodStorage.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.List;

@Getter
@Setter
@Entity
public class Food {

    @Id
    @GeneratedValue
    private UUID food_id;

    private String name;

    private Integer barcode;

    private List<String> ingredients;

    private String best_before_date;

    @ElementCollection
    private List<Nutrient> nutrients;

}
