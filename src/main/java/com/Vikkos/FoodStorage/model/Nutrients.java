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

    //@JsonProperty("carbohydrates")
    private float carbohydrates;

    @JsonProperty("energy-kcal")
    private float energy_kcal;

    @JsonProperty("fat")
    private float fat;

    @JsonProperty("saturated-fat")
    private float saturated_fat;

    private float unsaturated_fat;

    @JsonProperty("fiber")
    private float fiber;

    @JsonProperty("proteins")
    private float proteins;

    @JsonProperty("salt")
    private float salt;

    @JsonProperty("sodium")
    private float sodium;

    @JsonProperty("sugars")
    private float sugars;


    public float getUnsaturated_fat() {
        return (float) Math.round((fat - saturated_fat) * 10) / 10;
    }
}
