package com.Vikkos.FoodStorage.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Embeddable
public class Nutrient {

    private String name;

    private float amount;

    private String unit;
}
