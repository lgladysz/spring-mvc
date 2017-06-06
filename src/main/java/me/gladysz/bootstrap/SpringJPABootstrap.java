package me.gladysz.bootstrap;

import me.gladysz.model.Customer;
import me.gladysz.model.Product;
import me.gladysz.service.CustomerService;
import me.gladysz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {


    private final ProductService productService;

    private final CustomerService customerService;

    @Autowired
    public SpringJPABootstrap(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
        loadCustomers();
    }

    private void loadCustomers() {

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("John");
        customer1.setLastName("Wick");
        customer1.setEmail("email@email.com");
        customer1.setPhoneNumber("555555555");
        customer1.setAddressLineOne("Some Street 1");
        customer1.setAddressLineTwo("Seccond street");
        customer1.setZipCode("55-555");
        customer1.setCity("NYC");
        customerService.saveOrUpdate(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Mark");
        customer2.setLastName("Twain");
        customer2.setEmail("emailemail@email.com");
        customer2.setPhoneNumber("555666333");
        customer2.setAddressLineOne("Some Street 4");
        customer2.setAddressLineTwo("Street");
        customer2.setZipCode("55-598");
        customer2.setCity("Warsaw");
        customerService.saveOrUpdate(customer2);


        Customer customer3 = new Customer();
        customer3.setId(3L);
        customer3.setFirstName("Bob");
        customer3.setLastName("Moon");
        customer3.setEmail("email22@email.com");
        customer3.setPhoneNumber("567891456");
        customer3.setAddressLineOne("Street 1");
        customer3.setAddressLineTwo("Seccond");
        customer3.setZipCode("58-955");
        customer3.setCity("Cracow");
        customerService.saveOrUpdate(customer3);
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
