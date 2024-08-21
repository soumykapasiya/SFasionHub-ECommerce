package com.kapasiya.fashionhub.controller;

import com.kapasiya.fashionhub.dto.PurchaseDTO;
import com.kapasiya.fashionhub.serviceimpl.PurchaseServiceImpl;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final PurchaseServiceImpl purchaseService;

    @PostMapping("/booking")
    public String finalBooking(@RequestBody Map<String, Object> data) throws RazorpayException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();

        PurchaseDTO purchaseDTO = PurchaseDTO.builder()
                .firstName((String) data.get("firstName"))
                .lastName((String) data.get("lastName"))
                .amount(Double.parseDouble((String) data.get("amount")))
                .address1((String) data.get("add1"))
                .address2((String) data.get("add2"))
                .zipCode((String) data.get("zip"))
                .phone((String) data.get("phone"))
                .formEmail((String) data.get("email"))
                .additional((String) data.get("additional"))
                .build();

        purchaseService.updatePurchase(purchaseDTO, user);

        // Payment API Integration..........

        var client = new RazorpayClient("rzp_test_CAOmLVEJtoNizm", "hChC99xBGSoEnQQRMj07GGOR");

        JSONObject object = new JSONObject();

        object.put("amount", purchaseDTO.getAmount() * 100);
        object.put("currency", "INR");
        object.put("receipt", "txn_75563");

        Order order = client.orders.create(object);

        return order.toString();
    }
}
