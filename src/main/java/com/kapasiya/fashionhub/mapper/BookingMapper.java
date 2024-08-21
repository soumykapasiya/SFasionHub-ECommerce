package com.kapasiya.fashionhub.mapper;

import com.kapasiya.fashionhub.dto.PurchaseDTO;
import com.kapasiya.fashionhub.entity.Purchase;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingMapper {

    private BookingMapper(){
        throw new IllegalArgumentException("Utility class");
    }

    public static Purchase toEntity(PurchaseDTO bookingDTO){
        return Purchase.builder()
                .firstName(bookingDTO.getFirstName())
                .lastName(bookingDTO.getLastName())
                .address1(bookingDTO.getAddress1())
                .address2(bookingDTO.getAddress2())
                .zipCode(bookingDTO.getZipCode())
                .formEmail(bookingDTO.getFormEmail())
                .additional(bookingDTO.getAdditional())
                .amount(bookingDTO.getAmount())
                .phone(bookingDTO.getPhone())
                .accountEmail(bookingDTO.getAccountEmail())
                .date(LocalDate.now().toString())
                .time(LocalTime.now().toString())
                .build();
    }
}
