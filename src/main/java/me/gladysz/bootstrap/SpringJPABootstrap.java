package me.gladysz.bootstrap;

import me.gladysz.model.Product;
import me.gladysz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {


    private final ProductService productService;

    @Autowired
    public SpringJPABootstrap(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
    }

    private void loadProducts() {

        Product product1 = new Product();
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://lorempixel.com/640/480/technics/1/");
        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http://lorempixel.com/640/480/technics/2/");
        productService.saveOrUpdate(product2);

        Product product3 = new Product();
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("34.99"));
        product3.setImageUrl("http://lorempixel.com/640/480/technics/3/");
        productService.saveOrUpdate(product3);

        Product product4 = new Product();
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageUrl("http://lorempixel.com/640/480/technics/4/");
        productService.saveOrUpdate(product4);

        Product product5 = new Product();
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("45.99"));
        product5.setImageUrl("http://lorempixel.com/640/480/technics/5/");
        productService.saveOrUpdate(product5);
    }
}
