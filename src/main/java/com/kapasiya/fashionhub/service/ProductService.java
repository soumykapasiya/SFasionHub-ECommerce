package com.kapasiya.fashionhub.service;

import com.kapasiya.fashionhub.entity.Products;

import java.util.List;

public interface ProductService {
    List<Products> getAllProducts();

    void addProducts(Products products);

    void deleteProductByID(long id);

    Products findProductById(long id);

    List<Products> getAllProductsByCategoryId(int id);

    List<Products> getLimitedProducts();

    List<Products> getByName(String name);
}