package me.gladysz.service.reposervice;

import me.gladysz.model.Product;
import me.gladysz.repository.ProductRepository;
import me.gladysz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class ProductServiceRepoImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceRepoImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return productRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }
}
