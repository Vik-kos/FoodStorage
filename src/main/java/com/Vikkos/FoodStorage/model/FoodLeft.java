package com.Vikkos.FoodStorage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class FoodLeft {

    @Id
    //@GeneratedValue
    private UUID id;

    private String bestBeforeDate;

    private Float amount;

    private String amountType;

    @ManyToOne
    @JoinColumn(name = "barcode")
    @JsonIgnore
    private Food food;

}
