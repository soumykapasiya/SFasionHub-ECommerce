package com.kapasiya.fashionhub.repository;

import com.kapasiya.fashionhub.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
    Role findByRole(String name);
}
