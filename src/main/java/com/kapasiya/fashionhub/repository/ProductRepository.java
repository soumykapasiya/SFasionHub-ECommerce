package com.kapasiya.fashionhub.repository;

import com.kapasiya.fashionhub.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products,Long>
{
    Optional<List<Products>> findAllByCategory_Id(int id);

    List<Products> findByCategory_Name(String name);
}
