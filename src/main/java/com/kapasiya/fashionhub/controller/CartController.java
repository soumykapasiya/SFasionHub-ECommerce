package com.kapasiya.fashionhub.controller;


import com.kapasiya.fashionhub.entity.Products;
import com.kapasiya.fashionhub.global.GlobalData;
import com.kapasiya.fashionhub.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final ProductService productService;
    private int shippingCost = 0;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id) {
        GlobalData.cart.add(productService.findProductById(id));
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("shippingCost", getShippingCost());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Products::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index) {
        GlobalData.cart.remove(index);
        shippingCost = 0;
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("shippingCost", getShippingCost());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Products::getPrice).sum());

        return "checkout";
    }

    public int getShippingCost() {
        if (!GlobalData.cart.isEmpty()) {
            shippingCost = 60;
        }
        return shippingCost;
    }
}
