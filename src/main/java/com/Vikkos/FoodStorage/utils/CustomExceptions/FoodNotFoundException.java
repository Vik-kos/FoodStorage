package com.Vikkos.FoodStorage.utils.CustomExceptions;

public class FoodNotFoundException extends Exception{
    public FoodNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
