package me.gladysz.service;

import me.gladysz.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private Map<Long, Product> products;

    public ProductServiceImpl() {
        loadProducts();
    }

    @Override
    public List<Product> listAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getProductById(Long id) {
        return products.get(id);
    }

    @Override
    public Product saveOrUpdateProduct(Product product) {
        if (product != null) {
            if (product.getId() == null) {
                product.setId(getNextKey());
            }
            products.put(product.getId(), product);

            return product;
        } else {
            throw new RuntimeException("Product Can't be null");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        products.remove(id);
    }

    private Long getNextKey() {
        return Collections.max(products.keySet()) + 1;
    }

    private void loadProducts() {
        products = new HashMap<>();

        Product product1 = new Product();
        product1.setId(1L);
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://lorempixel.com/640/480/technics/1/");

        products.put(1L, product1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http://lorempixel.com/640/480/technics/2/");

        products.put(2L, product2);

        Product product3 = new Product();
        product3.setId(3L);
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("34.99"));
        product3.setImageUrl("http://lorempixel.com/640/480/technics/3/");

        products.put(3L, product3);

        Product product4 = new Product();
        product4.setId(4L);
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageUrl("http://lorempixel.com/640/480/technics/4/");

        products.put(4L, product4);

        Product product5 = new Product();
        product5.setId(5L);
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("45.99"));
        product5.setImageUrl("http://lorempixel.com/640/480/technics/5/");

        products.put(5L, product5);
    }
}
