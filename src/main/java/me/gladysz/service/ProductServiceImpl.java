package me.gladysz.service;

import me.gladysz.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class ProductServiceImpl extends AbstractMapService implements ProductService {

    @Override
    public Product getById(Long id) {
        return (Product) super.getById(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return (Product) super.saveOrUpdate(domainObject);
    }

    @Override
    protected void loadDomainObjects() {
        domainMap = new HashMap<>();

        Product product1 = new Product();
        product1.setId(1L);
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://lorempixel.com/640/480/technics/1/");

        domainMap.put(1L, product1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http://lorempixel.com/640/480/technics/2/");

        domainMap.put(2L, product2);

        Product product3 = new Product();
        product3.setId(3L);
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("34.99"));
        product3.setImageUrl("http://lorempixel.com/640/480/technics/3/");

        domainMap.put(3L, product3);

        Product product4 = new Product();
        product4.setId(4L);
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageUrl("http://lorempixel.com/640/480/technics/4/");

        domainMap.put(4L, product4);

        Product product5 = new Product();
        product5.setId(5L);
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("45.99"));
        product5.setImageUrl("http://lorempixel.com/640/480/technics/5/");

        domainMap.put(5L, product5);
    }
}
