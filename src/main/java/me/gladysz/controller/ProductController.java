package me.gladysz.controller;

import me.gladysz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/products")
    public String listProducts(Model model) {

        model.addAttribute("products", productService.listAllProducts());
        return "products";
    }

    @RequestMapping("/product/{id}")
    public String getProduct(@PathVariable Long id, Model model) {

        model.addAttribute("product", productService.getProductById(id));
        return "product";
    }
}
