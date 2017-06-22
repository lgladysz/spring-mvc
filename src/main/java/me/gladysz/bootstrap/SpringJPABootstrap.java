package me.gladysz.bootstrap;

import me.gladysz.enums.OrderStatus;
import me.gladysz.model.*;
import me.gladysz.model.security.Role;
import me.gladysz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ProductService productService;

    private final CustomerService customerService;

    private final UserService userService;

    private final OrderService orderService;

    private final RoleService roleService;

    @Autowired
    public SpringJPABootstrap(ProductService productService, CustomerService customerService, UserService userService, OrderService orderService, RoleService roleService) {
        this.productService = productService;
        this.customerService = customerService;
        this.userService = userService;
        this.orderService = orderService;
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
        loadUsersAndCustomers();
        loadCarts();
        loadOrderHistory();
        loadRoles();
        assignUsersToDefaultRole();
    }

    private void loadOrderHistory() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            Order order = new Order();
            order.setCustomer(user.getCustomer());
            order.setStatus(OrderStatus.SHIPPED);

            products.forEach(product -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addToOrderDetails(orderDetail);
                orderService.saveOrUpdate(order);
            });
        });
    }

    private void loadCarts() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartDetail(cartDetail);
            userService.saveOrUpdate(user);
        });
    }

    private void assignUsersToDefaultRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("CUSTOMER")) {
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }
        });
    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.saveOrUpdate(role);
    }


    private void loadUsersAndCustomers() {
        User user1 = new User();
        user1.setUsername("Username1");
        user1.setPassword("password1");

        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Wick");
        customer1.setEmail("email@email.com");
        customer1.setPhoneNumber("555555555");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLineOne("Some Street 1");
        customer1.getBillingAddress().setAddressLineTwo("Seccond street");
        customer1.getBillingAddress().setZipCode("55-555");
        customer1.getBillingAddress().setCity("NYC");

        customer1.setUser(user1);
        customerService.saveOrUpdate(customer1);


        User user2 = new User();
        user2.setUsername("Username2");
        user2.setPassword("password2");

        Customer customer2 = new Customer();
        customer2.setFirstName("Mark");
        customer2.setLastName("Twain");
        customer2.setEmail("emailemail@email.com");
        customer2.setPhoneNumber("555666333");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressLineOne("Some Street 4");
        customer2.getBillingAddress().setAddressLineTwo("Street");
        customer2.getBillingAddress().setZipCode("55-598");
        customer2.getBillingAddress().setCity("Warsaw");

        customer2.setUser(user2);
        customerService.saveOrUpdate(customer2);


        User user3 = new User();
        user3.setUsername("Username3");
        user3.setPassword("password3");

        Customer customer3 = new Customer();
        customer3.setFirstName("Bob");
        customer3.setLastName("Moon");
        customer3.setEmail("email22@email.com");
        customer3.setPhoneNumber("567891456");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddressLineOne("Street 1");
        customer3.getBillingAddress().setAddressLineTwo("Seccond");
        customer3.getBillingAddress().setZipCode("58-955");
        customer3.getBillingAddress().setCity("Cracow");

        customer3.setUser(user3);
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
