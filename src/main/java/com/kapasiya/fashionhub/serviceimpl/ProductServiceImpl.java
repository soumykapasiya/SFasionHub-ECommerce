package com.kapasiya.fashionhub.serviceimpl;

import com.kapasiya.fashionhub.entity.Products;
import com.kapasiya.fashionhub.exception.custom.ProductException;
import com.kapasiya.fashionhub.repository.ProductRepository;
import com.kapasiya.fashionhub.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Products> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (ProductException e) {
            throw new ProductException(e.getMessage());
        }
    }

    @Override
    public void addProducts(Products products) {
        try {
            productRepository.save(products);
        } catch (ProductException ex) {
            throw new ProductException(ex.getMessage());
        }
    }

    @Override
    public void deleteProductByID(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Products findProductById(long id) {
        try {
            Products product = productRepository.findById(id).orElse(null);
            if (product == null) {
                throw new ProductException("Product not found");
            }
            return product;
        } catch (ProductException ex) {
            throw new ProductException(ex.getMessage());
        }
    }

    @Override
    public List<Products> getAllProductsByCategoryId(int id) {
        try {
            List<Products> products = productRepository.findAllByCategory_Id(id).orElse(null);
            if (products == null) {
                throw new ProductException("Product not found in Category");
            }
            return products;
        } catch (ProductException ex) {
            throw new ProductException(ex.getMessage());
        }
    }

    @Override
    public List<Products> getLimitedProducts() {
        try {
            List<Products> allProducts = productRepository.findAll();
            if (allProducts.isEmpty()) {
                return Collections.emptyList();
            }
            Collections.shuffle(allProducts);
            return allProducts.subList(0, Math.min(8, allProducts.size()));
        } catch (ProductException ex) {
            throw new ProductException(ex.getMessage());
        }
    }

    @Override
    public List<Products> getByName(String name) {
        try {
            List<Products> productByName = productRepository.findByCategory_Name(name);
            if (productByName.isEmpty()) {
                return Collections.emptyList();
            }
            return productByName.subList(0, Math.min(3, productByName.size()));
        } catch (ProductException ex) {
            throw new ProductException(ex.getMessage());
        }
    }

}
