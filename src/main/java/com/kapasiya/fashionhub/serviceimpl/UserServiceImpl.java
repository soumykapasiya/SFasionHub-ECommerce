package com.kapasiya.fashionhub.serviceimpl;

import com.kapasiya.fashionhub.dto.UserRegisteredDTO;
import com.kapasiya.fashionhub.entity.Role;
import com.kapasiya.fashionhub.entity.User;
import com.kapasiya.fashionhub.exception.custom.UserException;
import com.kapasiya.fashionhub.repository.RoleRepository;
import com.kapasiya.fashionhub.repository.UserRepository;
import com.kapasiya.fashionhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("Invalid username or password.");
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
        } catch (UserException ux) {
            throw new UserException(ux.getMessage());
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).toList();
    }

    @Override
    public User save(UserRegisteredDTO userRegisteredDTO) {
        try {
            Role role = new Role();
            if (userRegisteredDTO.getRole() == null)
                role = roleRepo.findByRole("USER");

            else if (userRegisteredDTO.getRole().equals("ADMIN"))
                role = roleRepo.findByRole("ADMIN");

            User user = User.builder()
                    .firstName(userRegisteredDTO.getFname())
                    .lastName(userRegisteredDTO.getLname())
                    .email(userRegisteredDTO.getEmail())
                    .password(passwordEncoder.encode(userRegisteredDTO.getPassword()))
                    .build();
            user.setRole(role);

            return userRepository.save(user);
        } catch (UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }
}
