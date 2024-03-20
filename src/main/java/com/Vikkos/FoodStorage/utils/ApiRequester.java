package com.Vikkos.FoodStorage.utils;

import lombok.AllArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@AllArgsConstructor
public class ApiRequester {

    private String API_url;
    private String filter;
    public String requestFoodInfo(String barcode) throws Exception {
        //String API_url = "https://world.openfoodfacts.org/api/v2/product/";
        //String filter = "?fields=product_name,nutriments,ingredients";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_url + barcode + filter))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
