package com.Vikkos.FoodStorage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nutrients {

    private float carbohydrates;

    @JsonProperty("energy-kcal")
    private float energy_kcal;

    private float fat;

    @JsonProperty("saturated-fat")
    private float saturated_fat;

    private float unsaturated_fat;

    private float fiber;

    private float proteins;

    private float salt;

    private float sodium;

    private float sugars;


    public float getUnsaturated_fat() {
        return (float) Math.round((fat - saturated_fat) * 10) / 10;
    }
}
