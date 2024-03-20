package com.Vikkos.FoodStorage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class FoodLeft {

    @Id
    @GeneratedValue
    private Long id;

    private String best_before_date;

    private String amount;

    @ManyToOne
    @JoinColumn(name = "barcode")
    private Food food;
}
