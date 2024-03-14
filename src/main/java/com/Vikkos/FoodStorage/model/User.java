package com.Vikkos.FoodStorage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class User {

    //@Id
    //@GeneratedValue( strategy = GenerationType.AUTO)
    //private UUID user_id;
    @Id
    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable = false)
    private String password;


}
