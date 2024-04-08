package com.Vikkos.FoodStorage.utils.CustomExceptions;

public class BarcodeNullException extends Exception{
    public BarcodeNullException(String errorMessage){
        super(errorMessage);
    }
}
