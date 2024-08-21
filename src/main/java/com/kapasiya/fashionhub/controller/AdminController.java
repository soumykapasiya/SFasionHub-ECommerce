package com.kapasiya.fashionhub.controller;


import com.kapasiya.fashionhub.dto.ProductDTO;
import com.kapasiya.fashionhub.entity.Category;
import com.kapasiya.fashionhub.entity.Products;
import com.kapasiya.fashionhub.mapper.ProductMapper;
import com.kapasiya.fashionhub.service.CategoryService;
import com.kapasiya.fashionhub.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private static final String UPLOAD = System.getProperty("user.dir") + "/src/main/resources/static/ProductImages";
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/admin")
    public String admin() {
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCat(Model model) {
        model.addAttribute("getCategory", categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String getAddCat(Model model) {
        model.addAttribute("getAddCategory", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String getAddCat(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id) {
        categoryService.deleteCatByID(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model) {
        Category category = categoryService.findCatById(id);

        if (category != null) {
            model.addAttribute("category", category);
            return "categoriesAdd";
        } else {
            return "error";
        }
    }

    @GetMapping("/admin/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }


    @GetMapping("/admin/products/add")
    public String productAddGet(Model model) {
        model.addAttribute("productsDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }


    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDTO") ProductDTO productDTO, @RequestParam("productImage") MultipartFile file, @RequestParam("imgName") String imgName) throws IOException {
        Products product = Products.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .brand(productDTO.getBrand())
                .description(productDTO.getDescription())
                .category(categoryService.findCatById(productDTO.getCategoryID()))
                .imageName(getImageName(file, imgName))
                .build();
        productService.addProducts(product);

        return "redirect:/admin/products";
    }

    private String getImageName(MultipartFile file, String imgName) throws IOException {
        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(UPLOAD, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        return imageUUID;
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProductsById(@PathVariable long id) {
        productService.deleteProductByID(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProductById(@PathVariable long id, Model model) {
        Products product = productService.findProductById(id);
        ProductDTO productDTO = ProductMapper.toDto(product);
        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("productsDTO", productDTO);
        return "productsAdd";

    }


}

