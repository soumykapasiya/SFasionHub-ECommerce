package com.kapasiya.fashionhub.repository;

import com.kapasiya.fashionhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByEmail(String email);
}
