package com.kapasiya.fashionhub.repository;

import com.kapasiya.fashionhub.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findAllById(int id);

    boolean existsByName(String name);
}
