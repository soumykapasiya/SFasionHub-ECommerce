package com.kapasiya.fashionhub.repository;

import com.kapasiya.fashionhub.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {
}
