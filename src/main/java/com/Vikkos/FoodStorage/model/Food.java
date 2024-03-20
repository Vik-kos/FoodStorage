package com.Vikkos.FoodStorage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Food {

    @Id
    private String barcode;

    private String name;

    private List<String> ingredients;

    @Embedded
    private Nutrients nutrients;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<FoodLeft> foodLeftList = new ArrayList<>();

}
