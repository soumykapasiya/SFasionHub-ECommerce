package com.kapasiya.fashionhub.mapper;

import com.kapasiya.fashionhub.dto.ProductDTO;
import com.kapasiya.fashionhub.entity.Products;

public class ProductMapper {

    private ProductMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ProductDTO toDto(Products product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .brand(product.getBrand())
                .description(product.getDescription())
                .categoryID(product.getCategory().getId())
                .imageName(product.getImageName())
                .build();
    }

}
