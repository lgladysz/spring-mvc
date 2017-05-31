package me.gladysz.service;

import me.gladysz.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAllProducts();

    Product getProductById(Long id);

    Product saveOrUpdateProduct(Product product);

    void deleteProduct(Long id);
}
