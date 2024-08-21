package com.kapasiya.fashionhub.service;

import com.kapasiya.fashionhub.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService
{
    public void addCategory(Category category);

    public List<Category> getAllCategory();

    public void deleteCatByID(int id);

    public Category findCatById(int id);
}
