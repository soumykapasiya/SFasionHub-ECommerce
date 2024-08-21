package com.kapasiya.fashionhub.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {
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