package com.kapasiya.fashionhub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String zipCode;
    private String phone;
    private String accountEmail;
    private String formEmail;
    private String additional;
    private String time;
    private String date;
    private double amount;
    private String productId;
}