package com.kapasiya.fashionhub.service;

import com.kapasiya.fashionhub.dto.UserRegisteredDTO;
import com.kapasiya.fashionhub.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
    User save(UserRegisteredDTO userRegisteredDTO);

}
