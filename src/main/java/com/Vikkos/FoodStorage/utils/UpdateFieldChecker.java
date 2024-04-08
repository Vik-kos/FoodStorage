package com.Vikkos.FoodStorage.utils;

import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@NoArgsConstructor
public class UpdateFieldChecker {
    public <T> void updateFieldIfNotNull(Consumer<T> setter, T value){
        if (value != null) {
            setter.accept(value);
        }
    }
}
