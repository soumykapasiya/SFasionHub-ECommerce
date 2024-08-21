package com.kapasiya.fashionhub.controller;


import com.kapasiya.fashionhub.dto.UserLoginDTO;
import com.kapasiya.fashionhub.dto.UserRegisteredDTO;
import com.kapasiya.fashionhub.global.GlobalData;
import com.kapasiya.fashionhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        GlobalData.cart.clear();
        return "login";
    }

    @ModelAttribute("userLogin")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @PostMapping("/login")
    public void loginUser(@ModelAttribute("userLogin") UserLoginDTO userLoginDTO) {
        userService.loadUserByUsername(userLoginDTO.getEmail());
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userRegister") UserRegisteredDTO userRegisteredDTO) {
        userService.save(userRegisteredDTO);
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }
}
