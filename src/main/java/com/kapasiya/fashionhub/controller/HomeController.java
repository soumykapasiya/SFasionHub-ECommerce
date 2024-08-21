package com.kapasiya.fashionhub.controller;


import com.kapasiya.fashionhub.global.GlobalData;
import com.kapasiya.fashionhub.service.CategoryService;
import com.kapasiya.fashionhub.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private static final String CART_COUNT = "cartCount";
    private static final String PRODUCTS = "products";
    private static final String CATEGORIES = "categories";

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute(CART_COUNT, GlobalData.cart.size());
        model.addAttribute(CATEGORIES, categoryService.getAllCategory());
        model.addAttribute(PRODUCTS, productService.getLimitedProducts());
        model.addAttribute("topRated", productService.getByName("Top Rated"));
        model.addAttribute("bestSelling", productService.getByName("Best Selling"));
        model.addAttribute("onSale", productService.getByName("On Sale"));
        return "index";
    }

    @GetMapping("/allCollections")
    public String allCollections(Model model) {
        model.addAttribute(CART_COUNT, GlobalData.cart.size());
        model.addAttribute(CATEGORIES, categoryService.getAllCategory());
        model.addAttribute(PRODUCTS, productService.getAllProducts());

        return "allItems";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute(CART_COUNT, GlobalData.cart.size());
        model.addAttribute(CATEGORIES, categoryService.getAllCategory());
        model.addAttribute(PRODUCTS, productService.getAllProducts());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(@PathVariable int id, Model model) {
        model.addAttribute(CART_COUNT, GlobalData.cart.size());
        model.addAttribute(CATEGORIES, categoryService.getAllCategory());
        model.addAttribute(PRODUCTS, productService.getAllProductsByCategoryId(id));
        return "shop";
    }

    @GetMapping("/shop/viewProduct/{id}")
    public String viewProduct(@PathVariable int id, Model model) {
        model.addAttribute(CART_COUNT, GlobalData.cart.size());
        model.addAttribute(PRODUCTS, productService.findProductById(id));
        return "viewProduct";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
