package com.kapasiya.fashionhub.service;

import com.kapasiya.fashionhub.dto.PurchaseDTO;
import com.kapasiya.fashionhub.entity.Purchase;
import org.springframework.security.core.userdetails.UserDetails;

public interface PurchaseServiceDef
{
    Purchase updatePurchase(PurchaseDTO bookingDTO, UserDetails user);
}
