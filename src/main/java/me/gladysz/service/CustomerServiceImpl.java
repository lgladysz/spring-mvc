package me.gladysz.service;

import me.gladysz.model.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<Long, Customer> customers;

    public CustomerServiceImpl() {
        loadCustomers();
    }

    @Override
    public List<Customer> listAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customers.get(id);
    }

    @Override
    public Customer saveOrUpdateCustomer(Customer customer) {
        if (customer != null) {
            if (customer.getId() == null) {
                customer.setId(getNextKey());
            }
            customers.put(customer.getId(), customer);

            return customer;
        } else {
            throw new RuntimeException("Customer Can't be null");
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        customers.remove(id);
    }

    private Long getNextKey() {
        return Collections.max(customers.keySet()) + 1;
    }

    private void loadCustomers() {
        customers = new HashMap<>();

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

        customers.put(1L, customer1);

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

        customers.put(2L, customer2);

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

        customers.put(3L, customer3);
    }
}
