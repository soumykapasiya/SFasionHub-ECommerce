package com.kapasiya.fashionhub.serviceimpl;

import com.kapasiya.fashionhub.dto.PurchaseDTO;
import com.kapasiya.fashionhub.entity.Purchase;
import com.kapasiya.fashionhub.exception.custom.BookingException;
import com.kapasiya.fashionhub.mapper.BookingMapper;
import com.kapasiya.fashionhub.repository.PurchaseRepository;
import com.kapasiya.fashionhub.service.PurchaseServiceDef;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseServiceDef {
    private final PurchaseRepository repository;

    @Override
    public Purchase updatePurchase(PurchaseDTO bookingDTO, UserDetails user) {
        try {
            Purchase booking = BookingMapper.toEntity(bookingDTO);
            return repository.save(booking);
        } catch (BookingException ex) {
            throw new BookingException(ex.getMessage());
        }
    }
}
