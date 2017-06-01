package me.gladysz.service;

import me.gladysz.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> listAllCustomers();

    Customer getCustomerById(Long id);

    Customer saveOrUpdateCustomer(Customer customer);

    void deleteCustomer(Long id);
}
