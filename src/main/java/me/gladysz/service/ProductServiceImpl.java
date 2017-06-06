package me.gladysz.service;

import me.gladysz.model.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {

    @Override
    public Product getById(Long id) {
        return (Product) super.getById(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return (Product) super.saveOrUpdate(domainObject);
    }
}
