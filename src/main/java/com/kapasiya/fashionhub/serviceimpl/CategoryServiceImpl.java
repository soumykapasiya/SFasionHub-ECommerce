package com.kapasiya.fashionhub.serviceimpl;

import com.kapasiya.fashionhub.entity.Category;
import com.kapasiya.fashionhub.exception.custom.CategoryException;
import com.kapasiya.fashionhub.repository.CategoryRepository;
import com.kapasiya.fashionhub.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void addCategory(Category category) {
        try {
            if (categoryRepository.existsByName(category.getName())) {
                throw new CategoryException("Category already exists");
            }
            categoryRepository.save(category);
        } catch (CategoryException ex) {
            throw new CategoryException(ex.getMessage());
        }
    }

    @Override
    public List<Category> getAllCategory() {
        try {
            return categoryRepository.findAll();
        } catch (CategoryException ex) {
            throw new CategoryException(ex.getMessage());
        }
    }

    @Override
    public void deleteCatByID(int id) {
        try {
            categoryRepository.deleteById(id);
            log.info("Deleted Category with ID: {}", id);
        } catch (CategoryException ex) {
            throw new CategoryException(ex.getMessage());
        }
    }

    @Override
    public Category findCatById(int id) {
        try {
            return categoryRepository.findAllById(id).orElseThrow();
        } catch (CategoryException ex) {
            throw new CategoryException(ex.getMessage());
        }
    }
}
