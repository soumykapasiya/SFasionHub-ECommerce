package com.kapasiya.fashionhub.dto;


import lombok.Data;

@Data
public class UserRegisteredDTO {


    private String fname;

    private String lname;

    private String email;

    private String password;

    private String role;



    public UserRegisteredDTO() {
        super();
    }

    public UserRegisteredDTO(String role) {
        super();
        this.role = role;
    }

}


